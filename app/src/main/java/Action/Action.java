package Action;

import android.content.Context;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public interface Action {
    String Domain = "http://172.20.10.7:8080/0-BBBS/";


    String getUrl();

    Context getContext();

    void setContext(Context context);
}
