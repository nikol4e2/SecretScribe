package com.example.secretscribe.web.controller;


import com.example.secretscribe.model.Admin;
import com.example.secretscribe.model.exceptions.InvalidUserCredentialsException;
import com.example.secretscribe.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/login")
    public String getLoginPage()
    {
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, Model model)
    {
        Admin admin=null;

        //TODO Implement showing the error on Front end
        try {
            admin=adminService.login(username,password);
            request.getSession().setAttribute("admin",admin);
            return "redirect:/admin/page";
        }catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError",true);
            model.addAttribute("error",exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/";
    }
}

