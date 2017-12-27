package mapper;

import java.util.List;

import JDBC.Beans.Usermessagenews;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public interface UsermessagenewsMapper {

    public boolean add(Usermessagenews usermessagenews);

    public boolean delete(Integer id);

    public boolean update(Usermessagenews usermessagenews);

    public Usermessagenews findByUsermessagenewId(Integer id);
    //全部查询
    public List<Usermessagenews> findAll();

}
