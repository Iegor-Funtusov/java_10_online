package ua.com.alevel.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.Department;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class DepartmentReadController extends HttpServlet {

    private final DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Department> departments = departmentService.findAll();

        PrintWriter writer = resp.getWriter();

        writer.println("<html>");

        writer.println("<head>");
        writer.println("<title>Department Read</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Observe all department</h1>");

        if (CollectionUtils.isNotEmpty(departments)) {
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>Id</th>");
            writer.println("<th>Name</th>");
            writer.println("<th>Delete</th>");
            writer.println("</tr>");
            for (Department department : departments) {
                writer.println("<tr>");
                writer.println("<td>" + department.getId() + "</td>");
                writer.println("<td>" + department.getName() + "</td>");
                writer.write("<td><a href=\"/unit_25_servlet/departments/delete?id=" + department.getId() + "\">delete</a></td>");
                writer.println("</tr>");
            }
            writer.println("</table>");
        } else {
            writer.println("<h2>There are no departments</h2>");
        }

        writer.println("<a href=\"/unit_25_servlet/departments/new\">Create new departament</a>");
        writer.println("</body>");

        writer.println("</html>");
    }
}
