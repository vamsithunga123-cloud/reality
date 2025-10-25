package com.maytri.reality.controller;

import com.maytri.reality.model.Enquiry;
import com.maytri.reality.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryRepository enquiryRepository;

    // Home page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("projectEnquiry", new Enquiry());
        model.addAttribute("contactEnquiry", new Enquiry());
        model.addAttribute("modalEnquiry", new Enquiry());
        return "index"; // Thymeleaf template name
    }

    // Project Form Submission
    @PostMapping("/enquiry/project")
    public String submitProject(@ModelAttribute("projectEnquiry") Enquiry enquiry, Model model) {
        enquiryRepository.save(enquiry);
        model.addAttribute("projectEnquiry", new Enquiry());
        model.addAttribute("projectSuccessMessage", "Project enquiry submitted successfully!");
        return "redirect:/";
    }

    // Contact Form Submission
    @PostMapping("/enquiry/contact")
    public String submitContact(@ModelAttribute("contactEnquiry") Enquiry enquiry, Model model) {
        enquiryRepository.save(enquiry);
        model.addAttribute("contactEnquiry", new Enquiry());
        model.addAttribute("contactSuccessMessage", "Contact form submitted successfully!");
        return "redirect:/";
    }

    // Modal Form Submission
    @PostMapping("/enquiry/modal")
    public String submitModal(@ModelAttribute("modalEnquiry") Enquiry enquiry, Model model) {
        enquiryRepository.save(enquiry);
        model.addAttribute("modalEnquiry", new Enquiry());
        model.addAttribute("modalSuccessMessage", "Modal enquiry submitted successfully!");
        return "redirect:/";
    }
}
