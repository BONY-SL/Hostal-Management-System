package com.hostalmanagement.Web.Application.repository;

import com.hostalmanagement.Web.Application.model.PaymentUpdatesLog;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Registered
public interface FinepaymentRepository extends JpaRepository<PaymentUpdatesLog,Integer> {

    @Query(value="select * from payment_updates_log",nativeQuery = true)
    List<PaymentUpdatesLog> getfinePaymentDetails();

}
