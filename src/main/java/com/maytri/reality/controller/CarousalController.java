package com.maytri.reality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.maytri.reality.model.Carousal;
import com.maytri.reality.repository.CarousalRepository;

@Controller
@RequestMapping("/admin/carousal")
public class CarousalController {

    private final CarousalRepository repo;

    public CarousalController(CarousalRepository repo) {
        this.repo = repo;
    }

    // ✅ Show all carousal images
    @GetMapping
    public String showCarousal(Model model) {
        model.addAttribute("carousalAll", repo.findAll());
        model.addAttribute("newCarousal", new Carousal());
        return "admin-carousal";  // your Thymeleaf page
    }

    // ✅ Add new carousal image
    @PostMapping("/add")
    public String addCarousal(@ModelAttribute("newCarousal") Carousal newCarousal) {
        repo.save(newCarousal);
        return "redirect:/admin/carousal";
    }

    // ✅ Delete image by ID
    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin/carousal";
    }
}
