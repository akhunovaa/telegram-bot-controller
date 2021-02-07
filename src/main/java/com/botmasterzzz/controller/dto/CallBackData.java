package com.botmasterzzz.controller.dto;

import java.io.Serializable;
import java.util.Optional;

public class CallBackData implements Serializable {

    private static final long serialVersionUID = 2L;

    private String path;
    private Long ud;
    private Integer categoryId;
    private String ca;
    private Integer offset;
    private Integer limit;
    private Integer l;
    private Integer o;
    private Integer productId;
    private Integer fileCount;
    private Integer fileSelected;
    private Long fd;
    private Long igr;
    private String lang;
    private String p;
    private String ch;
    private String tst;
    private String mtp;

    public CallBackData() {
    }

    public CallBackData(String path) {
        this.path = path;
    }

    public CallBackData(String path, Long fileId) {
        this.path = path;
        this.fd = fileId;
    }

    public CallBackData(String path, Long userId, Long fileId) {
        this.path = path;
        this.fd = fileId;
        this.ud = userId;
    }

    public CallBackData(Long telegramId) {
        this.ud = telegramId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset(Integer max) {
        return Optional.ofNullable(this.offset).orElse(0) > max ? max : Optional.ofNullable(this.offset).orElse(0);
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset < 0 ? 0 : offset;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Integer getFileSelected() {
        return fileSelected;
    }

    public void setFileSelected(Integer fileSelected) {
        this.fileSelected = fileSelected;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public Long getUserId() {
        return ud;
    }

    public void setUserId(Long userId) {
        this.ud = userId;
    }

    public Long getFileId() {
        return fd;
    }

    public void setFileId(Long fileId) {
        this.fd = fileId;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getO() {
        return o;
    }

    public void setO(Integer o) {
        this.o = o;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public Long getIgr() {
        return igr;
    }

    public void setIgr(Long igr) {
        this.igr = igr;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getTst() {
        return tst;
    }

    public void setTst(String tst) {
        this.tst = tst;
    }

    public String getMediaType() {
        return mtp;
    }

    public void setMediaType(String mediaType) {
        this.mtp = mediaType;
    }

    @Override
    public String toString() {
        return "CallBackData{" +
                "path='" + path + '\'' +
                ", userId=" + ud +
                ", categoryId=" + categoryId +
                ", ca='" + ca + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", l=" + l +
                ", o=" + o +
                ", productId=" + productId +
                ", fileCount=" + fileCount +
                ", fileSelected=" + fileSelected +
                ", fileId=" + fd +
                ", igr=" + igr +
                ", lang='" + lang + '\'' +
                ", ch='" + ch + '\'' +
                ", mtp='" + mtp + '\'' +
                ", p='" + p + '\'' +
                ", tst='" + tst + '\'' +
                '}';
    }
}
