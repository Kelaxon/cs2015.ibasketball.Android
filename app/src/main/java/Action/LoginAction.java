package Action;

import android.content.Context;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public class LoginAction implements Action {
    private String url=Action.Domain+"loginApp.action";
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
    public void setContext(Context context) {
        this.context = context;
    }

}
