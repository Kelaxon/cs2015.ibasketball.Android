package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Usercollectnews;
import JDBC.Beans.Userinfo;
import JDBC.DBConnection;
import mapper.UsercollectnewMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 *      modify by 莫林立
 * DAO实现类
 */

public class UsercollectnewMapperImpl implements UsercollectnewMapper {
    @Override
    public boolean add(Usercollectnews usercollectnews) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from usercollectnews where ucn_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, usercollectnews.getUcnId());
            //执行
            rs = ps.executeQuery();

            if(!rs.next()){
                String sql_2 = "insert into usercollectnews(ucn_id,user_id,news_id,collection_time)" +
                        " values (?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1, usercollectnews.getUcnId());

                ps.setInt(2, usercollectnews.getUserId());

                ps.setInt(3, usercollectnews.getNewsId());

                ps.setTimestamp(4, usercollectnews.getCollectionTime());
                //执行
                int r=ps.executeUpdate();

                return true;
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

            String sql = "delete from usercollectnews where ucn_id=? ";
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
    public boolean update(Usercollectnews usercollectnews) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "update  usercollectnews " +
                    "set ucn_id=? , user_id=? , news_id=? , collection_time=? " +
                    "where ucn_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, usercollectnews.getUcnId());

            ps.setInt(2, usercollectnews.getUserId());

            ps.setInt(3, usercollectnews.getNewsId());

            ps.setTimestamp(4, usercollectnews.getCollectionTime());
            //指定ID
            ps.setInt(5, usercollectnews.getUcnId());
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
    public Usercollectnews findByUsercollectnewId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Usercollectnews usercollectnews =null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from usercollectnews where ucn_id =?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if(rs.next()){
                usercollectnews =new Usercollectnews();

                usercollectnews.setUcnId(rs.getInt("ucn_id"));

                usercollectnews.setUcnId(rs.getInt("user_id"));

                usercollectnews.setNewsId(rs.getInt("news_id"));

                usercollectnews.setCollectionTime(rs.getTimestamp("collection_time"));

            }

            return usercollectnews;
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
    public List<Usercollectnews> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Usercollectnews> list=new ArrayList<Usercollectnews>();
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from usercollectnews";

            st = conn.createStatement();
            //无参执行
            rs=st.executeQuery(sql);

            while (rs.next()){
                Usercollectnews usercollectnews=new Usercollectnews();

                usercollectnews.setUcnId(rs.getInt("ucn_id"));

                usercollectnews.setUcnId(rs.getInt("user_id"));

                usercollectnews.setNewsId(rs.getInt("news_id"));

                usercollectnews.setCollectionTime(rs.getTimestamp("collection_time"));

                list.add(usercollectnews);
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
