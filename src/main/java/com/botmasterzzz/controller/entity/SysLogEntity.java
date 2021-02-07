package com.botmasterzzz.controller.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sys_log", schema = "public")
public class SysLogEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "note")
    private String note;

    @Column(name = "messenger_id")
    private Long messangerId;

    @Column(name = "aud_when_create")
    private Timestamp audWhenCreate;

    @Column(name = "aud_when_update")
    private Timestamp audWhenUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getAudWhenCreate() {
        return audWhenCreate;
    }

    public void setAudWhenCreate(Timestamp audWhenCreate) {
        this.audWhenCreate = audWhenCreate;
    }

    public Timestamp getAudWhenUpdate() {
        return audWhenUpdate;
    }

    public void setAudWhenUpdate(Timestamp audWhenUpdate) {
        this.audWhenUpdate = audWhenUpdate;
    }

    public Long getMessangerId() {
        return messangerId;
    }

    public void setMessangerId(Long messangerId) {
        this.messangerId = messangerId;
    }

    @Override
    public String toString() {
        return "SysLogEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", note='" + note + '\'' +
                ", messangerId='" + messangerId + '\'' +
                ", audWhenCreate=" + audWhenCreate +
                ", audWhenUpdate=" + audWhenUpdate +
                '}';
    }
}
