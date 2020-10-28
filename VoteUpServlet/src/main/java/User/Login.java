package User;

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
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    Connection connection;

    public Login() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/html_voteup", "root", "3569");
            System.out.println("** Connection has been established **");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Login login = new Login();
        String uid = request.getParameter("email");
        String pword = request.getParameter("password");
        HttpSession session = request.getSession();

        try {
            PreparedStatement statement = login.connection.prepareStatement("Select * from users where uid=? and pword=?");
            statement.setString(1, uid);
            statement.setString(2, pword);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Login sucessful");
                session.setAttribute("login", "true");
                response.sendRedirect("ShowQuestion");
            } else {
                System.out.println("Login failed");
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
