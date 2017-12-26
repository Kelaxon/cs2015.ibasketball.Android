package mapper;

import JDBC.Beans.Announceinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 *
 * 实现增删改查功能
 */

public interface AnnounceinfoMapper {


    public boolean add(Announceinfo announceinfo);

    public boolean delete(Integer id);

    public boolean update(Announceinfo announceinfo);

    public boolean findByAnnounceinfoId(Integer id);

}
