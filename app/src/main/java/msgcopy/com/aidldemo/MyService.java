package msgcopy.com.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import msgcopy.com.aidl.IMyAidlService;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new IMyAidlService.Stub(){
            @Override
            public int basicTypes(int anInt1, int anInt2) throws RemoteException {
                return anInt1+anInt2;
            }

            @Override
            public String addvalue(String value) throws RemoteException {
                return null;
            }

        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
