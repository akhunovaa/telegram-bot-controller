package com.botmasterzzz.controller.dto.tiktok;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "secUid",
        "name",
        "nickName",
        "verified",
        "signature",
        "avatar"
})
public class AuthorMeta {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("secUid")
    private String secUid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("secUid")
    public String getSecUid() {
        return secUid;
    }

    @JsonProperty("secUid")
    public void setSecUid(String secUid) {
        this.secUid = secUid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("nickName")
    public String getNickName() {
        return nickName;
    }

    @JsonProperty("nickName")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @JsonProperty("signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        return new ToStringBuilder(this).append("id", id).append("secUid", secUid).append("name", name).append("nickName", nickName).append("verified", verified).append("signature", signature).append("avatar", avatar).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(secUid).append(signature).append(nickName).append(name).append(verified).append(id).append(avatar).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AuthorMeta) == false) {
            return false;
        }
        AuthorMeta rhs = ((AuthorMeta) other);
        return new EqualsBuilder().append(secUid, rhs.secUid).append(signature, rhs.signature).append(nickName, rhs.nickName).append(name, rhs.name).append(verified, rhs.verified).append(id, rhs.id).append(avatar, rhs.avatar).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
