package Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
@WebServlet("/question")
public class Question extends HttpServlet {
    Connection connection;

    public Question() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/html_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int votes = 0;
        int flag = 0;
        Question question = new Question();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        try {
            PreparedStatement statement = question.connection.prepareStatement("select * from questiontable where q_id=1");
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                if ("YES".equals(action)) {
                    votes = rs.getInt(3);
                    PreparedStatement statement2 = question.connection.prepareStatement("update questiontable set v_yes=? where q_id=1");
                    statement2.setInt(1, ++votes);
                    flag = statement2.executeUpdate();
                    out.print("Yes clicked.");
                    statement2.close();
                    session.setAttribute("click", "YES");
                    response.sendRedirect("ShowQuestion");

                } else if ("NO".equals(action)) {
                    votes = rs.getInt(4);
                    PreparedStatement statement2 = question.connection.prepareStatement("update questiontable set v_no=? where q_id=1");
                    statement2.setInt(1, ++votes);
                    flag = statement2.executeUpdate();
                    out.print("NO clicked.");
                    statement2.close();
                    session.setAttribute("click", "NO");
                    response.sendRedirect("ShowQuestion");
                }
                if (flag > 0)
                    System.out.println("Updated sucessfully to " + votes);
                else
                    System.out.println("Something happened!");

                rs.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
