package learnserver.useaar;

import android.content.Context;
import android.util.Log;

import com.example.clent.Client;

/**
 * 使用 aar 中的 service
 */
public class UserAARServer {
    private static final String TAG = UserAARServer.class.getSimpleName();

   public UserAARServer() {

   }

    public void testAarService(Context context) {
        Client client = new Client(context);
        client.startBinderService();
        Log.i(TAG, "startBinderService");
   }
}
