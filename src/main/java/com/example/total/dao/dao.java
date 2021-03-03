package com.example.total.dao;

import com.example.total.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//Database operations

public class dao {
    private final String url = "jdbc:postgresql://localhost/totalcost";
    private final String user = "postgres";
    private final String password = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO sheet" +
            "  (id, name, amount) VALUES " +
            " (?, ?, ?);";
    private static final String QUERY = "select id, name, amount from totalcost where id=?";
    private static final String SELECT_ALL_QUERY = "select * from totalcost";
    private static final String UPDATE_USERS_SQL = "update sheet set name = ? where id = ?;";
    private static final String DELETE_USERS_SQL = "delete from sheet where id = ?;";

    protected Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }


    //create user or insert
    public void insertUser(User user) throws SQLException{
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAmount());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //update users
    public boolean updateUser(User user) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAmount());
            statement.setInt(3, user.getId());

            rowUpdated = statement.executeUpdate()>0;
        }
        return rowUpdated;
    }

    //select user by id
    public User selectUser(int id){
        User user = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);){
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                int amount = rs.getInt("amount");
                user = new User(id, name, amount);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    //select users
    public List<User> selectAllUser(){
        List<User> users = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int amount = rs.getInt("amount");
                users.add(new User(id, name, amount));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    //delete user
    public boolean deleteUser(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate()>0;
        }
        return rowDeleted;
    }
}


