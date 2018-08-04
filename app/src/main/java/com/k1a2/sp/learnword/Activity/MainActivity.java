package com.k1a2.sp.learnword.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.k1a2.sp.learnword.DB.DatabaseHalper;
import com.k1a2.sp.learnword.DB.DatabaseKey;
import com.k1a2.sp.learnword.R;
import com.k1a2.sp.learnword.View.RecyclerView.WordListAdapter;
import com.k1a2.sp.learnword.View.RecyclerView.WordListItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {

    private RecyclerView recycler_word;
    private WordListAdapter wordListAdapter;
    private DatabaseHalper databaseHalper;
    private FloatingActionButton fab_addWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_word = (RecyclerView)findViewById(R.id.recycle_list_word);
        fab_addWord = (FloatingActionButton)findViewById(R.id.fab_main_plus);

        wordListAdapter = new WordListAdapter(R.layout.recycler_list_word);
        databaseHalper = new DatabaseHalper(getApplicationContext());

        recycler_word.setLayoutManager(new LinearLayoutManager(this));
        recycler_word.setItemAnimator(new DefaultItemAnimator());

        showWordLIst();

        //단어장 추가
        fab_addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = databaseHalper.getTableCount();
                Toast.makeText(MainActivity.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                String dform = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
                dform += count;
                Intent intent = new Intent(MainActivity.this, WordFileActivity.class);
                intent.putExtra(ActivityKey.KEY_INTENT_MW_NEW, ActivityKey.KEY_INTENT_MW_NEW_T);
                intent.putExtra(ActivityKey.KEY_INTENT_MW_NAME, dform);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showWordLIst() {
        ArrayList<WordListItem> wordListItems = databaseHalper.getTableName();

        if (wordListItems != null) {
            for (WordListItem s:wordListItems) {
                wordListAdapter.addItem(s);
            }
        }
        recycler_word.setAdapter(wordListAdapter);
    }
}
