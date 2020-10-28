package com.users.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
    Connection connection;

    public UserLogin() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserLogin userLogin = new UserLogin();
        String uid = request.getParameter("email");
        String pword = request.getParameter("password");

        try {
            PreparedStatement statement = userLogin.connection.prepareStatement("Select * from users where uid=? and pword=?");
            statement.setString(1, uid);
            statement.setString(2, pword);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Login sucessful");
                HttpSession session = request.getSession();
                session.setAttribute("uid", rs.getInt(1));
                session.setAttribute("u_name", rs.getString(3));
                session.setAttribute("quesNO", "0");
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("Login failed");
                HttpSession session = request.getSession();
                session.setAttribute("wrongCredentials", "Wrong credentials. Try again!");
                response.sendRedirect("userlogin.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("userlogin.jsp");
    }
}
