package com.botmasterzzz.controller.dto;

public class SearchKinoDTO {

    private long id;
    private String value;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchKinoDTO{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
