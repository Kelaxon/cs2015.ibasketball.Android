package mapper;

import JDBC.Beans.Userinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface UserinfoMapper {

    public boolean add(Userinfo userinfo);

    public boolean delete(Integer id);

    public boolean update(Userinfo userinfo);

    public boolean findByUserinfoId(Integer id);

}
