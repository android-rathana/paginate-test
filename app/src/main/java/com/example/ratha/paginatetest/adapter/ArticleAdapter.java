package com.example.ratha.paginatetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ratha.paginatetest.R;
import com.example.ratha.paginatetest.enity.Article;

import java.util.List;

/**
 * Created by ratha on 3/17/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    List<Article> articles;
    Context context;

    public  void add(List<Article> arts){
        int previousSize=articles.size();
        articles.addAll(arts);
        notifyItemRangeInserted(previousSize,articles.size());
    }
    public ArticleAdapter(Context context,List<Article> articles){
        this.context=context;this.articles=articles;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article =articles.get(position);
        holder.tvTitle.setText(article.getTitle());
        setPopupMenu(holder.btnOption);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView btnOption;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            btnOption=itemView.findViewById(R.id.btnOption);

        }
    }

    private  void setPopupMenu(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.btnRemove:

                                return true;
                            case R.id.btnEdit:

                                return true;
                            }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
