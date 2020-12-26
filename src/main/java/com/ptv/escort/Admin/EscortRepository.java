package com.ptv.escort.Admin;

import com.ptv.escort.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EscortRepository extends JpaRepository<EscortDetails, Long> {

//    @Query("select e.id, e.description, e.location from EscortDetails e where e.category = :category")
    List<EscortDetails> findAllByCategory(String category);


    @Modifying
    @Transactional
    @Query("update EscortDetails e set e.delFlag = 'Y' where e.id = :id")
    int softDelete(long id);

    @Query("select e from EscortDetails e where e.id = :id")
    EscortDetails findEscortDetailsById(long id);
}
