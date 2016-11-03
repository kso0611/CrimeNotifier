package com.embedded.dgu.crimenotifier;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    public Context mContext = null;
    public List<HistoryData> mListData = new ArrayList<>();
    public int item_layout;

    public ListViewAdapter(Context mContext, int item_layout) {
        super();
        this.mContext = mContext;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch(viewType){
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_history, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HistoryData mData = mListData.get(position);
        holder._subject.setText(mData.title);
        holder._detail.setText(mData.content);
        holder._date.setText(mData.date);
        holder.cardview.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView _subject;
        public TextView _detail;
        public TextView _date;
        public CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            _date = (TextView)itemView.findViewById(R.id.rawdate);
            _subject = (TextView)itemView.findViewById(R.id.subject);
            _detail = (TextView)itemView.findViewById(R.id.detail);
            cardview = (CardView)itemView.findViewById(R.id.cardview);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(HistoryData addInfo){
        mListData.add(addInfo);
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }

}
