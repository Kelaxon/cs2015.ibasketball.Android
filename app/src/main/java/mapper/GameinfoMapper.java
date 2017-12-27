package mapper;

import java.util.List;

import JDBC.Beans.Gameinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface GameinfoMapper {

    public boolean add(Gameinfo gameinfo);

    public boolean delete(Integer id);

    public boolean update(Gameinfo gameinfo);

    public Gameinfo findByGameinfoId(Integer id);
    //全部查询
    public List<Gameinfo> findAll();

}
