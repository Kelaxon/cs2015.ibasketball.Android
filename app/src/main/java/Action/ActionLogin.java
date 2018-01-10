package Action;

import android.content.Context;

import INTERFACE.Action;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public class ActionLogin implements Action {
    private String url=Action.Domain;
    private Context context;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

}
