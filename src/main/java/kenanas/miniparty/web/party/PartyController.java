package kenanas.miniparty.web.party;


import jakarta.validation.Valid;
import kenanas.miniparty.domain.file.FileStore;
import kenanas.miniparty.domain.file.UploadFile;
import kenanas.miniparty.domain.party.Party;
import kenanas.miniparty.domain.party.PartyRepository;
import kenanas.miniparty.domain.user.User;
import kenanas.miniparty.web.argumentresolver.Login;
import kenanas.miniparty.web.party.form.PartySaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/parties")
@RequiredArgsConstructor
public class PartyController {

    private final PartyRepository partyRepository;
    private final FileStore fileStore;


    @GetMapping
    public String parties(Model model){
        List<Party> parties = partyRepository.findAll();
        model.addAttribute("parties", parties);
        log.info("Model ={}" , model);

        return "parties/parties";
    }

    @GetMapping("/{partyId}")
    public String party(@PathVariable long partyId, Model model){
        Party party = partyRepository.findById(partyId);
        model.addAttribute("party", party);
        return "parties/party";
    }

    @GetMapping("/add")
    public String addParty(Model model){
        model.addAttribute("party", new PartySaveForm());
        return "parties/addParty";
    }

    @PostMapping("/add")
    public String addParty(@Login User loginUser, @Valid @ModelAttribute("party") PartySaveForm form,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        log.info("file={}", form.getImageUrl());
        log.info("ifUrl={}", form.getImageUrl().isEmpty());

        //공개여부가 false(비공개) 라면
        if (!form.getIsPublic()){
            //비밀번호가 존재해야함.
            if(form.getPartyPassword().isBlank() || form.getPartyPassword().isEmpty()){
                bindingResult.reject("passwordError", "비공개 파티는 비밀번호 설정이 필수입니다.");
            }
        }

        if (bindingResult.hasErrors()){
            log.info("error = {}", bindingResult);
            return "parties/addParty";
        }

        UploadFile file = fileStore.storeFile(form.getImageUrl());
        Party savedParty = formToParty(loginUser, form, file);


        redirectAttributes.addAttribute("partyId", savedParty.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/parties/{partyId}";
    }

    private Party formToParty(User loginUser, PartySaveForm form, UploadFile file) {
        //성공 로직
        Party party = new Party();

        //필수
        party.setPartyAdmin(loginUser.getId());
        party.setPartyName(form.getPartyName());
        party.setPartyDescription(form.getPartyDescription());

        //선택
        party.setIsPublic(form.getIsPublic());
        party.setPartyPassword(form.getPartyPassword());
        party.setImageUrl(file);

        return partyRepository.save(party);
    }

    @GetMapping("/{partyId}/edit")
    public String editParty(@PathVariable Long partyId, Model model){
        Party party = partyRepository.findById(partyId);
        model.addAttribute("party", party);
        return "parties/editParty";
    }

    @PostMapping("/{partyId}/edit")
    public String edit(@Login User loginUser, @PathVariable Long partyId, @ModelAttribute PartySaveForm form) throws IOException {
        UploadFile file = fileStore.storeFile(form.getImageUrl());
        Party savedParty = formToParty(loginUser, form, file);
        partyRepository.update(partyId, savedParty);
        return "redirect:/parties/{partyId}";

        //파일을 새로 업로드하지 않았다면 변경하면 안됨.
    }

    //이게 보여주는거
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.getFullPath(filename));
    }





}
