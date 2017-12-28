package mapper.mapperImpl;

import JDBC.Beans.Announceinfo;
import org.junit.Assert;
import org.junit.Test;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * @Author: 莫林立
 * @Date: Created in 2017/12/28
 */
public class AnnounceinfoMapperImplTest {

    AnnounceinfoMapperImpl announceinfoMapperImpl=new AnnounceinfoMapperImpl();

    /*
    注意外键添加的过程
    successful
     */
    @Test
    public void add() {

        Announceinfo announceinfo=new Announceinfo();
        announceinfo.setUserId(5);
        announceinfo.setAnnounceId(2);
        announceinfo.setAnnounceTitle("邹捷轮耍双杰昆");
        announceinfo.setAnnounceContent("门票只要998！");

        if(announceinfoMapperImpl.add(announceinfo)) {
            System.out.println("添加成功");
        }
    }
    /*
    successful
     */
    @Test
    public void delete() {
        if(announceinfoMapperImpl.delete(2)){
            System.out.println("删除成功");
        }


    }

    /*
    successful
     */
    @Test
    public void update() {
        Announceinfo announceinfo=announceinfoMapperImpl.findByAnnounceinfoId(2);

        System.out.println("更改前数据信息");
        System.out.println(announceinfo);
        System.out.println("更改后");
        announceinfo.setAnnounceTitle("周杰伦玩篮球贼6");

        if(announceinfoMapperImpl.update(announceinfo))
        {
            System.out.println("更改成功");

            System.out.println(announceinfoMapperImpl.findByAnnounceinfoId(2));
        };

    }

    /*
    successful
     */
    @Test
    public void findByAnnounceinfoId() {
        Announceinfo announceinfo=announceinfoMapperImpl.findByAnnounceinfoId(2);

        if(!announceinfo.equals(null)){
            System.out.println(announceinfo);
        }

    }

    /*
    successful
     */
    @Test
    public void findAll() {

        List<Announceinfo> list=announceinfoMapperImpl.findAll();

        if(!list.equals(null)){
            System.out.println("list 大小为"+list.size());
            for (Announceinfo a: list
                    ) {
                System.out.println(a);
            }
        }
    }
}
