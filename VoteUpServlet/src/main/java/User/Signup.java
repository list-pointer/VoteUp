package User;

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
@WebServlet("/signup")
public class Signup extends HttpServlet {
    Connection connection;

    public Signup() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/html_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Signup signup = new Signup();

        String uid = request.getParameter("uid");
        String name = request.getParameter("uName");
        String email = request.getParameter("email");
        String pword = request.getParameter("password");


        try {
            PreparedStatement statement = signup.connection.prepareStatement("insert into users (uid,u_name,email,pword) values (?,?,?,?)");
            statement.setString(1, uid);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, pword);
            int flag = statement.executeUpdate();
            if (flag > 0) {
                System.out.println("Signup sucessful");
                response.sendRedirect("login.html");
            } else {
                System.out.println("Signup failed");
                response.sendRedirect("signup.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
