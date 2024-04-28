package com.example.secretscribe.web.controller;

import com.example.secretscribe.model.Confession;
import com.example.secretscribe.service.AdminService;
import com.example.secretscribe.service.ConfessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private ConfessionService confessionService;

    public AdminController(AdminService adminService, ConfessionService confessionService) {
        this.adminService = adminService;
        this.confessionService = confessionService;
    }

    @GetMapping("/page")
    public String getAdminPage(Model model) {
        model.addAttribute("confessions", confessionService.findAllUnapproved());
        return "adminPage";
    }

    @PostMapping("/approve")
    public String approveConfession(@RequestParam Long id) {
        if(this.confessionService.findById(id).isPresent()) {
            this.confessionService.approveConfession(id);
            return "redirect:/admin/page";
        }
        return "redirect:/approve/page/error?=NotFound";

    }

    @PostMapping("/delete")
    public String deleteConfession(@RequestParam Long id) {
        if(this.confessionService.findById(id).isPresent()) {
            this.confessionService.deleteById(id);
            return "redirect:/admin/page";
        }
        return "redirect:/approve/page/error?=NotFound";

    }



}




