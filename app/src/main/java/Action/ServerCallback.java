package Action;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

/**
 * Created by ChrisYoung on 2018/1/11.
 */

public interface ServerCallback {
    void onSuccess(JsonObject reponse);
}
