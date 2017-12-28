package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Scoreinfo;
import JDBC.Beans.Teaminfo;
import JDBC.DBConnection;
import mapper.ScoreinfoMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 *      modify by 莫林立
 * DAO实现类
 */

public class ScoreinfoMapperImpl implements ScoreinfoMapper {
    @Override
    public boolean add(Scoreinfo scoreinfo) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;
        try{
            conn = DBConnection.getConnection();

            String sql = "select * from scoreinfo where score_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, scoreinfo.getScoreId());
            //执行
            rs = ps.executeQuery();

            if (!rs.next()){                //数据表没有主键冲突
                String sql_2 = "insert into scoreinfo(score_id,score_ranking,team_id,score_wins,score_loses,score_ppg,score_plp,score_tg)" +
                        " values (?,?,?,?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1,scoreinfo.getScoreId());

                ps.setInt(2,scoreinfo.getScoreRanking());

                ps.setInt(3,scoreinfo.getTeamId());

                ps.setInt(4,scoreinfo.getScoreWins());

                ps.setInt(5,scoreinfo.getScoreLoses());

                ps.setFloat(6,scoreinfo.getScorePpg());

                ps.setFloat(7,scoreinfo.getScorePlp());

                ps.setInt(8,scoreinfo.getScoreTg());
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

            String sql = "delete from scoreinfo where score_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
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
    public boolean update(Scoreinfo scoreinfo) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "update  scoreinfo " +
                    "set score_id=? , score_ranking=? , team_id=? , score_wins=? , score_loses=? , score_ppg=? , score_plp=? , score_tg=?" +
                    "where score_id=? ";
            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,scoreinfo.getScoreId());

            ps.setInt(2,scoreinfo.getScoreRanking());

            ps.setInt(3,scoreinfo.getTeamId());

            ps.setInt(4,scoreinfo.getScoreWins());

            ps.setInt(5,scoreinfo.getScoreLoses());

            ps.setFloat(6,scoreinfo.getScorePpg());

            ps.setFloat(7,scoreinfo.getScorePlp());

            ps.setInt(8,scoreinfo.getScoreTg());

            ps.setInt(9,scoreinfo.getScoreId());
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
    public Scoreinfo findByScoreinfoId(Integer id) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Scoreinfo scoreinfo=null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from scoreinfo where score_id =?";

            //准备语句
            ps=conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            //执行
            rs=ps.executeQuery();

            if(rs.next()){               //如果返回实例
                scoreinfo=new Scoreinfo();

                scoreinfo.setScoreId(rs.getInt("score_id"));

                scoreinfo.setScoreRanking(rs.getInt("score_ranking"));

                scoreinfo.setTeamId(rs.getInt("team_id"));

                scoreinfo.setScoreWins(rs.getInt("score_wins"));

                scoreinfo.setScoreLoses(rs.getInt("score_loses"));

                scoreinfo.setScorePpg(rs.getFloat("score_ppg"));

                scoreinfo.setScorePlp(rs.getFloat("score_plp"));

                scoreinfo.setScoreTg(rs.getInt("score_tg"));
            }

            return scoreinfo;
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
    public List<Scoreinfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Scoreinfo> list=new ArrayList<Scoreinfo>();
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from scoreinfo";

            st = conn.createStatement();
            //无参执行
            rs=st.executeQuery(sql);

            while (rs.next()){
                Scoreinfo scoreinfo=new Scoreinfo();

                scoreinfo.setScoreId(rs.getInt("score_id"));

                scoreinfo.setScoreRanking(rs.getInt("score_ranking"));

                scoreinfo.setTeamId(rs.getInt("team_id"));

                scoreinfo.setScoreWins(rs.getInt("score_wins"));

                scoreinfo.setScoreLoses(rs.getInt("score_loses"));

                scoreinfo.setScorePpg(rs.getFloat("score_ppg"));

                scoreinfo.setScorePlp(rs.getFloat("score_plp"));

                scoreinfo.setScoreTg(rs.getInt("score_tg"));

                list.add(scoreinfo);
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
