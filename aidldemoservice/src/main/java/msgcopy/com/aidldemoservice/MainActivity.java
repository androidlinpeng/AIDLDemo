package msgcopy.com.aidldemoservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import msgcopy.com.service.IAidlService;

public class MainActivity extends AppCompatActivity {

    private IAidlService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
    }

    private void initService() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,MyService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IAidlService.Stub.asInterface(iBinder);
            if (service!=null){
                try {
                    service.setValue("AIDL VALUE");
                    Toast.makeText(MainActivity.this,"Service 赋值成功",Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"Service 赋值失败",Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
