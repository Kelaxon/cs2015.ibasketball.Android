package Action;

import com.google.gson.JsonObject;

//import org.json.JSONObject;

/**
 * Created by ChrisYoung on 2018/1/11.
 */

public interface ServerCallback {

    void onSuccess(JsonObject reponse);
}
