package cn.hx.dao;

import cn.hx.pojo.DormBuild;
import cn.hx.pojo.DormManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dormmanagerDao {
    DBConnect db = new DBConnect();

    //验证登录
    public List<DormManager> managerLogin(String managerName, String password) {
        DBConnect db = new DBConnect();
        String sql = "select * from t_dormmanager where managerName='" + managerName + "' and password='" + password + "'";
        ResultSet rs = db.executeQuery(sql);
        try {
            if (rs.next()) {
                ArrayList<DormManager> currentUser = new ArrayList<DormManager>();
                DormManager dm = new DormManager();
                dm.setDormManId(rs.getString(1));
                dm.setManagerName(rs.getString(2));
                dm.setPassword(rs.getString(3));
                currentUser.add(dm);
                return currentUser;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //根据id查数据
    public DormManager getDormManById(String dormManId) {
        DBConnect db = new DBConnect();
        String sql = "select * from t_dormmanager where dormManId=" + dormManId + "";
        ResultSet rs = db.executeQuery(sql);
        try {
            if (rs.next()) {
                DormManager dm = new DormManager();
                dm.setDormManId(rs.getString(1));
                dm.setManagerName(rs.getString(2));
                dm.setPassword(rs.getString(3));
                dm.setDormBuildId(rs.getInt(4));
                dm.setSex(rs.getString(5));
                dm.setTel(rs.getString(6));
                return dm;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //获取所有宿舍楼信息
    public List<DormBuild> getDormBuildList() {
        DBConnect db = new DBConnect();
        String sql = "select * from t_dormbuild";
        ResultSet rs = db.executeQuery(sql);
        ArrayList<DormBuild> dormBuildList = new ArrayList<DormBuild>();
        try {
            while (rs.next()) {
                DormBuild dormBuild = new DormBuild();
                dormBuild.setDormBuildId(rs.getString(1));
                dormBuild.setDormBuildName(rs.getString(2));
                dormBuild.setDormBuildDetail(rs.getString(3));
                dormBuildList.add(dormBuild);
            }
            return dormBuildList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //插入数据
    public int insertDormManager(String dormManId_s, String managerName, String password, String sex, String tel, int dormBuildId) {
        DBConnect db = new DBConnect();
        String sql = "insert into t_dormmanager (managerName,password,sex,tel,dormBuildId) values('" + managerName + "','" + password + "','" + sex + "','" + tel + "','" + dormBuildId + "')";
        int result = db.executeUpdate(sql);
        return result;
    }

    //更新数据
    public int updateDormManager(String dormManId_s, String managerName, String password, String sex, String tel, int dormBuildId) {
        DBConnect db = new DBConnect();
        String sql = "update t_dormmanager set managerName='" + managerName + "',password='" + password + "',sex='" + sex + "',tel='" + tel + "',dormBuildId='" + dormBuildId + "' where dormManId=" + dormManId_s;
        int result = db.executeUpdate(sql);
        return result;
    }

    //删除数据
    public int deleteDormManager(String dormManId) {
        DBConnect db = new DBConnect();
        String sql = "delete from t_dormmanager where dormManId=" + dormManId;
        int result = db.executeUpdate(sql);
        return result;
    }
}