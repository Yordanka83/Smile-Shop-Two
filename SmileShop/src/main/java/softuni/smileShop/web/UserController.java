package softuni.smileShop.web;


import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.smileShop.model.binding.UserLoginBindingModel;
import softuni.smileShop.model.binding.UserRegisterBindingModel;
import softuni.smileShop.model.service.UserServiceModel;
import softuni.smileShop.service.UserService;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username, RedirectAttributes attributes) {

        attributes.addFlashAttribute("error_message", true);
        attributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }


    @PostMapping("/register")
    public String registerAndLoginUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/register";
        }

        if (userService.userExists(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/register";
        }

        UserServiceModel userServiceModel = modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class);

        this.userService.registerUser(userServiceModel);

        return "redirect:/home";


    }
    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username, RedirectAttributes attributes) {

        attributes.addFlashAttribute("error_message", true);
        attributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

//    @GetMapping("/crash")
//    public ModelAndView crash() {
//        throw new RuntimeException("Uuups, we crashed.");
//    }
//
//    @ExceptionHandler({PersistenceException.class})
//    public ModelAndView handleException( ex) {
//        ModelAndView modelAndView = new ModelAndView("handler");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }
}
