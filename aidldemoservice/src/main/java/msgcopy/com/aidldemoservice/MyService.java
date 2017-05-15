package msgcopy.com.aidldemoservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import msgcopy.com.service.IAidlService;

public class MyService extends Service {

    public IAidlService.Stub person = new Person();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return person;
    }
}
