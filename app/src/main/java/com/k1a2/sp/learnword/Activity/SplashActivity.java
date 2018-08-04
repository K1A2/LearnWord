package com.k1a2.sp.learnword.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.k1a2.sp.learnword.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int permisionRequest = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permisionRequest2 = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permisionRequest == PackageManager.PERMISSION_DENIED||permisionRequest2 == PackageManager.PERMISSION_DENIED)
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)||shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder permissioCheck = new AlertDialog.Builder(SplashActivity.this);
                    permissioCheck.setTitle("권한 허가")
                            .setMessage("단어장을 만들고 저장하려면 저장소 읽기와 쓰기 권한이 필요합니다")
                            .setCancelable(false)
                            .setPositiveButton("동의", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                                    }
                                }
                            })
                            .setNegativeButton("거부", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(SplashActivity.this, "이 앱을 사용할 수 없습니다", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .create()
                            .show();
                } else {
                    ActivityCompat.requestPermissions(SplashActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                } else {
                startActivity();
            }
        } else {
            startActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity();
                } else {
                    Toast.makeText(SplashActivity.this, "저장이 불가능 합니다.", Toast.LENGTH_SHORT).show();//
                    finish();
                }
                return;
        }
    }

    private void startActivity() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2300);
    }
}
