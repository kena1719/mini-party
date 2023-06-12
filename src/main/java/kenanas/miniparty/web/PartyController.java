package kenanas.miniparty.web;

import jakarta.annotation.PostConstruct;
import kenanas.miniparty.domain.party.Party;
import kenanas.miniparty.domain.party.PartyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/parties")
@RequiredArgsConstructor
public class PartyController {

    private final PartyRepository partyRepository;


    @GetMapping
    public String parties(Model model){
        List<Party> parties = partyRepository.findAll();
        model.addAttribute("parties", parties);
        log.info("Model ={}" , model);

        return "form/parties";
    }

    @GetMapping("/{partyId}")
    public String party(@PathVariable long partyId, Model model){
        Party party = partyRepository.findById(partyId);
        model.addAttribute("party", party);
        return "form/party";
    }

    @GetMapping("/add")
    public String addParty(Model model){
        model.addAttribute("party", new Party());
        return "form/addParty";
    }

    @PostMapping("/add")
    public String addParty(@ModelAttribute Party party, RedirectAttributes redirectAttributes){
        //원래 로그인한사람 아이디가 들어가야함
        party.setPartyAdmin(0);
        //로그인 기능이 없으므로 임시 0번.

        Party savedParty = partyRepository.save(party);
        redirectAttributes.addAttribute("partyId", savedParty.getPartyId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/parties/{partyId}";
    }

    @GetMapping("/{partyId}/edit")
    public String editParty(@PathVariable Long partyId, Model model){
        Party party = partyRepository.findById(partyId);
        model.addAttribute("party", party);
        return "form/editParty";
    }

    @PostMapping("/{partyId}/edit")
    public String edit(@PathVariable Long partyId, @ModelAttribute Party party){
        partyRepository.update(partyId, party);
        return "redirect:/parties/{partyId}";
    }


    @PostConstruct
    public void init(){
        Party party1 = new Party("파티A", "처음 만든 파티입니다", 0,
                true, null, null);
        Party party2 = new Party("파티B", "두번째로 만든 파티입니다", 0,
                false, null, null);

        partyRepository.save(party1);
        partyRepository.save(party2);
    }


}
