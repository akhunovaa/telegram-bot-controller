package com.botmasterzzz.controller.dto;

public class KinoDTO {

    private long id;
    private String name;
    private String type;
    private String posterLInk;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterLInk() {
        return posterLInk;
    }

    public void setPosterLInk(String posterLInk) {
        this.posterLInk = posterLInk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "KinoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", posterLInk='" + posterLInk + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
