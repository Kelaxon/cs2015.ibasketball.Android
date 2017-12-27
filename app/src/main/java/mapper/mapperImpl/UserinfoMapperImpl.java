package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import JDBC.Beans.Userinfo;
import JDBC.DBConnection;
import mapper.UserinfoMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 * userinfo写一个判断用户password是否正确的
 *
 *    modify by 莫林立
 * DAO实现类
 */

public class UserinfoMapperImpl implements UserinfoMapper {
    @Override
    public boolean add(Userinfo userinfo) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from usercollectnews where ucn_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, userinfo.getUserId());
            //执行
            rs = ps.executeQuery();

            if(!rs.next()){
                String sql_2 = "insert into userinfo(user_id,user_name,user_password,user_addr,user_email,user_tel,user_avatar,user_truname,user_intro)" +
                        " values (?,?,?,?,?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1,userinfo.getUserId());

                ps.setString(2,userinfo.getUserName());

                ps.setString(3,userinfo.getUserPassword());

                ps.setString(4,userinfo.getUserAddr());

                ps.setString(5,userinfo.getUserEmail());

                ps.setString(6,userinfo.getUserTel());

                ps.setString(7,userinfo.getUserAvatar());

                ps.setString(8,userinfo.getUserTruname());

                ps.setString(9,userinfo.getUserIntro());
                //执行
                int r=ps.executeUpdate();

                return  true;
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                DBConnection.dbClose(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "delete from userinfo where user_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            int r=ps.executeUpdate();

            return true;

        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                ps.close();

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean update(Userinfo userinfo) {

        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "update  userinfo " +
                    "set user_id=? user_name=? user_password=? user_addr=? user_email=? user_tel=? user_avatar=? user_truname=? user_intro=? " +
                    "where user_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,userinfo.getUserId());

            ps.setString(2,userinfo.getUserName());

            ps.setString(3,userinfo.getUserPassword());

            ps.setString(4,userinfo.getUserAddr());

            ps.setString(5,userinfo.getUserEmail());

            ps.setString(6,userinfo.getUserTel());

            ps.setString(7,userinfo.getUserAvatar());

            ps.setString(8,userinfo.getUserTruname());

            ps.setString(9,userinfo.getUserIntro());
            //指定ID
            ps.setInt(10,userinfo.getUserId());
            //执行
            int r=ps.executeUpdate();

            return true;

        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                ps.close();

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Userinfo findByUserinfoId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Userinfo userinfo=null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from userinfo where user_id =?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if (rs.next()){
                userinfo=new Userinfo();

                userinfo.setUserId(rs.getInt("user_id"));

                userinfo.setUserName(rs.getString("user_name"));

                userinfo.setUserPassword(rs.getString("user_password"));

                userinfo.setUserAddr(rs.getString("user_addr"));

                userinfo.setUserEmail(rs.getString("user_email"));

                userinfo.setUserTel(rs.getString("user_tel"));

                userinfo.setUserAvatar(rs.getString("user_avatar"));

                userinfo.setUserTruname(rs.getString("user_truname"));

                userinfo.setUserIntro(rs.getString("user_intro"));
            }

            return userinfo;

        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                DBConnection.dbClose(conn,ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public List<Userinfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Userinfo> list=null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from userinfo";

            st = conn.createStatement();
            //无参直接执行
            rs=st.executeQuery(sql);

            while (rs.next()){
                Userinfo userinfo=new Userinfo();

                userinfo.setUserId(rs.getInt("user_id"));

                userinfo.setUserName(rs.getString("user_name"));

                userinfo.setUserPassword(rs.getString("user_password"));

                userinfo.setUserAddr(rs.getString("user_addr"));

                userinfo.setUserEmail(rs.getString("user_email"));

                userinfo.setUserTel(rs.getString("user_tel"));

                userinfo.setUserAvatar(rs.getString("user_avatar"));

                userinfo.setUserTruname(rs.getString("user_truname"));

                userinfo.setUserIntro(rs.getString("user_intro"));

                list.add(userinfo);
            }

            return list;
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                rs.close();
                st.close();
               conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Userinfo> findByName(String name) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        List<Userinfo> list=null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from userinfo where user_id like ?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置模糊参数
            ps.setString(1,"%"+name+"%");
            //执行
            rs=ps.executeQuery();

            while (rs.next()){
                Userinfo userinfo=new Userinfo();

                userinfo.setUserId(rs.getInt("user_id"));

                userinfo.setUserName(rs.getString("user_name"));

                userinfo.setUserPassword(rs.getString("user_password"));

                userinfo.setUserAddr(rs.getString("user_addr"));

                userinfo.setUserEmail(rs.getString("user_email"));

                userinfo.setUserTel(rs.getString("user_tel"));

                userinfo.setUserAvatar(rs.getString("user_avatar"));

                userinfo.setUserTruname(rs.getString("user_truname"));

                userinfo.setUserIntro(rs.getString("user_intro"));

                list.add(userinfo);
            }

            return list;
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                DBConnection.dbClose(conn,ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean userLogin(String name, String password) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from userinfo where user_name = ?";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setString(1,name);
            //执行
            rs=ps.executeQuery();

            while (rs.next()){
                String getPassword=rs.getString("user_password");

                if(getPassword==password){
                    return true;
                }
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            try {
                DBConnection.dbClose(conn,ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
