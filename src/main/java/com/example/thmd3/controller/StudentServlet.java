package com.example.thmd3.controller;

import com.example.thmd3.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;

    public StudentServlet() {
        studentService = new StudentService() ;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                display(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "update":
                update(request, response);
                break;
        }


    }

    public void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students" ,studentService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/display.jsp") ;
        requestDispatcher.forward(request,response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("classroom" ,studentService.getClassDAO().findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/create.jsp") ;
        requestDispatcher.forward(request,response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentService.delete(request);
       display(request,response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("classroom" ,studentService.getClassDAO().findAll());
        request.setAttribute("students",studentService.findOne(request));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/update.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
            case "search":
                search(request, response);
                break;
        }


    }

    public void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentService.create(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/create.jsp");
        requestDispatcher.forward(request, response);

    }


    public void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentService.update(request);
        request.setAttribute("students", studentService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/display.jsp");
        requestDispatcher.forward(request, response);
    }

    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students" ,studentService.findByName(request));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/display.jsp") ;
        requestDispatcher.forward(request,response);
    }
}
