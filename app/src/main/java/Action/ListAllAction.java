package Action;

import android.content.Context;

import INTERFACE.Action;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public class ListAllAction implements Action {
    private  String URL=Action.Domain;
    private Context context;

    public ListAllAction(String type){
        URL+=type;
    }

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
        this.context=context;
    }

    @Override
    public void setUrl(String url) {
        this.URL=url;
    }
}
