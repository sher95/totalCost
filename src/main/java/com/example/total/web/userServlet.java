package com.example.total.web;

import com.example.total.dao.dao;
import com.example.total.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "userServlet", value = "/userServlet")
public class userServlet extends HttpServlet {
    private com.example.total.dao.dao dao;

    public userServlet(){
        this.dao = new dao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action){
            case "/new":
                showNewUser(request, response);
                break;
            case "/insert":
                try{
                    InsertUser(request, response);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "/delete":
                try{
                    deleteUser(request, response);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "/edit":
                try {
                    showEdit(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updateUser(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                try {
                    listUser(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;
        }
    }

    private void showNewUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void InsertUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        User newUser = new User(name, amount);
        dao.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteUser(id);
        response.sendRedirect("list");
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        User existUser = dao.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existUser);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));

        User bo = new User(id, name, amount);
        dao.updateUser(bo);
        response.sendRedirect("list");
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        List<User> listUser = dao.selectAllUser();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);

    }
}
