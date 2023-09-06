package com.example.thmd3.service;

import com.example.thmd3.DAO.ClassDAO;
import com.example.thmd3.DAO.StudentDAO;
import com.example.thmd3.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentService implements  IGenerateService <Student> {
    private ClassDAO classDAO ;
    public StudentDAO studentDAO ;

    public StudentService() {
        classDAO = new ClassDAO() ;
        studentDAO = new StudentDAO();
    }

    public ClassDAO getClassDAO() {
        return classDAO;
    }

    public void setClassDAO(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public List<Student> findByName(HttpServletRequest request) throws ServletException, IOException {
        String name = request.getParameter("search");
        return studentDAO.findByName(name);
    }


    @Override
    public Student findOne(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        return studentDAO.findOne(id);
    }

    @Override
    public void update(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name") ;
        String email = request.getParameter("email") ;
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int idClass = Integer.parseInt(request.getParameter("classroom"));
        Student student = new Student(id,name,email, dob,address,phone,classDAO.findOne(idClass));
        studentDAO.update(student);
    }

    @Override
    public void create(HttpServletRequest request) throws ServletException, IOException {
        String name = request.getParameter("name") ;
        String email = request.getParameter("email") ;
        LocalDate dob = LocalDate.parse(request.getParameter("dob"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int idClass = Integer.parseInt(request.getParameter("classroom"));
        Student student = new Student(name,email, dob,address,phone,classDAO.findOne(idClass));
        studentDAO.create(student);
    }

    @Override
    public void delete(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.delete(id);
    }


}
