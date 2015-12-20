package com.duongtran.smskute;

import java.io.Serializable;

/**
 * Created by thuydao on 09/12/2015.
 */
public class SMS implements Serializable {
    private int id;
    private String content;
    private boolean liked;
    private String topicId;
    //private String title;

    public SMS()  {}

    //public SMS( String title, String content) {
    public SMS(  String content) {
       // this.title= title;
        this.content= content;
    }

    public SMS( String content, String topicId, boolean liked) {
        this.setContent(content);
        this.setTopicId(topicId);
        this.setLiked(liked);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getContent() {
        return content;
    }

    public String getNContent() {
        return "'" + content.substring(0,20) + "..." + "'";
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String toString() {
        return this.content;
    }

 //   public String getTitle() {
   //     return title;
   // }

   // public void settitle(String title) {
  //      this.title = title;
   // }

}
