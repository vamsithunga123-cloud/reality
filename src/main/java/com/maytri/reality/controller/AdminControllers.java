
package com.maytri.reality.controller;

import com.maytri.reality.model.Enquiry;
import com.maytri.reality.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminControllers {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @GetMapping("/admin/enquiries")
    public String getAllEnquiries(Model model) {
        List<Enquiry> enquiries = enquiryRepository.findAll();
        model.addAttribute("enquiries", enquiries);
        return "admin-enquiries";
    }
}
