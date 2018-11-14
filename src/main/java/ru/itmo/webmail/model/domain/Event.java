package ru.itmo.webmail.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private long id;
    private long userId;
    private Type type;
    private Date creationTime;


  public Event(Type type){
      this.type=type;
  }

    public void setType(Type type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getType() {
        return type.toString();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    public enum Type{
        ENTER,LOGOUT;

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
