package mapper;

import java.util.List;

import JDBC.Beans.Teaminfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface TeaminfoMapper {

    public boolean add(Teaminfo teaminfo);

    public boolean delete(Integer id);

    public boolean update(Teaminfo teaminfo);

    public Teaminfo findByTeaminfoId(Integer id);
    //全部查询
    public List<Teaminfo> findAll();

}
