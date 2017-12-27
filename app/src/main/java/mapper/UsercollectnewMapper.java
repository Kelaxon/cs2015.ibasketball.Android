package mapper;

import java.util.List;

import JDBC.Beans.Usercollectnews;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface UsercollectnewMapper {

    public boolean add(Usercollectnews usercollectnews);

    public boolean delete(Integer id);

    public boolean update(Usercollectnews usercollectnews);

    public Usercollectnews findByUsercollectnewId(Integer id);
    //全部查询
    public List<Usercollectnews> findAll();

}
