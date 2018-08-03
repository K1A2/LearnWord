package com.k1a2.sp.learnword.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.k1a2.sp.learnword.R;
import com.k1a2.sp.learnword.View.RecyclerView.WordListAdapter;
import com.k1a2.sp.learnword.View.RecyclerView.WordListItem;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView recycler_word;
    private WordListAdapter wordListAdapter;
    private FloatingActionButton fab_addWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_word = (RecyclerView)findViewById(R.id.recycle_list_word);
        fab_addWord = (FloatingActionButton)findViewById(R.id.fab_main_plus);

        wordListAdapter = new WordListAdapter(R.layout.recycler_list_word);

        recycler_word.setLayoutManager(new LinearLayoutManager(this));
        recycler_word.setItemAnimator(new DefaultItemAnimator());

        showWordLIst();

        //단어장 추가
        fab_addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showWordLIst() {
        ArrayList<WordListItem> wordListItems = new ArrayList<WordListItem>();
        wordListItems.add(new WordListItem("Test", 99));
        wordListItems.add(new WordListItem("test334", 23));
        wordListItems.add(new WordListItem("test354", 66));
        wordListItems.add(new WordListItem("test353", 34));
        wordListItems.add(new WordListItem("test875", 12));
        wordListItems.add(new WordListItem("test7676", 10));
        wordListItems.add(new WordListItem("test87", 9));
        wordListItems.add(new WordListItem("test090", 95));
        wordListItems.add(new WordListItem("test86", 3));
        wordListItems.add(new WordListItem("test09", 2));

        if (wordListItems != null) {
            for (WordListItem s:wordListItems) {
                wordListAdapter.addItem(s);
            }
        }
        recycler_word.setAdapter(wordListAdapter);
    }
}
