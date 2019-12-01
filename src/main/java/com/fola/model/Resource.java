package com.fola.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Resource {
    @Id
    private final Integer id;
    private final String sku;
    private final String description;
    private final String createdBy;
    private final String updatedBy;
    private final LocalDateTime createdOn;
    private final LocalDateTime updatedOn;
}
