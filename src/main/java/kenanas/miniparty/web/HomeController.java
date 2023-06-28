package kenanas.miniparty.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kenanas.miniparty.domain.party.Party;
import kenanas.miniparty.domain.party.PartyRepository;
import kenanas.miniparty.domain.user.User;
import kenanas.miniparty.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final PartyRepository partyRepository;


    //@GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model){
        model.addAttribute("parties", partyRepository.findAll());

        //로그인하지 않았을 때
        if (loginUser == null){
            return "home";
        }

        //로그인했을 때
        model.addAttribute("user", loginUser);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeV2(@Login User loginUser, Model model){
        model.addAttribute("parties", partyRepository.findAll());

        //로그인하지 않았을 때
        if (loginUser == null){
            return "home";
        }

        //로그인했을 때
        model.addAttribute("user", loginUser);
        return "loginHome";
    }

    @GetMapping("/error-page/500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }



}
