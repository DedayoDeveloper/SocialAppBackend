package com.ptv.escort.Admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EPDRepository extends JpaRepository<EscortPaymentDetails, Long> {




    @Query("select e from EscortPaymentDetails e where e.user = :id and e.escort = :id1")
    EscortPaymentDetails findByUserAndEscort(long id, long id1);
}
