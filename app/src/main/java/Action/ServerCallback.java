package Action;

import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by ChrisYoung on 2018/1/11.
 */

public interface ServerCallback {
    void onSuccess(JsonObjectRequest reponse);
}
