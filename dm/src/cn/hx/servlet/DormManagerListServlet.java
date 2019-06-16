package cn.hx.servlet;

import cn.hx.dao.adminDao;
import cn.hx.pojo.DormManager;
import cn.hx.pojo.pageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DormManagerListServlet", value = "/dormManager")
public class DormManagerListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String s_dormManagerText = request.getParameter("s_dormManagerText");

        String site = request.getParameter("site");

        if (s_dormManagerText == null) {
            s_dormManagerText = "";
        }

        pageInfo page = new pageInfo();
        int currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));           //页码
        int pageSize = 5;             //设定每页大小

        adminDao ad = new adminDao();
        List<DormManager> dmList = ad.getManagersListPage(currentPage, pageSize, searchType, s_dormManagerText);
        int total = ad.getManagersListLen(searchType, s_dormManagerText);

        int totalPage = total / pageSize;               //计算总页数
        if (total % pageSize != 0) {
            totalPage++;
        }

        //页面元素
        String pageCode = request.getParameter("pageCode") == null ? "" : "";
        if (currentPage > 1) {
            pageCode = pageCode + "<a href='/dormManager?currentPage=" + (currentPage - 1) + "&site=dormManager&searchType=" + searchType + "&s_dormManagerText=" + s_dormManagerText + "'>上一页</a>";
        } else {
            pageCode = pageCode + "<a href='#'>上一页   </a>";
        }
        pageCode = pageCode + "当前第" + currentPage + "页,共" + totalPage + "页   ";
        if (currentPage < totalPage) {
            pageCode = pageCode + "<a href='/dormManager?currentPage=" + (currentPage + 1) + "&site=dormManager&searchType=" + searchType + "&s_dormManagerText=" + s_dormManagerText + "'>下一页</a>";
        } else {
            pageCode = pageCode + "<a href='#'>下一页</a>";
        }

        //设置分页值
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalPage(totalPage);

        HttpSession session = request.getSession();
        session.setAttribute("mainPage", "admin/" + site + ".jsp");
        session.setAttribute("dormManagerList", dmList);
        session.setAttribute("searchType", searchType);
        session.setAttribute("s_dormManagerText", s_dormManagerText);
        session.setAttribute("pageCode", pageCode);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("totalPage", totalPage);
        response.sendRedirect("mainAdmin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
