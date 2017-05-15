package msgcopy.com.aidldemoclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import msgcopy.com.service.IAidlService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private IAidlService service;
    private TextView mTv;
    private Button btn;
    private Button bin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.mTv_value);
        btn = (Button) findViewById(R.id.button);
        bin = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
        bin.setOnClickListener(this);

    }

    private void initService() {
        Toast.makeText(MainActivity.this, "跨进程通信 成功5", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("msgcopy.com.aidldemoservice.MyService");
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IAidlService.Stub.asInterface(iBinder);
            Toast.makeText(MainActivity.this, "跨进程通信 成功2", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(MainActivity.this, "跨进程通信 成功3", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Toast.makeText(MainActivity.this, "跨进程通信 成功1", Toast.LENGTH_SHORT).show();
                if (service != null) {
                    Toast.makeText(MainActivity.this, "跨进程通信 成功4", Toast.LENGTH_SHORT).show();
                    try {
                        String value = service.getValue();
                        Log.i("getValue()",""+value);
                        mTv.setText(value + "");
                        Toast.makeText(MainActivity.this, "跨进程通信 成功", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "跨进程通信 失败", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.button2:
                initService();
                break;
        }

    }
}
