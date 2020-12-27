package com.ptv.escort.Admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EPDRepository extends JpaRepository<EscortPaymentDetails, Long> {





    @Query("select e from EscortPaymentDetails e where e.user = :id and e.escort = :id1")
    EscortPaymentDetails findByUserAndEscort(long id, long id1);

    @Modifying
    @Transactional
    @Query("update EscortPaymentDetails e set e.paymentConfirmed = 1 where e.user = :idOfUser and e.escort = :idOfEscort")
    int updateEscortPayment(long idOfUser, long idOfEscort);
}
