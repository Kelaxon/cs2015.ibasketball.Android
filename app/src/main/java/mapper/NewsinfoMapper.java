package mapper;

import JDBC.Beans.Newsinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface NewsinfoMapper {

    public boolean add(Newsinfo newsinfo);

    public boolean delete(String newsId);

    public boolean update(Newsinfo newsinfo);

    public boolean findByNewsinfoId(String newsId);

}
