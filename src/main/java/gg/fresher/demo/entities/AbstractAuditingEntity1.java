//package gg.fresher.demo.entities;
//
//import java.time.Instant;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class AbstractAuditingEntity1 {
//    @CreatedBy
//    @Column(name = "created_by", nullable = false, updatable = false)
//    private String createdBy;
//
//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private Instant createdAt = Instant.now();
//
//    @LastModifiedBy
//    @Column(name = "updated_by")
//    private String updatedBy;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private Instant updatedAt = Instant.now();
//}
