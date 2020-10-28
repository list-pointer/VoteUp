package com.admins.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/adminlogout")
public class AdminLogout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("a_uname");
        request.getSession().removeAttribute("wrongCredentials");
        request.getSession().removeAttribute("quesInsertOk");
        request.getSession().removeAttribute("quesInsertFail");
        request.getSession().invalidate();
        response.sendRedirect("adminlogin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("a_uname");
        request.getSession().removeAttribute("wrongCredentials");
        request.getSession().removeAttribute("quesInsertOk");
        request.getSession().removeAttribute("quesInsertFail");
        request.getSession().invalidate();
        response.sendRedirect("adminlogin.jsp");
    }
}
