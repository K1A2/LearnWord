package com.k1a2.sp.learnword.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.k1a2.sp.learnword.DB.DatabaseHalper;
import com.k1a2.sp.learnword.R;

public class WordFileActivity extends Activity {

    private String tableName;

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

            }
        }
    }
}
