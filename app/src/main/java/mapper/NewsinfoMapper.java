package mapper;

import java.util.List;

import JDBC.Beans.Newsinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface NewsinfoMapper {

    public boolean add(Newsinfo newsinfo);

    public boolean delete(Integer newsId);

    public boolean update(Newsinfo newsinfo);

    public Newsinfo findByNewsinfoId(String newsId);
    //全部查询
    public List<Newsinfo> findAll();
}
