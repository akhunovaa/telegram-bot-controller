package com.botmasterzzz.controller.dto.tiktok.music;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "uniqueId",
        "nickname",
        "avatarThumb",
        "avatarMedium",
        "avatarLarger",
        "signature",
        "verified",
        "secUid",
        "secret",
        "ftc",
        "relation",
        "openFavorite",
        "commentSetting",
        "duetSetting",
        "stitchSetting",
        "privateAccount"
})
public class Author {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("uniqueId")
    private String uniqueId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("avatarThumb")
    private String avatarThumb;
    @JsonProperty("avatarMedium")
    private String avatarMedium;
    @JsonProperty("avatarLarger")
    private String avatarLarger;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("secUid")
    private String secUid;
    @JsonProperty("secret")
    private Boolean secret;
    @JsonProperty("ftc")
    private Boolean ftc;
    @JsonProperty("relation")
    private Integer relation;
    @JsonProperty("openFavorite")
    private Boolean openFavorite;
    @JsonProperty("commentSetting")
    private Integer commentSetting;
    @JsonProperty("duetSetting")
    private Integer duetSetting;
    @JsonProperty("stitchSetting")
    private Integer stitchSetting;
    @JsonProperty("privateAccount")
    private Boolean privateAccount;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("uniqueId")
    public String getUniqueId() {
        return uniqueId;
    }

    @JsonProperty("uniqueId")
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("avatarThumb")
    public String getAvatarThumb() {
        return avatarThumb;
    }

    @JsonProperty("avatarThumb")
    public void setAvatarThumb(String avatarThumb) {
        this.avatarThumb = avatarThumb;
    }

    @JsonProperty("avatarMedium")
    public String getAvatarMedium() {
        return avatarMedium;
    }

    @JsonProperty("avatarMedium")
    public void setAvatarMedium(String avatarMedium) {
        this.avatarMedium = avatarMedium;
    }

    @JsonProperty("avatarLarger")
    public String getAvatarLarger() {
        return avatarLarger;
    }

    @JsonProperty("avatarLarger")
    public void setAvatarLarger(String avatarLarger) {
        this.avatarLarger = avatarLarger;
    }

    @JsonProperty("signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @JsonProperty("secUid")
    public String getSecUid() {
        return secUid;
    }

    @JsonProperty("secUid")
    public void setSecUid(String secUid) {
        this.secUid = secUid;
    }

    @JsonProperty("secret")
    public Boolean getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(Boolean secret) {
        this.secret = secret;
    }

    @JsonProperty("ftc")
    public Boolean getFtc() {
        return ftc;
    }

    @JsonProperty("ftc")
    public void setFtc(Boolean ftc) {
        this.ftc = ftc;
    }

    @JsonProperty("relation")
    public Integer getRelation() {
        return relation;
    }

    @JsonProperty("relation")
    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    @JsonProperty("openFavorite")
    public Boolean getOpenFavorite() {
        return openFavorite;
    }

    @JsonProperty("openFavorite")
    public void setOpenFavorite(Boolean openFavorite) {
        this.openFavorite = openFavorite;
    }

    @JsonProperty("commentSetting")
    public Integer getCommentSetting() {
        return commentSetting;
    }

    @JsonProperty("commentSetting")
    public void setCommentSetting(Integer commentSetting) {
        this.commentSetting = commentSetting;
    }

    @JsonProperty("duetSetting")
    public Integer getDuetSetting() {
        return duetSetting;
    }

    @JsonProperty("duetSetting")
    public void setDuetSetting(Integer duetSetting) {
        this.duetSetting = duetSetting;
    }

    @JsonProperty("stitchSetting")
    public Integer getStitchSetting() {
        return stitchSetting;
    }

    @JsonProperty("stitchSetting")
    public void setStitchSetting(Integer stitchSetting) {
        this.stitchSetting = stitchSetting;
    }

    @JsonProperty("privateAccount")
    public Boolean getPrivateAccount() {
        return privateAccount;
    }

    @JsonProperty("privateAccount")
    public void setPrivateAccount(Boolean privateAccount) {
        this.privateAccount = privateAccount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("uniqueId", uniqueId).append("nickname", nickname).append("avatarThumb", avatarThumb).append("avatarMedium", avatarMedium).append("avatarLarger", avatarLarger).append("signature", signature).append("verified", verified).append("secUid", secUid).append("secret", secret).append("ftc", ftc).append("relation", relation).append("openFavorite", openFavorite).append("commentSetting", commentSetting).append("duetSetting", duetSetting).append("stitchSetting", stitchSetting).append("privateAccount", privateAccount).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(avatarMedium).append(signature).append(secUid).append(stitchSetting).append(verified).append(commentSetting).append(secret).append(privateAccount).append(relation).append(avatarThumb).append(ftc).append(openFavorite).append(avatarLarger).append(duetSetting).append(nickname).append(id).append(additionalProperties).append(uniqueId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Author) == false) {
            return false;
        }
        Author rhs = ((Author) other);
        return new EqualsBuilder().append(avatarMedium, rhs.avatarMedium).append(signature, rhs.signature).append(secUid, rhs.secUid).append(stitchSetting, rhs.stitchSetting).append(verified, rhs.verified).append(commentSetting, rhs.commentSetting).append(secret, rhs.secret).append(privateAccount, rhs.privateAccount).append(relation, rhs.relation).append(avatarThumb, rhs.avatarThumb).append(ftc, rhs.ftc).append(openFavorite, rhs.openFavorite).append(avatarLarger, rhs.avatarLarger).append(duetSetting, rhs.duetSetting).append(nickname, rhs.nickname).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(uniqueId, rhs.uniqueId).isEquals();
    }

}
