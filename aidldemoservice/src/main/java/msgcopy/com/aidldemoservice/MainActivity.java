package msgcopy.com.aidldemoservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import msgcopy.com.service.IAidlService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private IAidlService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void initService() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,MyService.class);
        bindService(intent,connection, Service.BIND_AUTO_CREATE);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                initService();
                break;
            case R.id.button2:
                unbindService(connection);
                break;
        }
    }
}
