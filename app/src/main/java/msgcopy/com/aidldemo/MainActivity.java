package msgcopy.com.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import msgcopy.com.aidl.IMyAidlService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTv;
    private IMyAidlService myService;
    private MyServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.mTv);
        mTv.setOnClickListener(this);

        initService();

    }

    private void initService() {
        mConnection= new MyServiceConnection();
        Intent intent = new Intent();
        intent.setClassName("msgcopy.com.aidldemo",MyService.class.getName());
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        try {
            String value = myService.addvalue("aidl");
//            mTv.setText(Integer.valueOf(value).toString()+"");
            mTv.setText(value+"");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public class MyServiceConnection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService = IMyAidlService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
