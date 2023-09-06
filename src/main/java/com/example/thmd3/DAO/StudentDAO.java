package com.example.thmd3.DAO;

import com.example.thmd3.model.Classroom;
import com.example.thmd3.model.Student;
import com.example.thmd3.myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IDAO<Student> {
    private Connection connection;
    private ClassDAO classDAO;

    public StudentDAO() {
        connection = new MyConnection().getConnection();
        classDAO = new ClassDAO();
    }

    public ClassDAO getClassDAO() {
        return classDAO;
    }

    public void setClassDAO(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    private String SELECT_ALL = "select * from student";
    private String SELECT_ONE = "select * from student where id = ?";
    private String CREATE = "insert into student (name , email,dob,address,phone_number,classroom) values (?,?,?,?,?,?);";
    private String UPDATE = "update student set name =? , email = ?,dob = ?,address = ?,phone_number = ?,classroom = ?  where id=?;";
    private String DELETE = "delete from student where id = ?;";
    private String SEARCH = "select * from student where name LIKE ?;";

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dOB = resultSet.getObject("dob", LocalDate.class);
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                Classroom classroom = classDAO.findOne(resultSet.getInt("classroom"));
                students.add(new Student(idStudent, name, email, dOB, address, phone, classroom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student findOne(int id) {
        Student students = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dOB = resultSet.getObject("dob", LocalDate.class);
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                Classroom classroom = classDAO.findOne(resultSet.getInt("classroom"));
                students = new Student(idStudent, name, email, dOB, address, phone, classroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getDateOfBirth().toString());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassroom().getId());
            preparedStatement.setInt(7, student.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getDateOfBirth().toString());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassroom().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                String nameStudent = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dOB = resultSet.getObject("dob", LocalDate.class);
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                Classroom classroom = classDAO.findOne(resultSet.getInt("classroom"));
                students.add(new Student(idStudent, nameStudent, email, dOB, address, phone, classroom));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
