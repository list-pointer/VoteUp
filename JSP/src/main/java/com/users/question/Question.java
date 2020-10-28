package com.users.question;

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

@SuppressWarnings("serial")
@WebServlet("/question")
public class Question extends HttpServlet {
    Connection connection;

    public Question() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("SKIP".equals(action)) {
            request.getSession().setAttribute("quesNO",
                    Integer.toString((Integer.parseInt(request.getSession().getAttribute("quesNO").toString()) + 1)));
            response.sendRedirect("index.jsp");
        } else {
            int flag = 0;
            int vote = 0;
            Question question = new Question();
            HttpSession session = request.getSession();

            try {
                PreparedStatement statement = question.connection
                        .prepareStatement("insert into answertable(u_id, q_id, vote) values (?,?,?)");
                statement.setInt(1, Integer.parseInt(session.getAttribute("uid").toString()));
                statement.setInt(2, Integer.parseInt(session.getAttribute("quesNO").toString()));

                if ("YES".equals(action))
                    vote = 1;
                else if ("NO".equals(action))
                    vote = 0;

                statement.setInt(3, vote);
                flag = statement.executeUpdate();

                if (flag > 0)
                    System.out.println("Vote casted as " + vote);
                else
                    System.out.println("Something happened!");

                statement.close();
                request.getSession().setAttribute("quesNO", Integer
                        .toString((Integer.parseInt(request.getSession().getAttribute("quesNO").toString()) + 1)));
                response.sendRedirect("index.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
