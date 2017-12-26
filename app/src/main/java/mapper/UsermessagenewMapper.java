package mapper;

import JDBC.Beans.Usermessagenew;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface UsermessagenewMapper {

    public boolean add(Usermessagenew usermessagenew);

    public boolean delete(Integer id);

    public boolean update(Usermessagenew usermessagenew);

    public boolean findByUsermessagenewId(Integer id);



}
