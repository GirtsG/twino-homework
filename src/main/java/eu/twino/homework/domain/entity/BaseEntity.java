package eu.twino.homework.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @ReadOnlyProperty
    private LocalDateTime createdTime;

    @LastModifiedDate
    @ReadOnlyProperty
    private LocalDateTime lastModifiedTime;
}
