package com.maytri.reality.controller;

import com.maytri.reality.model.Project;
import com.maytri.reality.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/projects")
public class AdminProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    
    @GetMapping
    public String viewProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("project", new Project()); // for add form
        return "admin-projects";
    }

   
    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/admin/projects";
    }

    // Delete project
    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/admin/projects";
    }

    // Edit project (optional)
    @PostMapping("/edit/{id}")
    public String editProject(@PathVariable Long id, @ModelAttribute Project project) {
        project.setId(id);
        projectRepository.save(project);
        return "redirect:/admin/projects";
    }
}
