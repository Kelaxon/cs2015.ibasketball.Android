package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import JDBC.Beans.Announceinfo;
import JDBC.Beans.Gameinfo;
import JDBC.DBConnection;
import mapper.AnnounceinfoMapper;


/**
 * Created by ChrisYoung on 2017/12/26.
 *
 *      modify by 莫林立
 * DAO实现类
 */

public class AnnounceinfoMapperImpl implements AnnounceinfoMapper {

    @Override
    public boolean add(Announceinfo announceinfo) {

        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {

            conn = DBConnection.getConnection();

            String sql = "select * from announceinfo where announce_id =?";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, announceinfo.getAnnounceId());
            //执行
            rs = ps.executeQuery();

            if (!rs.next()) {       //没有相应记录

                String sql_2 = "insert into announceinfo(announce_id,user_id,announce_title,announce_conten,announce_time) " +
                        "values (?,?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1, announceinfo.getAnnounceId());

                ps.setInt(2, announceinfo.getUserId());

                ps.setString(3, announceinfo.getAnnounceTitle());

                ps.setString(4, announceinfo.getAnnounceContent());

                ps.setTimestamp(5, announceinfo.getAnnounceTime());
                //执行
                int r = ps.executeUpdate(sql_2);

                    return true;

            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                DBConnection.dbClose(conn, ps, rs);       //释放连接资源

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;           //主键冲突无法添加
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {

            conn = DBConnection.getConnection();

            String sql = "delete from announceinfo where announce_id=? ";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            int r=ps.executeUpdate();

            return true;        //不管删除成功或失败都返回true

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

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
    public boolean update(Announceinfo announceinfo) {
        Connection conn = null;

        PreparedStatement ps = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "update  announceinfo " +
                    "set user_id=? announce_title=? announce_content=? announce_time=? " +
                    "where announce_id=? ";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,announceinfo.getUserId());

            ps.setString(2,announceinfo.getAnnounceTitle());

            ps.setString(3,announceinfo.getAnnounceContent());

            ps.setTimestamp(4,announceinfo.getAnnounceTime());

            ps.setInt(5,announceinfo.getAnnounceId());
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
    public Announceinfo findByAnnounceinfoId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Announceinfo announceinfo=null;

        try{
            conn = DBConnection.getConnection();

            String sql = "select * from announceinfo where announce_id =?";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if(rs.next()){
                announceinfo=new Announceinfo();

                announceinfo.setAnnounceId(rs.getInt("announce_id"));

                announceinfo.setAnnounceContent(rs.getString("announce_content"));

                announceinfo.setAnnounceTitle(rs.getString("announce_title"));

                announceinfo.setAnnounceTime(rs.getTimestamp("announce_time"));

                announceinfo.setUserId(rs.getInt("user_id"));
            }

            return announceinfo;

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
    public List<Announceinfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Announceinfo> list=null;

        try {
            conn = DBConnection.getConnection();

            String sql = "select * from announceinfo";

            st = conn.createStatement();
            //无参执行
            rs = st.executeQuery(sql);

            while (rs.next()){
                Announceinfo announceinfo=new Announceinfo();

                announceinfo.setAnnounceId(rs.getInt("announce_id"));

                announceinfo.setAnnounceContent(rs.getString("announce_content"));

                announceinfo.setAnnounceTitle(rs.getString("announce_title"));

                announceinfo.setAnnounceTime(rs.getTimestamp("announce_time"));

                announceinfo.setUserId(rs.getInt("user_id"));

                list.add(announceinfo);
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
}
