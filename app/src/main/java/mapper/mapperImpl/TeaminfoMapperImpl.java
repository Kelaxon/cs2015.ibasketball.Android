package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Teaminfo;
import JDBC.Beans.Usercollectnews;
import JDBC.DBConnection;
import mapper.TeaminfoMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 *  *      modify by 莫林立
 * DAO实现类
 */

public class TeaminfoMapperImpl implements TeaminfoMapper {
    @Override
    public boolean add(Teaminfo teaminfo) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from teaminfo where team_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, teaminfo.getTeamId());
            //执行
            rs = ps.executeQuery();

            if (!rs.next()){
                String sql_2 = "insert into teaminfo(team_id,team_name,team_time,team_coachers,team_players,team_logo,team_honors)" +
                        " values (?,?,?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1,teaminfo.getTeamId());

                ps.setString(2,teaminfo.getTeamName());

                ps.setTimestamp(3,teaminfo.getTeamTime());

                ps.setString(4,teaminfo.getTeamCoachers());

                ps.setString(5,teaminfo.getTeamPlayers());

                ps.setString(6,teaminfo.getTeamLogo());

                ps.setString(7,teaminfo.getTeamHonors());
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

            String sql = "delete from teaminfo where team_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            int r=ps.executeUpdate();

            return  true;

        }catch (Exception e) {

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
    public boolean update(Teaminfo teaminfo) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "update  teaminfo " +
                    "set team_id=? , team_name=? , team_time=? , team_coachers=? , team_players=? , team_logo=? , team_honors=? " +
                    "where team_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,teaminfo.getTeamId());

            ps.setString(2,teaminfo.getTeamName());

            ps.setTimestamp(3,teaminfo.getTeamTime());

            ps.setString(4,teaminfo.getTeamCoachers());

            ps.setString(5,teaminfo.getTeamPlayers());

            ps.setString(6,teaminfo.getTeamLogo());

            ps.setString(7,teaminfo.getTeamHonors());

            ps.setInt(8,teaminfo.getTeamId());
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
    public Teaminfo findByTeaminfoId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Teaminfo teaminfo=null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from teaminfo where team_id =?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if (rs.next()){                 //如果返回实例
                teaminfo=new Teaminfo();

                teaminfo.setTeamId(rs.getInt("team_id"));

                teaminfo.setTeamName(rs.getString("team_name"));

                teaminfo.setTeamTime(rs.getTimestamp("team_time"));

                teaminfo.setTeamCoachers(rs.getString("team_coachers"));

                teaminfo.setTeamPlayers(rs.getString("team_players"));

                teaminfo.setTeamLogo(rs.getString("team_logo"));

                teaminfo.setTeamHonors(rs.getString("team_honors"));

            }

            return teaminfo;
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
    public List<Teaminfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Teaminfo> list=new ArrayList<Teaminfo>();
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from teaminfo";

            st = conn.createStatement();
            //无参执行
            rs=st.executeQuery(sql);

            while (rs.next()){
                Teaminfo teaminfo=new Teaminfo();

                teaminfo.setTeamId(rs.getInt("team_id"));

                teaminfo.setTeamName(rs.getString("team_name"));

                teaminfo.setTeamTime(rs.getTimestamp("team_time"));

                teaminfo.setTeamCoachers(rs.getString("team_coachers"));

                teaminfo.setTeamPlayers(rs.getString("team_players"));

                teaminfo.setTeamLogo(rs.getString("team_logo"));

                teaminfo.setTeamHonors(rs.getString("team_honors"));

                list.add(teaminfo);
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
