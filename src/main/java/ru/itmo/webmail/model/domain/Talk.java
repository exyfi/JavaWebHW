package ru.itmo.webmail.model.domain;

import ru.itmo.webmail.model.service.UserService;

import java.io.Serializable;
import java.util.Date;

public class Talk implements Serializable {
    private UserService userService=new UserService();
    private long id;
    private long sourceId;
    private long targetId;

    //private User user =userService.find(targetId)

    private String target;


    private String text;

    private Date creationTime;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }



    public void setTarget(String target) {
        this.target=target;
       // this.target =userService.find(userId).getLogin();
    }
    //Без геттера невозможно получить доступ к target в Talk.ftlh
    public String getTarget() {
        return target;
    }


    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public long getSourceId() {
        return sourceId;
    }

    public long getTargetId() {
        return targetId;
    }

    public String getText() {
        return text;
    }
}
