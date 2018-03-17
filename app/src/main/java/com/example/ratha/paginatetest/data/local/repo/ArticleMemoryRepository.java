package com.example.ratha.paginatetest.data.local.repo;

import com.example.ratha.paginatetest.enity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratha on 3/17/2018.
 */

public class ArticleMemoryRepository {
    private List<Article> articles=new ArrayList<>();
    private  int limit;
    public ArticleMemoryRepository(){
        if(limit==0)
            limit=15;
        for(int i=0;i<limit;i++){
            Article article=new Article();
            article.setId(i);
            article.setTitle("សុទ្ធ\u200Bតែ\u200Bកប់\u200Bៗ ឡាន\u200B-\u200Bម៉ូតូ\u200Bទំនើប\u200B\u200B១៥\u200Bប្រភេទ\u200B David Beckham ធ្លាប់\u200Bប្រើ");
            article.setContent("អតីត\u200Bតារា\u200Bបាល់ទាត់\u200Bពិភព\u200Bលោក David Beckham បាន\u200Bក្លាយ\u200Bជា\u200Bបុគ្គល\u200Bលេច\u200Bធ្លោ\u200B\u200Bក្នុង\u200Bចំណោម\u200B\u200Bអ្នក\u200Bកីឡា និង\u200Bតារា\u200Bសិល្បៈ\u200B។ កត្តា\u200Bជា\u200Bច្រើន\u200Bដែល\u200Bធ្វើ\u200Bឲ្យ\u200Bរូប\u200Bគេ\u200Bក្លាយ\u200Bជា\u200B\u200Bតារា\u200Bលេច\u200Bធ្លោ\u200Bបំផុត\u200B\u200Bគឺ\u200Bទាំង\u200B\u200Bផ្នែក\u200Bកីឡា\u200B ហ្វ៊េហ្សិន\u200B \u200Bជំនួញ និង\u200Bការ\u200Bប្រើ\u200Bប្រាស់\u200Bសម្ភារៈ\u200Bទំនើប។ ចំណែក\u200Bរថយន្ត\u200Bក៏\u200Bជា\u200Bផ្នែក\u200Bដ៏\u200Bសំខាន់\u200Bដែល\u200Bតារា\u200Bបាល់ទាត់\u200Bរូប\u200Bនេះ\u200B\u200Bនិយម\u200Bប្រើ\u200B\u200B\u200Bទំនើប\u200Bបំផុត។ ខាង\u200Bក្រោម\u200Bជា\u200Bរថយន្ត\u200B និង\u200Bម៉ូតូ\u200Bទំនើប\u200B១៥\u200Bដែល\u200Bរូប\u200Bគេ\u200B\u200Bធ្លាប់\u200Bបាន\u200Bប្រើ។ ១៥. រថយន្ត\u200Bម៉ាក\u200B Rolls Royce Phantom Drop Head Coupe");
            articles.add(article);
        }
    }

    public List<Article> getArticles(int size){
        this.limit=size;
        return articles;
    }
}
