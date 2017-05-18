package com.example.kazi.testapplication.model;

public class News {

    private String article_fighter_id;
    private String featured_news_category;
    private String article_date;
    private String external_url_text;
    private String id;
    private String author;
    private String article_media_id;
    private String title;
    private String last_modified;
    private String thumbnail;
    private String created;
    private String[] keyword_ids;
    private String published_start_date;
    private String external_url;
    private String introduction;
    private String url_name;

    public String getArticle_fighter_id() {
        return article_fighter_id;
    }

    public String getFeatured_news_category() {
        return featured_news_category;
    }

    public String getArticle_date() {
        return article_date;
    }

    public String getExternal_url_text() {
        return external_url_text;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getArticle_media_id() {
        return article_media_id;
    }

    public String getTitle() {
        return title;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getCreated() {
        return created;
    }

    public String[] getKeyword_ids() {
        return keyword_ids;
    }

    public String getPublished_start_date() {
        return published_start_date;
    }

    public String getExternal_url() {
        return external_url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getUrl_name() {
        return url_name;
    }

    @Override
    public String toString() {
        return "News [article_fighter_id = " + article_fighter_id + ", featured_news_category = " + featured_news_category + ", article_date = " + article_date + ", external_url_text = " + external_url_text + ", id = " + id + ", author = " + author + ", article_media_id = " + article_media_id + ", title = " + title + ", last_modified = " + last_modified + ", thumbnail = " + thumbnail + ", created = " + created + ", keyword_ids = " + keyword_ids + ", published_start_date = " + published_start_date + ", external_url = " + external_url + ", introduction = " + introduction + ", url_name = " + url_name + "]";
    }
}