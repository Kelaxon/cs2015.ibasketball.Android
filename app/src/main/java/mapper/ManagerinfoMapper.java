package mapper;

import java.util.List;

import JDBC.Beans.Managerinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface ManagerinfoMapper {

    public boolean add(Managerinfo managerinfo);

    public boolean delete(String id);

    public boolean update(Managerinfo managerinfo);

    public Managerinfo findByManagerinfoId(String id);
    //全部查询
    public List<Managerinfo> findAll();
}
