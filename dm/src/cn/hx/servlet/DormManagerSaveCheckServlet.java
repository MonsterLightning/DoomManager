package cn.hx.servlet;

import cn.hx.dao.dormmanagerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DormManagerSaveServlet", value = "/dormManSaveCheck")
public class DormManagerSaveCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String site = request.getParameter("site");
        String dormManId = request.getParameter("dormManId");               //识别类型Id
        dormmanagerDao d = new dormmanagerDao();//用于修改
        String dormManId_s = request.getParameter("dormManId");             //参数Id

        String managerName = request.getParameter("managerName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");
        int dormBuildId = Integer.parseInt(request.getParameter("dormBuildId") == null ? "0" : request.getParameter("dormBuildId"));
        if (dormManId != null && dormManId!="") {
            //保存更新操作
            int i = d.updateDormManager(dormManId_s, managerName, password, sex, tel, dormBuildId);
            if (i == 1) {
                request.getRequestDispatcher("/dormManager?site=dormManager").forward(request, response);
            } else {
                session.setAttribute("error", "请核对信息后重新提交!");
            }

        }else{
            //添加
            int i = d.insertDormManager(dormManId_s, managerName, password, sex, tel, dormBuildId);
            if (i == 1) {
                request.getRequestDispatcher("/dormManager?site=dormManager").forward(request, response);
            } else {
                session.setAttribute("error", "请核对信息后重新提交!");
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
