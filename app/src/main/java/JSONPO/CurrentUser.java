package JSONPO;

/**
 * Created by 莫林立 on 2018/1/10.
 *
 *      当前用户类
 */

public class CurrentUser {
    private static Userinfo currentUser;

    public Userinfo getcurrentUser(){
        currentUser.setAnnounceinfos(null);
        currentUser.setUsercollectnews(null);
        currentUser.setUsermessagenews(null);
        return currentUser;
    }

    public static void setCurrentUser(Userinfo user){
        currentUser=user;
    }

}
