package com.k1a2.sp.learnword.View.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.k1a2.sp.learnword.R;

import java.util.ArrayList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private List<WordListItem> wordListItems;
    private int itemLayout;

    public WordListAdapter(int itemLayout) {
        wordListItems = new ArrayList<WordListItem>();
        this.itemLayout = itemLayout;
    }

    @Override
    public WordListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordListAdapter.ViewHolder holder, int position) {
        WordListItem item = wordListItems.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textCount.setText("단어 수: " + item.getCount());
    }

    @Override
    public int getItemCount() {
        return wordListItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textTitle;
        public TextView textCount;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.text_card_Title);
            textCount = (TextView) itemView.findViewById(R.id.text_card_Count);
        }
    }

    public void removeItem(int position) {
        try {
            wordListItems.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void addItem(WordListItem item) {
        wordListItems.add(item);
        notifyItemInserted(wordListItems.size());
    }

    public void addItem(WordListItem item, int position) {
        wordListItems.add(position, item);
        notifyItemInserted(position);
    }

    public WordListItem getItem(int position) {
        return wordListItems.get(position);
    }

    public void clearItem() {
        final int count = wordListItems.size();
        wordListItems.clear();
        notifyItemRangeRemoved(0, count);
    }
}
