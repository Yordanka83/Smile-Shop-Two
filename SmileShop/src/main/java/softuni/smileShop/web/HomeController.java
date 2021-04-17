package softuni.smileShop.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";

    }


//    @GetMapping("/admin")
//    public ModelAndView admin(@AuthenticationPrincipal UserDetails principal) {
//        ModelAndView mav= new ModelAndView("admin");
//        mav.addObject("user", principal);
//        return mav;
//    }
//
//    @GetMapping("/user")
//    public ModelAndView user(@AuthenticationPrincipal UserDetails principal) {
//        ModelAndView mav= new ModelAndView("user");
//        mav.addObject("user", principal);
//        return mav;
//    }
}
