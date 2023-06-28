package kenanas.miniparty.web.user;

import jakarta.validation.Valid;
import kenanas.miniparty.domain.user.User;
import kenanas.miniparty.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "users/addUserForm";
        }
        userRepository.save(user);
        return "redirect:/";

    }

}
