package com.admins.login;

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
@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
    Connection connection;

    public AdminLogin() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminLogin adminLogin = new AdminLogin();
        String a_uname = request.getParameter("email");
        String pword = request.getParameter("password");

        try {
            PreparedStatement statement = adminLogin.connection.prepareStatement("Select * from admins where a_uname=? and pword=?");
            statement.setString(1, a_uname);
            statement.setString(2, pword);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Login sucessful");
                HttpSession session = request.getSession();
                session.setAttribute("a_uname", a_uname);
                response.sendRedirect("adminindex.jsp");
            } else {
                System.out.println("Login failed");
                HttpSession session = request.getSession();
                session.setAttribute("wrongCredentials", "Wrong credentials. Try again!");
                response.sendRedirect("adminlogin.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
