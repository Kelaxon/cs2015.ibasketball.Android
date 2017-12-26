package mapper;

import JDBC.Beans.Usercollectnew;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface UsercollectnewMapper {

    public boolean add(Usercollectnew usercollectnew);

    public boolean delete(Integer id);

    public boolean update(Usercollectnew usercollectnew);

    public boolean findByUsercollectnewId(Integer id);


}
