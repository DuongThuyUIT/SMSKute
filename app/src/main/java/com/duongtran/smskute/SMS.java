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

    public SMS()  {}

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
}
