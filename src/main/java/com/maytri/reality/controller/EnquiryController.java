package com.maytri.reality.controller;

import com.maytri.reality.model.Enquiry;
import com.maytri.reality.model.Project;
import com.maytri.reality.repository.EnquiryRepository;
import com.maytri.reality.repository.ProjectRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryRepository enquiryRepository;
    @Autowired
    private ProjectRepository projectRepository;

    // Home Page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("projectEnquiry", new Enquiry());
        model.addAttribute("contactEnquiry", new Enquiry());
        model.addAttribute("modalEnquiry", new Enquiry());
        List<Project> projects = projectRepository.findAll();
    model.addAttribute("projects", projects);
        return "index"; 
    }


    @PostMapping("/enquiry/project")
    public String submitProject(@ModelAttribute("projectEnquiry") Enquiry enquiry, Model model) {
        enquiryRepository.save(enquiry);
        model.addAttribute("projectEnquiry", new Enquiry());
        model.addAttribute("projectSuccessMessage", "Project enquiry submitted successfully!");
        return "redirect:/";
    }

    // 📬 Contact Form Submission
    @PostMapping("/enquiry/contact")
    public String submitContact(@ModelAttribute("contactEnquiry") Enquiry enquiry, Model model) {
        enquiryRepository.save(enquiry);
        model.addAttribute("contactEnquiry", new Enquiry());
        model.addAttribute("contactSuccessMessage", "Contact form submitted successfully!");
        return "redirect:/";
    }

    // 💬 Modal Form Submission
    @PostMapping("/enquiry/modal")
    public String submitModal(@ModelAttribute("modalEnquiry") Enquiry enquiry, Model model) {
        enquiryRepository.save(enquiry);
        model.addAttribute("modalEnquiry", new Enquiry());
        model.addAttribute("modalSuccessMessage", "Modal enquiry submitted successfully!");
        return "redirect:/";
    }

    //Projects Page
    @GetMapping("/projects")
    public String projects() {
        return "projects"; // templates/projects.html
    }

    // About Us Page
    @GetMapping("/about_us")
    public String aboutUs(Model model) {
        return "about_us"; // templates/about_us.html
    }

    // Contact Us Page
    @GetMapping("/contact_us")
    public String contactUs(Model model) {
        return "contact_us"; // templates/contact_us.html
    }
    @GetMapping("/termsAndConditions")
    public String termsAndConditions(Model model){
        return "termsAndConditions";
    }
    @GetMapping("/privacyAndPolicy")
    public String privacyAndPolicy(Model model){
        return "privacyAndPolicy";
    }
}
