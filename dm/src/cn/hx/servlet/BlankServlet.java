package cn.hx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BlankServlet", value = "/blank")
public class BlankServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");

        if (userType.equals("admin")) {
            session.setAttribute("mainPage", "admin/blank.jsp");
            response.sendRedirect("mainAdmin.jsp");
        }
        if (userType.equals("dormManager")) {
            session.setAttribute("mainPage", "dormManager/blank.jsp");
            response.sendRedirect("mainManager.jsp");
        }
        if (userType.equals("student")) {
            session.setAttribute("mainPage", "student/blank.jsp");
            response.sendRedirect("mainStudent.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
