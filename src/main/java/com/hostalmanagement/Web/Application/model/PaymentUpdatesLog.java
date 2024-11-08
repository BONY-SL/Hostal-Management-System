package com.hostalmanagement.Web.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_updates_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdatesLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tg_no", length = 50, nullable = false)
    private String tgNo;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "update_time", updatable = false) // Make non-updatable if only set on creation
    private LocalDateTime updateTime;

    @Column(name = "message", length = 255)
    private String message;

    @PrePersist
    public void prePersist() {
        this.updateTime = LocalDateTime.now(); // Ensure updateTime is set when entity is persisted
        System.out.println("prePersist called: updateTime set to " + this.updateTime);
    }
}
