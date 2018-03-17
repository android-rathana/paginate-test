package com.example.ratha.paginatetest.data.local.repo;

/**
 * Created by ratha on 3/17/2018.
 */

public class Database {
    private static Database database;
    private ArticleMemoryRepository articleMemoryRepository;
    private Database(){}
    public static Database getInstance(){
        if(database==null)
            database=new Database();
        return database;
    }

    public ArticleMemoryRepository getArticleMemoryRepository(){
        return new ArticleMemoryRepository();
    }
}
