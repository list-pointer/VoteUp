package com.admins.question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
    Connection connection;

    public AddQuestion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddQuestion addQuestion = new AddQuestion();
        try {
            PreparedStatement statement = addQuestion.connection.prepareStatement("insert into questiontable(question) values (?)");
            statement.setString(1, request.getParameter("question"));

            int flag = statement.executeUpdate();
            if (flag > 0)
                request.getSession().setAttribute("quesInsertOk", "New Question Inserted!");
            else
                request.getSession().setAttribute("quesInsertFail", "Some problem occured");
            response.sendRedirect("adminindex.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("adminindex.jsp");
    }
}
