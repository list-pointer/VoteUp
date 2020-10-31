<%@page import="java.sql.ResultSet" %>
<%@page import="com.mysql.cj.protocol.Resultset" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>VoteUp Admin Result Page</title>
    <link
            href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap"
            rel="stylesheet">
    <link
            href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css"
            rel="stylesheet">
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            rel="stylesheet">
    <link href="assets/css/signUp.css" rel="stylesheet">
</head>


<body class="jumbotron" style="margin-bottom: -50px;">
<nav class="navbar navbar-light bg-light" style="margin-top: -30px;">
    <a class="navbar-brand"><b><h1>VoteUp</h1></b></a>
    <form class="form-inline">
        <button class="btn btn-outline-danger my-2 my-sm-0" name="result"
                type="submit" onclick="form.action='adminindex.jsp';">Add
            Questions
        </button>
        &emsp;
        <button class="btn btn-outline-danger my-2 my-sm-0" name="action"
                type="submit" onclick="form.action='adminlogout';">Logout
        </button>
    </form>
</nav>
<div><br>
    <%
        //To tell browser to not store cache for this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-validate"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setHeader("Expires", "0"); //Proxy server

        if (session.getAttribute("a_uname") == null)
            response.sendRedirect("adminlogin.jsp");
        //if(session.getAttribute("quesNO").equals("0"))
        //session.setAttribute("quesNO", "1");
        //else
        //session.setAttribute("quesNO", Integer.toString((Integer.parseInt(session.getAttribute("quesNO").toString())+1)));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_voteup", "root", "3569");
            System.out.println("** Connection has been established **");

            PreparedStatement statement = connection.prepareStatement("select count(*) from questiontable");
            ResultSet rs = statement.executeQuery();
            rs.next();

            for (int i = 0; i <= rs.getInt(1); i++) {
                PreparedStatement statement1 = connection.prepareStatement("select * from answertable where q_id=" + i);
                ResultSet tablers = statement1.executeQuery();
                ResultSet questionResultSet = connection.prepareStatement("select question from questiontable where q_id=" + i)
                        .executeQuery();
                if (questionResultSet.next()) {
                    out.print("<h4> Q." + i + " " + questionResultSet.getString(1) + "");
                    ResultSet yesVoteResultSet = connection
                            .prepareStatement("select count(*) from answertable where q_id=" + i + " and vote=1")
                            .executeQuery();
                    ResultSet noVoteResultSet = connection
                            .prepareStatement("select count(*) from answertable where q_id=" + i + " and vote=0")
                            .executeQuery();
                    yesVoteResultSet.next();
                    noVoteResultSet.next();
                    int yes = yesVoteResultSet.getInt(1);
                    int no = noVoteResultSet.getInt(1);
                    yesVoteResultSet.close();
                    noVoteResultSet.close();

                    if (tablers.next()) {
                        out.print("&nbsp;&nbsp;  <p align=\"right\"> Overall answer = ");
                        if (yes > no)
                            out.print("Yes");
                        else if (yes < no)
                            out.print("No");
                        else
                            out.print("Tie");


                        out.print("</p> </h4> <table>");
                        do {

                            int user = tablers.getInt(1);
                            int question = tablers.getInt(2);

                            ResultSet userResultSet = connection.prepareStatement("select u_name from users where u_id=" + user).executeQuery();

                            if (userResultSet.next()) {
                                out.print("<tr> <td>");
                                out.print("<h4> Vote = ");

                                //tablers.getInt(3)
                                if (tablers.getInt(3) == 1)
                                    out.print("Yes");
                                else if (tablers.getInt(3) == 0)
                                    out.print("No");

                                out.print("</h4> </td> <td> <h4> &emsp; User = " + userResultSet.getString(1));
                                out.print(" </h4> </td> </tr>");
                            }
                        } while (tablers.next());
                    } else
                        out.print("<h4> <p align=\"right\"> <font color=\"red\"> No one has answered yet </font> </p> </h4>");
                    out.print("</table> <hr class=\"my-4\">");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

</div>
</body>
</html>