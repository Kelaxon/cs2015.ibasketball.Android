package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import JDBC.Beans.Managerinfo;
import JDBC.Beans.Newsinfo;
import JDBC.DBConnection;
import mapper.ManagerinfoMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 * <p>
 * modify by 莫林立
 * DAO实现类
 */

public class ManagerinfoMapperImpl implements ManagerinfoMapper {
    @Override
    public boolean add(Managerinfo managerinfo) {

        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from managerinfo where mag_id =?";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setString(1, managerinfo.getMagId());
            //执行
            rs = ps.executeQuery();

            if (!rs.next()) {               //数据表不存在主键重复

                String sql_2 = "insert into managerinfo(mag_id,mag_password,mag_name)" +
                        " values (?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setString(1, managerinfo.getMagId());

                ps.setString(2, managerinfo.getMagPassword());

                ps.setString(3, managerinfo.getMagName());
                //执行
                int r = ps.executeUpdate();

                return true;
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                DBConnection.dbClose(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "delete from managerinfo where mag_id=? ";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setString(1, id);
            //执行
            int r = ps.executeUpdate();

            return true;
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                ps.close();

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean update(Managerinfo managerinfo) {
        Connection conn = null;

        PreparedStatement ps = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "update  managerinfo " +
                    "set mag_id=? mag_password=? mag_name=? " +
                    "where mag_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setString(1,managerinfo.getMagId());

            ps.setString(2,managerinfo.getMagPassword());

            ps.setString(3,managerinfo.getMagName());

            ps.setString(4,managerinfo.getMagId());
            //执行
            int r=ps.executeUpdate();

            return  true;

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
    public Managerinfo findByManagerinfoId(String id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Managerinfo managerinfo=null;

        try {
            conn = DBConnection.getConnection();

            String sql = "select * from managerinfo where mag_id =?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setString(1,id);
            //执行
            rs=ps.executeQuery();

            if(rs.next()){          //如果返回实例
                managerinfo=new Managerinfo();

                managerinfo.setMagId(rs.getString("mag_id"));

                managerinfo.setMagPassword(rs.getString("mag_password"));

                managerinfo.setMagName(rs.getString("mag_name"));
            }

            return managerinfo;

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
    public List<Managerinfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Managerinfo> list=null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from managerinfo";
            //准备语句
            st = conn.createStatement();
            //无参执行
            rs = st.executeQuery(sql);

            while (rs.next()){
                Managerinfo managerinfo=new Managerinfo();

                managerinfo.setMagId(rs.getString("mag_id"));

                managerinfo.setMagPassword(rs.getString("mag_password"));

                managerinfo.setMagName(rs.getString("mag_name"));

                list.add(managerinfo);
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
