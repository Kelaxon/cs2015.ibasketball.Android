package INTERFACE;

import android.content.Context;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public interface Action {
    String Domain="";

    String getUrl();

    Context getContext();

    void setContext(Context context);

    void setUrl(String url);
}
