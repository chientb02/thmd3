package com.example.thmd3.DAO;

import com.example.thmd3.model.Classroom;
import com.example.thmd3.myConnection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements IDAO<Classroom>{
    private Connection connection;
    private String SELECT_ALL = "select * from classroom ;";
    private String SELECT_ONE = "select * from classroom where id = ? ;";

    public ClassDAO() {
        connection = new MyConnection().getConnection();
    }

    @Override
    public List<Classroom> findAll() {
        List<Classroom> list = new ArrayList<>() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idClass = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new Classroom(idClass,name));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Classroom findOne(int id) {
        Classroom list = null ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idClass = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list = new Classroom(idClass,name);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public void update(Classroom classroom) {

    }

    @Override
    public void create(Classroom classroom) {

    }

    @Override
    public void delete(int id) {

    }
}
