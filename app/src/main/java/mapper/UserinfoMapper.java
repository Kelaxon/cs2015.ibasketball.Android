package mapper;

import java.util.List;

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
    //用户登录判断密码
    public boolean userLogin(String name,String password);
}
