package mapper;

import java.util.List;

import JDBC.Beans.Scoreinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface ScoreinfoMapper {

    public boolean add(Scoreinfo scoreinfo);

    public boolean delete(Integer id);

    public boolean update(Scoreinfo scoreinfo);

    public Scoreinfo findByScoreinfoId(Integer id);
    //全部查询
    public List<Scoreinfo> findAll();

}
