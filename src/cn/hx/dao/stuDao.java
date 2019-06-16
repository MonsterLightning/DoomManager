package cn.hx.dao;

import cn.hx.pojo.DormManager;
import cn.hx.pojo.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stuDao {
    DBConnect db = new DBConnect();

    public List<Student> stuLogin(String stuName, String password) {
        DBConnect db = new DBConnect();
        String sql = "select * from t_student where stuName='" + stuName + "' and password='" + password + "'";
        ResultSet rs = db.executeQuery(sql);
        try {
            if (rs.next()) {
                ArrayList<Student> currentUser = new ArrayList<Student>();
                Student stu = new Student();
                stu.setStudentId(rs.getInt(1));
                stu.setStuName(rs.getString(2));
                stu.setPassword(rs.getString(3));
                currentUser.add(stu);
                return currentUser;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}