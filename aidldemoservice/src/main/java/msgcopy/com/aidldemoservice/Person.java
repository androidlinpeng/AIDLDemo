package msgcopy.com.aidldemoservice;

import android.os.RemoteException;

import msgcopy.com.service.IAidlService;

/**
 * Created by liang on 2017/5/15.
 */

public class Person extends IAidlService.Stub{

    public String value;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void setValue(String value) throws RemoteException {
        this.value = value;
    }

    @Override
    public String getValue() throws RemoteException {
        return value;
    }
}
