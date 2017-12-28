package mapper.mapperImpl;

import JDBC.Beans.Gameinfo;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 莫林立
 * @Date: Created in 2017/12/28
 */
public class GameinfoMapperImplTest {

    GameinfoMapperImpl gameinfoMapperImpl=new GameinfoMapperImpl();

    /*
    successful
     */
    @Test
    public void add() {
        Gameinfo gameinfo=new Gameinfo();

        gameinfo.setGameId(1);

        gameinfo.setGameResult("第一");

        gameinfo.setGameTeam2("红牛对");

        gameinfo.setGameTeam1("黄牛对");

        gameinfo.setGameLocation("洛杉矶");

        if(gameinfoMapperImpl.add(gameinfo)){
            System.out.println("add success!");
        }else {
            System.out.println("add fail");
        }
    }

    /*
    successful
     */
    @Test
    public void delete() {

        if(gameinfoMapperImpl.delete(1)){
            System.out.println("delete successful!");
        }else {
            System.out.println("delete fail!");
        }

    }

    /*
    successful
     */
    @Test
    public void update() {
        Gameinfo gameinfo=gameinfoMapperImpl.findByGameinfoId(1);

        System.out.println("修改之前");

        System.out.println(gameinfo);

        gameinfo.setGameLocation("纽约");

        if(gameinfoMapperImpl.update(gameinfo)){
            System.out.println("更改成功");

            System.out.println(gameinfoMapperImpl.findByGameinfoId(1));
        }else {
            System.out.println("更改失败");
        }
    }

    /*
    successful
     */
    @Test
    public void findByGameinfoId() {
        Gameinfo gameinfo=gameinfoMapperImpl.findByGameinfoId(1);

        if (!gameinfo.equals(null)){
            System.out.println(gameinfo);
        }else {
            System.out.println("获取失败！");
        }
    }

    /*
    successsful
     */
    @Test
    public void findAll() {

        List<Gameinfo> list=gameinfoMapperImpl.findAll();

        if(!list.equals(null)){

            System.out.println("list不为空");

            for (Gameinfo g:list
                    ) {
                System.out.println(g);
            }
        }else {
            System.out.println("List空");
        }
    }
}
