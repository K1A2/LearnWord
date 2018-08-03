package com.k1a2.sp.learnword.View.RecyclerView;

public class WordListItem {

    private String title;
    private int count;

    public WordListItem(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public String getTitle() {
        return title;
    }
}
