package com.botmasterzzz.controller.dto;

import com.google.common.base.Objects;

import java.io.Serializable;

public class KafkaKeyDTO implements Serializable {

    private Long instanceKey;
    private Integer updateId;

    public Long getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(Long instanceKey) {
        this.instanceKey = instanceKey;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaKeyDTO that = (KafkaKeyDTO) o;
        return Objects.equal(instanceKey, that.instanceKey) &&
                Objects.equal(updateId, that.updateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instanceKey, updateId);
    }
}
