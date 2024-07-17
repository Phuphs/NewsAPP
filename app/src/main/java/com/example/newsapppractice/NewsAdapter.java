package com.example.newsapppractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHodler> {
    private NewsItemClicked listener;
    private ArrayList<News> items;
    private Context context;

    public NewsAdapter(NewsItemClicked listener) {
        this.listener = listener;
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_item,parent,false);
        NewsViewHodler newsViewHodler=new NewsViewHodler(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(items.get(newsViewHodler.getAdapterPosition()));
            }
        });
        return newsViewHodler;
    }

    public void updateNews(ArrayList<News> newnews)
    {
        items.clear();
        items.addAll(newnews);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull NewsViewHodler holder, int position) {
    News currentItem=items.get(position);
    holder.newsAuthor.setText(currentItem.getAuthor());
    holder.newsFeed.setText(currentItem.getTitle());
        Glide.with(holder.itemView.getContext()).load(currentItem.getImageUrl()).into(holder.newsImage);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class NewsViewHodler extends RecyclerView.ViewHolder {
        private TextView newsFeed,newsAuthor;
        private ImageView newsImage;
        public NewsViewHodler(@NonNull View itemView) {
            super(itemView);
            newsImage=itemView.findViewById(R.id.newsImage);
            newsFeed=itemView.findViewById(R.id.newsFeed);
            newsAuthor=itemView.findViewById(R.id.newsAuthor);

        }
    }
    interface  NewsItemClicked{
        public void onItemClicked(News item);
    }
}
