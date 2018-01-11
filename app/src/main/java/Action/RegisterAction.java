package Action;

import android.content.Context;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public class RegisterAction implements Action {
    private String URL = Action.Domain+"registerApp.action";
    private Context context;

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

}
