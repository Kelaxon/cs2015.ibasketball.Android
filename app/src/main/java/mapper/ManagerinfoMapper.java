package mapper;

import JDBC.Beans.Managerinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface ManagerinfoMapper {

    public boolean add(Managerinfo managerinfo);

    public boolean delete(Integer id);

    public boolean update(Managerinfo managerinfo);

    public boolean findByManagerinfoId(Integer id);

}
