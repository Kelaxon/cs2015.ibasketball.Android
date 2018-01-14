package Action;

import android.content.Context;

/**
 * Created by 莫林立 on 2018/1/10.
 */

public class ListAllAction implements Action {
    private String URL = Action.Domain;
    private Context context;

    public ListAllAction(String type) {

        if (type.equals("news")){

            URL += "listNewsAllApp.action";
        }

        if(type.equals("game")){

            URL+="listGameAllApp.action";
        }

        if (type.equals("message"))
            URL += "listMessageAllApp.action";
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
        this.context = context;
    }


}
