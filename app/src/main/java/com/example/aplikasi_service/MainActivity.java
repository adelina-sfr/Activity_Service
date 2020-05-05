package com.example.aplikasi_service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button mSetBtn;
    private Button mUnsetBtn;
    private RadioButton mMenitRadio;
    private RadioButton mLimaRadio;
    private RadioButton mTigaPuluhRadio;
    private RadioButton mJamRadio;
    private RadioGroup mTimeRadioGrup;
    public int mChangeTime = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetBtn = findViewById(R.id.btnSet);
        mUnsetBtn = findViewById(R.id.btnUnset);
        mMenitRadio = findViewById(R.id.radio0);
        mLimaRadio = findViewById(R.id.radio1);
        mTigaPuluhRadio = findViewById(R.id.radio2);
        mJamRadio = findViewById(R.id.radio3);
        mTimeRadioGrup = findViewById(R.id.radioGrup);

        mUnsetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mDisable = new Intent(MainActivity.this,
                        WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int mRadioID = mTimeRadioGrup.getCheckedRadioButtonId();
                if(mMenitRadio.getId()==mRadioID){mChangeTime=60;}
                else if (mLimaRadio.getId()==mRadioID){mChangeTime=5*60;}
                else if (mTigaPuluhRadio.getId()==mRadioID){mChangeTime=30*60;}
                else if (mJamRadio.getId()==mRadioID){mChangeTime=60*60;}

                Intent mService = new Intent(MainActivity.this,
                        WallpaperChangeService.class);

                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi",mChangeTime);
                mService.putExtras(mBundleTime);
                startService(mService);
                finish();
            }
        });
    }
}
