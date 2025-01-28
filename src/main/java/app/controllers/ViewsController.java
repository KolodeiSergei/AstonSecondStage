package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {

    @GetMapping("/home")
    public String viewHomePage() {
        return "home_page";
    }
    @GetMapping("/createNewUser")
    public String createUser() {
        return "create_user_page";
    }
}
