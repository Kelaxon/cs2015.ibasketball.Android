package mapper.mapperImpl;

import JDBC.Beans.Newsinfo;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 莫林立
 * @Date: Created in 2017/12/29
 */
public class NewsinfoMapperImplTest {
    NewsinfoMapperImpl newsinfoMapperImpl=new NewsinfoMapperImpl();

    /*
    successful
     */
    @Test
    public void add() {
        Newsinfo newsinfo=new Newsinfo();

        newsinfo.setNewsId(1);

        newsinfo.setNewsContent("黄金拔凉");

        newsinfo.setNewsTitle("入球得三分");

        if ( newsinfoMapperImpl.add(newsinfo)){
            System.out.println("add successful");

        }else {
            System.out.println("add fail");
        }
    }

    /*
    successful
     */
    @Test
    public void delete() {
        if(newsinfoMapperImpl.delete(1)){
            System.out.println("delete successful");
        }else {
            System.out.println("delete fail");
        }
    }

    /*
    successful
     */
    @Test
    public void update() {
        Newsinfo newsinfo=newsinfoMapperImpl.findByNewsinfoId(1);

        if(!newsinfo.equals(null)){
            System.out.println("更改之前");

            System.out.println(newsinfo);

            newsinfo.setNewsTitle("火山篮球");

            newsinfoMapperImpl.update(newsinfo);

            System.out.println("更改之后");

            newsinfo=newsinfoMapperImpl.findByNewsinfoId(1);

            System.out.println(newsinfo);
        }else {
            System.out.println("没有这个对象");
        }
    }

    /*
    successful
     */
    @Test
    public void findByNewsinfoId() {
        Newsinfo newsinfo=newsinfoMapperImpl.findByNewsinfoId(2);

        System.out.println(newsinfo);
    }

    /*
    successful
     */
    @Test
    public void findAll() {
        List<Newsinfo> list=newsinfoMapperImpl.findAll();

        if (!list.equals(null)){

            System.out.println("list"+list.size());

            for (Newsinfo n:list
                    ) {
                System.out.println(n);
            }
        }
    }
}
