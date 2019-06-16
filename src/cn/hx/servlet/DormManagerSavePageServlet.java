package cn.hx.servlet;

import cn.hx.dao.dormmanagerDao;
import cn.hx.pojo.DormBuild;
import cn.hx.pojo.DormManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DormManagerSavePageServlet", value = "/dormManSavePage")
public class DormManagerSavePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String site = request.getParameter("site");
        String dormManId = request.getParameter("dormManId");                   //用于修改

        dormmanagerDao d = new dormmanagerDao();
        DormManager dormManager = d.getDormManById(dormManId);
        List<DormBuild> dormBuildList = d.getDormBuildList();
        HttpSession session = request.getSession();
        session.setAttribute("mainPage", "admin/" + site + ".jsp");
        session.setAttribute("dormManId", dormManId);
        session.setAttribute("dormManager", dormManager);
        session.setAttribute("dormBuildList", dormBuildList);
        response.sendRedirect("mainAdmin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
