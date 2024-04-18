package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.request.EmployeeRequest;
import ua.com.alevel.dto.response.EmployeeResponse;
import ua.com.alevel.facade.EmployeeFacade;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @GetMapping
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeFacade.findAll());
        return "pages/employees/employees_all";
    }

    @GetMapping("/new")
    public String getNewEmployeePage(Model model) {
        model.addAttribute("employee", new EmployeeRequest());
        return "pages/employees/employees_new";
    }

    @PostMapping("/new")
    public String newEmployee(@ModelAttribute EmployeeRequest employeeRequest) {
        employeeFacade.create(employeeRequest);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable Long id) {
        employeeFacade.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String getUpdateEmployeePage(Model model, @PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeFacade.findById(id);
        model.addAttribute("currentEmployee", employeeResponse);
        return "pages/employees/employees_update";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@ModelAttribute EmployeeRequest employeeRequest, @PathVariable Long id) {
        employeeFacade.update(employeeRequest, id);
        return "redirect:/employees";
    }
}
