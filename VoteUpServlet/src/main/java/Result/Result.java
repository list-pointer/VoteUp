package Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
@WebServlet("/result")
public class Result extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // To tell browser to not store cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-validate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxy server

        if (request.getSession().getAttribute("login") == "true") {
            try {
                PrintWriter out = response.getWriter();
                out.print("<!DOCTYPE html>\r\n" + "<html>\r\n" + "+ \"<head>\\r\\n\"\n" +
                        "\t\t\t\t+ \"<meta charset=\\\"UTF-8\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"<meta content=\\\"width=device-width, initial-scale=1.0\\\" name=\\\"viewport\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"<meta content=\\\"ie=edge\\\" http-equiv=\\\"X-UA-Compatible\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"<title>VoteUp Question Page</title>\\r\\n\"\n" +
                        "\t\t\t\t+ \"<link\\r\\n\"\n" +
                        "\t\t\t\t+ \"\thref=\\\"https://fonts.googleapis.com/css?family=Karla:400,700&display=swap\\\"\\r\\n\"\n" +
                        "\t\t\t\t+ \"\trel=\\\"stylesheet\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"<link\\r\\n\"\n" +
                        "\t\t\t\t+ \"\thref=\\\"https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css\\\"\\r\\n\"\n" +
                        "\t\t\t\t+ \"\trel=\\\"stylesheet\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"<link\\r\\n\"\n" +
                        "\t\t\t\t+ \"\thref=\\\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\\\"\\r\\n\"\n" +
                        "\t\t\t\t+ \"\trel=\\\"stylesheet\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"<link href=\\\"assets/css/signUp.css\\\" rel=\\\"stylesheet\\\">\\r\\n\"\n" +
                        "\t\t\t\t+ \"</head>\r\n" + "<body class=\"jumbotron\" style=\"margin-bottom: -50px;\">\r\n"
                        + "\r\n"
                        + "	<nav class=\"navbar navbar-light bg-light\" style=\"margin-top: -30px;\">\r\n"
                        + "		<a class=\"display-4\">VoteUp</a>\r\n"
                        + "		<form class=\"form-inline\" method=\"post\">\r\n"
                        + "			<button class=\"btn btn-outline-danger my-2 my-sm-0\" name=\"result\"\r\n"
                        + "				type=\"submit\" onclick=\"form.action='result';\">Show Result</button> &emsp; \r\n"
                        + "			<button class=\"btn btn-outline-danger my-2 my-sm-0\" name=\"action\"\r\n"
                        + "				type=\"submit\" onclick=\"form.action='logout';\">Logout</button>\r\n"

                        + "		</form>\r\n"
                        + "	</nav>\r\n");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/html_voteup", "root",
                        "3569");
                System.out.println("** Connection has been established **");
                String sqlQuery = "select v_yes,v_no from questiontable where q_id=1";
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
                ResultSet rs = statement.executeQuery();
                rs.next();
                int yes = rs.getInt(1);
                int no = rs.getInt(2);
                rs.close();

                out.println("<br><br><h1 class=\"display-5\">Number of Yes Votes -->" + yes + "</h1>");
                out.println("<br><br><h1 class=\"display-5\">Number of No Votes -->" + no + "</h1><br><br>");

                if (yes > no)
                    out.println("<br><br><h1 class=\"display-5\">The answer is YES</h1>");
                else if (yes < no)
                    out.println("<br><br><h1 class=\"display-5\">The answer is NO</h1>");
                else
                    out.println("<br><br><h1 class=\"display-5\">It is a TIE!!!</h1>");

                out.print("<br> <br><form action=\"ShowQuestion\">\r\n"
                        + "<input type=\"submit\" value=\"Go Back\" class=\"btn btn-warning btn-lg\" style=\"width: 100px;margin-left: 50px;\">\r\n"
                        + "</form>\r\n" + "</body>\r\n" + "</html>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            response.sendRedirect("login.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
