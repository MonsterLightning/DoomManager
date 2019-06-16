package cn.hx.dao;

import cn.hx.pojo.Admin;
import cn.hx.pojo.DormManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminDao {

    public List<Admin> adminLogin(String userName, String password) {
        DBConnect db = new DBConnect();
        String sql = "select * from t_admin where userName='" + userName + "' and password='" + password + "'";
        ResultSet rs = db.executeQuery(sql);
        try {
            if (rs.next()) {
                ArrayList<Admin> currentUser = new ArrayList<Admin>();
                Admin a = new Admin();
                a.setAdminId(rs.getInt(1));
                a.setUserName(rs.getString(2));
                a.setPassword(rs.getString(3));
                currentUser.add(a);
                return currentUser;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //记录总长
    public int getManagersListLen(String searchType, String s_dormManagerText) {
        DBConnect db = new DBConnect();
        String sql = "select dormManId,managerName,password,a.dormBuildId,sex,tel,dormBuildName " +
                "from t_dormmanager a join t_dormbuild b on a.dormBuildId=b.dormBuildId " +
                " where 1 ";

        //条件搜索
        if (s_dormManagerText != null && s_dormManagerText != "") {
            if (searchType.equals("dormBuildId")) {
                sql = sql + "and b.dormBuildId=any(select dormBuildId from t_dormbuild where dormBuildName like '%" + s_dormManagerText + "%')";
            } else {
                sql = sql + "and " + searchType + " like '%" + s_dormManagerText + "%'";
            }
        }

        ResultSet rs = db.executeQuery(sql);
        int total = 0;
        try {
            while (rs.next()) {
                total++;
            }
            return total;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    //分页+条件搜索
    public List<DormManager> getManagersListPage(int currentPage, int pageSize, String searchType, String s_dormManagerText) {
        DBConnect db = new DBConnect();
        String temp = "";
        //条件搜索
        if (s_dormManagerText != null && s_dormManagerText != "") {
            if (searchType.equals("dormBuildId")) {
                temp = "and b.dormBuildId=any(select dormBuildId from t_dormbuild where dormBuildName like '%" + s_dormManagerText + "%')";
            } else {
                temp = "and " + searchType + " like '%" + s_dormManagerText + "%'";
            }
        }
        //sql语句
        String sql = "select dormManId,managerName,password,a.dormBuildId,sex,tel,dormBuildName " +
                "from t_dormmanager a join t_dormbuild b on a.dormBuildId=b.dormBuildId " +
                " where 1 " + temp +
                "order by dormManId limit " + (currentPage - 1) * pageSize + "," + pageSize;

        ResultSet rs = db.executeQuery(sql);
        ArrayList<DormManager> dmList = new ArrayList<DormManager>();
        try {
            while (rs.next()) {
                DormManager dm = new DormManager();
                dm.setDormManId(rs.getString(1));
                dm.setManagerName(rs.getString(2));
                dm.setPassword(rs.getString(3));
                dm.setDormBuildId(rs.getInt(4));
                dm.setSex(rs.getString(5));
                dm.setTel(rs.getString(6));
                dm.setDormBuildName(rs.getString(7));
                dmList.add(dm);
            }
            return dmList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}