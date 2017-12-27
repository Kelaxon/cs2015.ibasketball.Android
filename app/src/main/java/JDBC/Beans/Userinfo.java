package JDBC.Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import JDBC.DBConnection;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class Userinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private String userPassword;
    private String userAddr;
    private String userEmail;
    private String userTel;
    private String userAvatar;
    private String userTruname;
    private String userIntro;

    static public Userinfo getByName(String userName) {
        Userinfo userinfo = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from userinfo where user_name =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                userinfo = new Userinfo();  // 必不可少
                userinfo.setUserId(rs.getInt("user_id"));
                userinfo.setUserName(rs.getString("user_name"));
                userinfo.setUserTruname(rs.getString("user_truname"));
            }

            return userinfo;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.dbClose(conn, ps, rs); // 释放数据库连接资源
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static public boolean isExists(String userName, String userPassword) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "select * from userinfo where user_name =? and user_password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.dbClose(conn, ps, rs); // 释放数据库连接资源
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserTruname() {
        return userTruname;
    }

    public void setUserTruname(String userTruname) {
        this.userTruname = userTruname;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

}
