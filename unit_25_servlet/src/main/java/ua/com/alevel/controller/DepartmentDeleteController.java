package ua.com.alevel.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;

import java.io.IOException;

public class DepartmentDeleteController extends HttpServlet {

    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameterId = req.getParameter("id");
        Long id = Long.parseLong(parameterId);
        departmentService.delete(id);

        resp.sendRedirect("/unit_25_servlet/departments");
    }
}
