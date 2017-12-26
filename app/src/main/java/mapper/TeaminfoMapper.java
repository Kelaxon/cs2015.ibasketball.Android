package mapper;

import JDBC.Beans.Teaminfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface TeaminfoMapper {

    public boolean add(Teaminfo teaminfo);

    public boolean delete(Integer id);

    public boolean update(Teaminfo teaminfo);

    public boolean findByTeaminfoId(Integer id);

}
