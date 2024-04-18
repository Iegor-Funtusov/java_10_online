package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.AttachedEmployeesToDepartment;
import ua.com.alevel.facade.DepartmentFacade;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @GetMapping
    public String getDepartments(Model model) {
        model.addAttribute("departments", departmentFacade.findAll());
        return "pages/departments/departments_all";
    }

    @GetMapping("/{id}/attach")
    public String attachEmployeeToDepartmentPage(Model model, @PathVariable Long id) {
        model.addAttribute("department", departmentFacade.findById(id));
        model.addAttribute("nonAttachedEmployees", departmentFacade.findByNonAttachToDepartment(id));
        model.addAttribute("attachedEmployeesToDepartment", new AttachedEmployeesToDepartment());
        return "pages/departments/departments_attach";
    }

    @PostMapping("/{id}/attach")
    public String attachEmployeesToDepartment(@ModelAttribute AttachedEmployeesToDepartment attachedEmployeesToDepartment, @PathVariable Long id) {
        departmentFacade.attachEmployeesToDepartment(id, attachedEmployeesToDepartment.getEmployeesId());
        return "redirect:/departments";
    }
}
