package mapper;

import java.util.List;
import java.util.Map;

import JDBC.Beans.Userinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 * DAO接口
 */

public interface UserinfoMapper {

    public boolean add(Userinfo userinfo);

    public boolean delete(Integer id);

    public boolean update(Userinfo userinfo);

    public Userinfo findByUserinfoId(Integer id);
    //全部查询
    public List<Userinfo> findAll();
    //名字模糊查询
    public List<Userinfo> findByName(String name);
    //用户登录，根据map.get(status)获取到用户登录的状态
    public Map<String ,Integer> userLogin(String name, String password);
}
