package cn.hx.servlet;

import cn.hx.dao.adminDao;
import cn.hx.dao.dormmanagerDao;
import cn.hx.dao.stuDao;
import cn.hx.pojo.Admin;
import cn.hx.pojo.DormManager;
import cn.hx.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        //管理员身份登录
        if (userType.equals("admin")) {
            HttpSession session = request.getSession();
            adminDao ad = new adminDao();
            List<Admin> currentUser = ad.adminLogin(userName, password);
            if (currentUser != null) {

                session.setAttribute("currentUser", currentUser);
                session.setAttribute("mainPage", "admin/blank.jsp");
                session.setAttribute("userType",userType);
                response.sendRedirect("mainAdmin.jsp");
            } else {
                response.sendRedirect("login.jsp");
                session.setAttribute("error", "请核对用户名和密码!");
            }
        }
        //宿舍管理员身份登录
        if (userType.equals("dormManager")) {
            HttpSession session = request.getSession();
            dormmanagerDao dmd = new dormmanagerDao();
            List<DormManager> dmcurrentUser = dmd.managerLogin(userName, password);
            if (dmcurrentUser != null) {
                session.setAttribute("currentUser", dmcurrentUser);
                session.setAttribute("mainPage", "dormManager/blank.jsp");
                session.setAttribute("userType",userType);
                response.sendRedirect("mainManager.jsp");
            } else {
                response.sendRedirect("login.jsp");
                session.setAttribute("error", "请核对用户名和密码!");
            }
        }
        //学生身份登录
        if (userType.equals("student")) {
            HttpSession session = request.getSession();
            stuDao stu = new stuDao();
            List<Student> stucurrentUser = stu.stuLogin(userName, password);
            if (stucurrentUser != null) {
                session.setAttribute("currentUser", stucurrentUser);
                session.setAttribute("mainPage", "student/blank.jsp");
                session.setAttribute("userType",userType);
                response.sendRedirect("mainStudent.jsp");
            } else {
                response.sendRedirect("login.jsp");
                session.setAttribute("error", "请核对用户名和密码!");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
