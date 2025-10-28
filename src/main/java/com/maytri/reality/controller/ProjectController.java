package com.maytri.reality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.maytri.reality.repository.ProjectRepository;


    @Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/our-projects")
    public String showProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projects";
    }
}


