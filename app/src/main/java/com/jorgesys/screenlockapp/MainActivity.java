package com.jorgesys.screenlockapp;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    protected static final int REQUEST_ENABLE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Throws
        //SecurityException	if the calling application does not own an active administrator that uses DeviceAdminInfo.USES_POLICY_FORCE_LOCK

        //https://developer.android.com/reference/android/app/admin/DeviceAdminInfo#USES_POLICY_FORCE_LOCK

        //A type of policy that this device admin can use: able to force the device to lock
        //Un tipo de política que el administrador de este dispositivo puede usar: capaz de forzar el bloqueo del dispositivo

        ComponentName cn = new ComponentName(this, AdminReceiver.class);
        DevicePolicyManager mgr = (DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);

        if (mgr.isAdminActive(cn)) {
            Log.i(TAG, "User is an admin!");
            mgr.lockNow();
        }else{
            Log.e(TAG, "User is NOT an admin!");
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
            startActivityForResult(intent, REQUEST_ENABLE);

        }


    }
}
