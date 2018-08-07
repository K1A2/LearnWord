package com.k1a2.sp.learnword.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.k1a2.sp.learnword.DB.DatabaseHalper;
import com.k1a2.sp.learnword.R;

public class WordFileActivity extends Activity {

    private String tableName;
    private ActionBar actionBar;
    private long backKeyPress;
    private DatabaseHalper databaseHalper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordfile);

        databaseHalper = new DatabaseHalper(getApplicationContext());

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.getBooleanExtra(ActivityKey.KEY_INTENT_MW_NEW, true)) {
                tableName = intent.getStringExtra(ActivityKey.KEY_INTENT_MW_NAME);
                databaseHalper.createTable(tableName);
            } else {
                tableName = intent.getStringExtra(ActivityKey.KEY_INTENT_MW_NAME);
            }
        }

        actionBar = getActionBar();
        actionBar.setTitle(tableName);
    }

    @Override
    public void onBackPressed() {
        Toast toast = new Toast(WordFileActivity.this);
        if (System.currentTimeMillis() - backKeyPress < 2000) {
            startActivity(new Intent(WordFileActivity.this, MainActivity.class));
            finish();
        } else {
            toast.makeText(WordFileActivity.this, "한번더 누르면 나가집니다", Toast.LENGTH_SHORT).show();
            backKeyPress = System.currentTimeMillis();
        }
    }
}
