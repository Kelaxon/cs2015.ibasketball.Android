package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Gameinfo;
import JDBC.Beans.Managerinfo;
import JDBC.DBConnection;
import mapper.GameinfoMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 *      modify by 莫林立   complete
 * DAO实现类
 */

public class GameinfoMapperImpl implements GameinfoMapper {
    @Override
    public boolean add(Gameinfo gameinfo) {

        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from gameinfo where game_id =?";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,gameinfo.getGameId());
            //执行
            rs=ps.executeQuery();

            if (!rs.next()){        //没有相应记录

                String sql_2 = "insert into gameinfo(game_id,game_time,game_location,game_team1,game_team2,game_result)" +
                        " values (?,?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1,gameinfo.getGameId());

                ps.setTimestamp(2,gameinfo.getGameTime());

                ps.setString(3,gameinfo.getGameLocation());

                ps.setString(4,gameinfo.getGameTeam1());

                ps.setString(5,gameinfo.getGameTeam2());

                ps.setString(6,gameinfo.getGameResult());
                //执行
                int r=ps.executeUpdate();

                return true;
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
        return false;           //主键冲突添加失败
    }


    @Override
    public boolean delete(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;
        try{

            conn = DBConnection.getConnection();

            String sql = "delete from gameinfo where game_id=? ";
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
    public boolean update(Gameinfo gameinfo) {
        Connection conn = null;

        PreparedStatement ps = null;
        try{

            conn = DBConnection.getConnection();

            String sql = "update  gameinfo " +
                    "set game_id=? , game_time=? , game_location=? , game_team1=? , game_team2=? , game_result=?" +
                    "where game_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,gameinfo.getGameId());

            ps.setTimestamp(2,gameinfo.getGameTime());

            ps.setString(3,gameinfo.getGameLocation());

            ps.setString(4,gameinfo.getGameTeam1());

            ps.setString(5,gameinfo.getGameTeam2());

            ps.setString(6,gameinfo.getGameResult());

            ps.setInt(7,gameinfo.getGameId());
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
    public Gameinfo findByGameinfoId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Gameinfo gameinfo=null;
        try{

            conn = DBConnection.getConnection();

            String sql = "select * from gameinfo where game_id =?";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if (rs.next()){             //如果返回实例
                gameinfo=new Gameinfo();

                gameinfo.setGameId(rs.getInt("game_id"));

                gameinfo.setGameTime(rs.getTimestamp("game_time"));

                gameinfo.setGameLocation(rs.getString("game_location"));

                gameinfo.setGameTeam1(rs.getString("game_team1"));

                gameinfo.setGameTeam2(rs.getString("game_team2"));

                gameinfo.setGameResult(rs.getString("game_result"));

            }

            return gameinfo;

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
    public List<Gameinfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Gameinfo> list=new ArrayList<Gameinfo>();

        try {
            conn = DBConnection.getConnection();

            String sql = "select * from gameinfo";

            st = conn.createStatement();
            //无参执行
            rs = st.executeQuery(sql);

            while (rs.next()){
                Gameinfo gameinfo=new Gameinfo();

                gameinfo.setGameId(rs.getInt("game_id"));

                gameinfo.setGameTime(rs.getTimestamp("game_time"));

                gameinfo.setGameLocation(rs.getString("game_location"));

                gameinfo.setGameTeam1(rs.getString("game_team1"));

                gameinfo.setGameTeam2(rs.getString("game_team2"));

                gameinfo.setGameResult(rs.getString("game_result"));

                list.add(gameinfo);
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
