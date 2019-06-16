package cn.hx.servlet;

import cn.hx.dao.dormmanagerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DormManagerDeleteServlet", value = "/dormManagerDel")
public class DormManagerDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dormManId = request.getParameter("dormManId");
        dormmanagerDao d = new dormmanagerDao();
        HttpSession session = request.getSession();
        int i = d.deleteDormManager(dormManId);
        if (i == 1) {
            request.getRequestDispatcher("/dormManager?site=dormManager").forward(request, response);
        } else {
            session.setAttribute("error", "操作遇到错误，请重试!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
