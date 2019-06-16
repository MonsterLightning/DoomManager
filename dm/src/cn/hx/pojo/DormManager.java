package cn.hx.pojo;

public class DormManager {
    private String dormManId;
    private String managerName;
    private String password;
    private int dormBuildId;
    private String sex;
    private String tel;
    private String dormBuildName;

    public DormManager() {

    }

    public DormManager(String managerName, String password) {
        this.managerName = managerName;
        this.password = password;
    }

    public String getDormManId() {
        return dormManId;
    }

    public void setDormManId(String dormManId) {
        this.dormManId = dormManId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }
}
