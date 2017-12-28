package mapper.mapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Newsinfo;
import JDBC.Beans.Scoreinfo;
import JDBC.DBConnection;
import mapper.NewsinfoMapper;

/**
 * Created by ChrisYoung on 2017/12/26.
 * 1写一个getAll
 * 2NewsInfo再写个通过string近似搜索的 用like % 返回List<NewsInfo>
 * <p>
 * modify by 莫林立 complete
 * DAO实现类
 */

public class NewsinfoMapperImpl implements NewsinfoMapper {
    @Override
    public boolean add(Newsinfo newsinfo) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            String sql = "select * from newsinfo where news_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, newsinfo.getNewsId());
            //执行
            rs = ps.executeQuery();

            if (!rs.next()) {                //如果数据表主键不冲突
                String sql_2 = "insert into newsinfo(news_id,mag_id,news_title,news_content,news_time,news_pic,news_count)" +
                        " values (?,?,?,?,?,?,?)";
                //准备语句
                ps = conn.prepareStatement(sql_2);
                //设置参数
                ps.setInt(1, newsinfo.getNewsId());

                ps.setString(2, newsinfo.getMagId());

                ps.setString(3, newsinfo.getNewsTitle());

                ps.setString(4, newsinfo.getNewsContent());

                ps.setTimestamp(5, newsinfo.getNewsTime());

                ps.setString(6, newsinfo.getNewsPic());

                ps.setInt(7, newsinfo.getNewsCount());
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
    public boolean delete(Integer newsId) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "delete from newsinfo where news_id=? ";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, newsId);
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
        }
        return false;
    }

    @Override
    public boolean update(Newsinfo newsinfo) {
        Connection conn = null;

        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "update  newsinfo " +
                    "set news_id=? , mag_id=? , news_title=? , news_content=? , news_time=? , news_pic=? , news_count=? " +
                    "where news_id=? ";
            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, newsinfo.getNewsId());

            ps.setString(2, newsinfo.getMagId());

            ps.setString(3, newsinfo.getNewsTitle());

            ps.setString(4, newsinfo.getNewsContent());

            ps.setTimestamp(5, newsinfo.getNewsTime());

            ps.setString(6, newsinfo.getNewsPic());

            ps.setInt(7, newsinfo.getNewsCount());

            ps.setInt(8,newsinfo.getNewsId());
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
        }
        return false;
    }

    @Override
    public Newsinfo findByNewsinfoId(Integer newsId) {
        Connection conn = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        Newsinfo newsinfo = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "select * from newsinfo where news_id =?";

            //准备语句
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, newsId);
            //执行
            rs = ps.executeQuery();

            if (rs.next()) {              //如果返回实例
                newsinfo = new Newsinfo();

                newsinfo.setNewsId(rs.getInt("news_id"));

                newsinfo.setMagId(rs.getString("mag_id"));

                newsinfo.setNewsTitle(rs.getString("news_title"));

                newsinfo.setNewsContent(rs.getString("news_content"));

                newsinfo.setNewsTime(rs.getTimestamp("news_time"));

                newsinfo.setNewsPic(rs.getString("news_pic"));

                newsinfo.setNewsCount(rs.getInt("news_count"));

            }
            return newsinfo;

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                DBConnection.dbClose(conn, ps, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Newsinfo> findAll() {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Newsinfo> list = new ArrayList<Newsinfo>();

        try {
            conn = DBConnection.getConnection();

            String sql = "select * from newsinfo";

            st = conn.createStatement();
            //无参执行
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Newsinfo newsinfo = new Newsinfo();

                newsinfo.setNewsId(rs.getInt("news_id"));

                newsinfo.setMagId(rs.getString("mag_id"));

                newsinfo.setNewsTitle(rs.getString("news_title"));

                newsinfo.setNewsContent(rs.getString("news_content"));

                newsinfo.setNewsTime(rs.getTimestamp("news_time"));

                newsinfo.setNewsPic(rs.getString("news_pic"));

                newsinfo.setNewsCount(rs.getInt("news_count"));

                list.add(newsinfo);
            }

            return list;
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
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
