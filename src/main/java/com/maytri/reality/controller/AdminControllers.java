package com.maytri.reality.controller;

import com.maytri.reality.model.Enquiry;
import com.maytri.reality.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminControllers {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @GetMapping("/admin/enquiries")
    public String viewEnquiries(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        List<Enquiry> enquiries;

        if (startDate != null && endDate != null) {
            enquiries = enquiryRepository.findByCreatedDateBetween(startDate, endDate);
        } else {
            enquiries = enquiryRepository.findAll();
        }

        model.addAttribute("enquiries", enquiries);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "admin-enquiries";
    }

    @GetMapping("/admin-enquiries/download-by-date")
    public ResponseEntity<byte[]> downloadEnquiriesByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
            throws IOException {

        List<Enquiry> enquiries = enquiryRepository.findByCreatedDateBetween(startDate, endDate);
        String fileName = String.format("enquiries_%s_to_%s.csv", startDate, endDate);
        return generateCsvResponse(enquiries, fileName);
    }

    private ResponseEntity<byte[]> generateCsvResponse(List<Enquiry> enquiries, String filename) throws IOException {
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Name,Phone,Email,Interest,Message,Created Date\n");

        for (Enquiry e : enquiries) {
            csv.append(e.getId()).append(",")
                    .append(e.getName()).append(",")
                    .append(e.getPhone()).append(",")
                    .append(e.getEmail()).append(",")
                    .append(e.getInterest()).append(",")
                    .append(e.getMessage().replace(",", " ")).append(",")
                    .append(e.getCreatedDate()).append("\n");
        }

        byte[] csvBytes = csv.toString().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    }
}