package com.users.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/userlogout")
public class UserLogout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("uid");
        request.getSession().removeAttribute("quesNO");
        request.getSession().removeAttribute("u_name");
        request.getSession().removeAttribute("insertFailed");
        request.getSession().removeAttribute("insertSuccessful");
        request.getSession().removeAttribute("wrongCredentials");
        request.getSession().invalidate();
        response.sendRedirect("userlogin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("uid");
        request.getSession().removeAttribute("quesNO");
        request.getSession().removeAttribute("u_name");
        request.getSession().removeAttribute("insertFailed");
        request.getSession().removeAttribute("insertSuccessful");
        request.getSession().removeAttribute("wrongCredentials");
        request.getSession().invalidate();
        response.sendRedirect("userlogin.jsp");
    }
}
