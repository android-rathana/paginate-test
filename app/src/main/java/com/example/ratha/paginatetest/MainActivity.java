package com.example.ratha.paginatetest;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratha.paginatetest.adapter.ArticleAdapter;
import com.example.ratha.paginatetest.data.local.repo.Database;
import com.example.ratha.paginatetest.enity.Article;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemCreator;
import com.paginate.recycler.LoadingListItemSpanLookup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Paginate.Callbacks{
    private static final int GRID_SPAN = 3;
    Database database;
    RecyclerView recyclerView;
    ArticleAdapter adapter;
    private boolean loading = false;
    private int page = 0;
    protected int itemsPerPage = 10;
    int totalPage=3;
    protected boolean addLoadingRow = true;
    protected boolean customLoadingListItem = false;
    private Paginate paginate;

    List<Article> articles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database= Database.getInstance();

        setupUI();
        refreshData();
    }

    private void refreshData() {
        if (paginate != null) {
            paginate.unbind();
        }
        articles.addAll(database.getArticleMemoryRepository().getArticles(20));
        adapter=new ArticleAdapter(this,articles);
        loading=false;
        page=0;

        //adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        paginate=Paginate.with(recyclerView,this)
                .setLoadingTriggerThreshold(3)
                .addLoadingListItem(addLoadingRow)
                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
                /*.setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return 3;
                    }
                })*/
                .build();
    }

    private void setupUI() {
        recyclerView=findViewById(R.id.rvArticle);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

     class WrapContentLinearLayoutManager extends LinearLayoutManager {
        //... constructor
         public WrapContentLinearLayoutManager(Context context,int orientation,boolean reversed){
             super(context,orientation,reversed);
         }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("probe", "meet a IOOBE in RecyclerView");
            }
        }
    }
    private static final String TAG = "MainActivity";
    @Override
    public synchronized void onLoadMore() {
        Log.e(TAG, "onLoadMore: "+page);
        loading=true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                adapter.add(database.getArticleMemoryRepository().getArticles(itemsPerPage));
                loading=false;
            }
        },2000);

    }

    @Override
    public boolean isLoading() {
        return loading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return page==totalPage;
    }
    private class CustomLoadingListItemCreator implements LoadingListItemCreator {
        @Override
        public HV onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.loading_item_simple_layout, parent, false);
            return new HV(view);
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // Bind custom loading row if needed
        }
    }
    static class HV extends RecyclerView.ViewHolder{
        public HV(View itemView){
            super(itemView);
        }

    }
}
