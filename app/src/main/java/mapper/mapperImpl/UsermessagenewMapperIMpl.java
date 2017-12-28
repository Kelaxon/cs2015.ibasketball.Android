package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Userinfo;
import JDBC.Beans.Usermessagenews;
import JDBC.DBConnection;
import mapper.UsermessagenewsMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 *      modify by 莫林立
 * DAO实现类
 */

public class UsermessagenewMapperIMpl implements UsermessagenewsMapper {

    @Override
    public boolean add(Usermessagenews usermessagenews) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from usermessagenews where umn_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, usermessagenews.getUmnId());
            //执行
            rs = ps.executeQuery();

            if (!rs.next()){
                String sql_2 = "insert into usermessagen(umn_id,user_id,news_id,message_time,message_content)" +
                        " values (?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1,usermessagenews.getUmnId());

                ps.setInt(2,usermessagenews.getUserId());

                ps.setInt(3,usermessagenews.getNewsId());

                ps.setTimestamp(4,usermessagenews.getMessageTime());

                ps.setString(5,usermessagenews.getMessageContent());
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

            String sql = "delete from usermessagenews where umn_id=? ";
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
    public boolean update(Usermessagenews usermessagenews) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "update  usermessagenews " +
                    "set umn_id=? , user_id=? , news_id=? , message_time=? , message_content=? " +
                    "where umn_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,usermessagenews.getUmnId());

            ps.setInt(2,usermessagenews.getUserId());

            ps.setInt(3,usermessagenews.getNewsId());

            ps.setTimestamp(4,usermessagenews.getMessageTime());

            ps.setString(5,usermessagenews.getMessageContent());
            //指定ID
            ps.setInt(6,usermessagenews.getUmnId());
            //执行
            int r= ps.executeUpdate();

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
    public Usermessagenews findByUsermessagenewId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Usermessagenews usermessagenews=null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from usermessagenews where umn_id =?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if(rs.next()){
                usermessagenews=new Usermessagenews();

                usermessagenews.setUmnId(rs.getInt("umn_id"));

                usermessagenews.setUserId(rs.getInt("user_id"));

                usermessagenews.setNewsId(rs.getInt("news_id"));

                usermessagenews.setMessageTime(rs.getTimestamp("message_time"));

                usermessagenews.setMessageContent(rs.getString("message_content"));
            }

            return usermessagenews;
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
    public List<Usermessagenews> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Usermessagenews> list=new ArrayList<Usermessagenews>();
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from usermessagenews";

            st = conn.createStatement();
            //执行
            rs=st.executeQuery(sql);

            while (rs.next()){
                Usermessagenews usermessagenews=new Usermessagenews();

                usermessagenews.setUmnId(rs.getInt("umn_id"));

                usermessagenews.setUserId(rs.getInt("user_id"));

                usermessagenews.setNewsId(rs.getInt("news_id"));

                usermessagenews.setMessageTime(rs.getTimestamp("message_time"));

                usermessagenews.setMessageContent(rs.getString("message_content"));

                list.add(usermessagenews);
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
