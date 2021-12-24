package the_first.demo.model;

public class User {

    private int id;

    private String stunum;

    private String stuphone;

    private String stupwd;

    private String stuemail;

    private String salt;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", stunum='" + stunum + '\'' +
                ", stuphone='" + stuphone + '\'' +
                ", stupwd='" + stupwd + '\'' +
                ", stuemail='" + stuemail + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStunum() {
        return stunum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public String getStuphone() {
        return stuphone;
    }

    public void setStuphone(String stuphone) {
        this.stuphone = stuphone;
    }

    public String getStupwd() {
        return stupwd;
    }

    public void setStupwd(String stupwd) {
        this.stupwd = stupwd;
    }

    public String getStuemail() {
        return stuemail;
    }

    public void setStuemail(String stuemail) {
        this.stuemail = stuemail;
    }

}
