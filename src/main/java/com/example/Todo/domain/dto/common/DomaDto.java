package com.example.Todo.domain.dto.common;

import java.time.LocalDateTime;

public interface DomaDto extends Dto{
    String getCreated_by();

    void setCreated_by(String createdBy);

    LocalDateTime getCreated_at();

    void setCreated_at(LocalDateTime createdAt);

    String getUpdated_by();

    void setUpdated_by(String updatedBy);

    LocalDateTime getUpdated_at();

    void setUpdated_at(LocalDateTime updatedAt);

    String getDeleted_by();

    void setDeleted_by(String deletedBy);

    LocalDateTime getDeleted_at();

    void setDeleted_at(LocalDateTime deletedAt);

    Integer getVersion();

    void setVersion(Integer version);
}
