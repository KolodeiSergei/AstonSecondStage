package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewsController {

    @GetMapping("/home")
    public String viewHomePage() {
        System.out.println("viewHomePage");
        return "views.home_page.jsp";
    }
    @GetMapping("/createNewUser")
    public String createUser() {
        return "create_user_page";
    }
}
