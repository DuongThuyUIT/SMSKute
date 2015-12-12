package com.duongtran.smskute;

/**
 * Created by thuydao on 09/12/2015.
 */
import java.io.Serializable;

public class Topic implements Serializable {

    private String id;
    private String topicName;


    public Topic()  {}

    public Topic(  String id, String topicName) {
        this.setId(id);
        this.setTopicName(topicName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString()  {
        return this.topicName;
    }
}
