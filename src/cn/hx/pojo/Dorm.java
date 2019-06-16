package cn.hx.pojo;

public class Dorm {
    private int dormId;
    private int dormBuildId;
    private String dormName;
    private String dormType;
    private String tel;

    public Dorm() {

    }

    public int getDormId() {
        return dormId;
    }

    public void setDormId(int dormId) {
        this.dormId = dormId;
    }

    public int getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(int dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getDormType() {
        return dormType;
    }

    public void setDormType(String dormType) {
        this.dormType = dormType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
