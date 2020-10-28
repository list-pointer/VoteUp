package Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
@WebServlet("/ShowQuestion")
public class ShowQuestion extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //To tell browser to not store cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-validate"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setHeader("Expires", "0"); //Proxy server

        if (request.getSession().getAttribute("login") == "true") {
            PrintWriter out = response.getWriter();
            out.print("<!DOCTYPE html>\r\n"
                    + "<html>\r\n"
                    + "<head>\r\n"
                    + "<meta charset=\"UTF-8\">\r\n"
                    + "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">\r\n"
                    + "<meta content=\"ie=edge\" http-equiv=\"X-UA-Compatible\">\r\n"
                    + "<title>VoteUp Question Page</title>\r\n"
                    + "<link\r\n"
                    + "	href=\"https://fonts.googleapis.com/css?family=Karla:400,700&display=swap\"\r\n"
                    + "	rel=\"stylesheet\">\r\n"
                    + "<link\r\n"
                    + "	href=\"https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css\"\r\n"
                    + "	rel=\"stylesheet\">\r\n"
                    + "<link\r\n"
                    + "	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\"\r\n"
                    + "	rel=\"stylesheet\">\r\n"
                    + "<link href=\"assets/css/signUp.css\" rel=\"stylesheet\">\r\n"
                    + "</head>\r\n"
                    + "<body class=\"jumbotron\" style=\"margin-bottom: -50px;\">\r\n"
                    + "\r\n"
                    + "	<nav class=\"navbar navbar-light bg-light\" style=\"margin-top: -30px;\">\r\n"
                    + "		<a class=\"display-4\">VoteUp</a>\r\n"
                    + "		<form class=\"form-inline\" method=\"post\">\r\n"
                    + "			<button class=\"btn btn-outline-danger my-2 my-sm-0\" name=\"result\"\r\n"
                    + "				type=\"submit\" onclick=\"form.action='result';\">Show Result</button> &emsp; \r\n"
                    + "			<button class=\"btn btn-outline-danger my-2 my-sm-0\" name=\"action\"\r\n"
                    + "				type=\"submit\" onclick=\"form.action='logout';\">Logout</button>\r\n"

                    + "		</form>\r\n"
                    + "	</nav>\r\n"
                    + "\r\n"
                    + "	<div>\r\n"
                    + "		<br><br><h1 class=\"display-5\">Cast Your Vote!</h1>\r\n"
                    + "		<p class=\"lead\" style=\"margin-top: 30px\">\r\n"
                    + "		<h2>Q.Should we have Stress Buster Event Next Week?.</h2>\r\n"
                    + "		</p>\r\n"
                    + "		<hr class=\"my-4\">\r\n"
                    + "		<form action=\"question\" method=post>\r\n"
                    + "			<input name=\"action\" type=\"submit\" value=\"YES\" class=\"btn btn-primary btn-lg\" style=\"width: 100px;margin-left: 2px;\"> \r\n"
                    + "			<input name=\"action\" type=\"submit\" value=\"NO\" class=\"btn btn-warning btn-lg\"  style=\"width: 100px;margin-left: 50px;\"><br><br>\r\n"
                    + "		</form>\r\n");

            if (request.getSession().getAttribute("click") != null) {
                out.print("<br> <br> <h2> You clicked " + request.getSession().getAttribute("click") + "</h2>");
                request.getSession().removeAttribute("click");
            }
            out.print("	</div>\r\n"
                    + "</body>\r\n"
                    + "</html>");
        } else
            response.sendRedirect("login.html");
    }

}
