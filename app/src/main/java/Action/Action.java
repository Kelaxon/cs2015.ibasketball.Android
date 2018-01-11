package Action;

import android.content.Context;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public interface Action {
    String Domain="http://192.168.43.113:8080/0-BBBS/";

    String getUrl();

    Context getContext();

    void setContext(Context context);
}
