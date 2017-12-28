package mapper.mapperImpl;

import JDBC.Beans.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 莫林立
 * @Date: Created in 2017/12/28
 */
public class UserinfoMapperImplTest {

    UserinfoMapperImpl userinfoMapperImpl=new UserinfoMapperImpl();

    /*
    添加用户记录，密码不为空
    successful
     */
    @Test
    public void add() {
        Userinfo userinfo=new Userinfo();

        userinfo.setUserName("李治廷");

        userinfo.setUserId(5);

        userinfo.setUserAddr("");

        userinfo.setUserIntro("");

        userinfo.setUserPassword("123456");

        if(userinfoMapperImpl.add(userinfo)) {
            System.out.println("add success!");
        }

    }

    /*
    删除user信息
    successful
     */
    @Test
    public void delete() {

        if(userinfoMapperImpl.delete(1)){
            System.out.println("删除成功");
        }

    }

    /*
    更新当前用户信息
    successful
     */
    @Test
    public void update() {
        Userinfo userinfo=new Userinfo();

        userinfo=userinfoMapperImpl.findByUserinfoId(2);

        System.out.println("更正前信息如下：");

        System.out.println(userinfo);

        userinfo.setUserName("杨洋");

        userinfoMapperImpl.update(userinfo);

        List<Userinfo>list= userinfoMapperImpl.findAll();

        System.out.println("更新后信息如下：");
        for (Userinfo u:list) {
            System.out.println(u);
        }
    }

    /*
    指定用户ID查询
        successful
     */
    @Test
    public void findByUserinfoId() {

        Userinfo userinfo=userinfoMapperImpl.findByUserinfoId(2);

        System.out.println(userinfo);
    }

    /*
    返回数据表所有信息
        successful
     */
    @Test
    public void findAll() {

        List<Userinfo> list=userinfoMapperImpl.findAll();

        if(list==null){
            System.out.println("List 为 空");
        }else {
            System.out.println("查找内容如下：");
        }
        for (Userinfo u:list
                ) {
            System.out.println(u);

        }
    }

    /*
    指定名字模糊查询
    successful
     */
    @Test
    public void findByName() {

        List<Userinfo> list=userinfoMapperImpl.findByName("lx");

        if(list!=null){
            System.out.println("List 不为空"+list.size());
        }
        for (Userinfo u:list
                ) {
            System.out.println(u);
        }
    }

    /*
    用户登录信息
    successful
    name正确password错误--》密码有误
    name不存在-->该用户不存在
     */
    @Test
    public void userLogin() {

        Integer i=userinfoMapperImpl.userLogin("杨洋","123456")
                .get("status");

        switch (i){
            case 0:
                System.out.println("登录成功");
                break;
            case 1:
                System.out.println("密码错误");
                break;
            case 2:
                System.out.println("该用户不存在");
                break;
        }

        System.out.println("当前用户");
        System.out.println(Userinfo.getCurrentUser());
    }
}
