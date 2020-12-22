package com.ptv.escort.Admin;

import com.ptv.escort.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EscortReposiroty extends JpaRepository<EscortDetails, Long> {

//    @Query("select e from EscortDetails e where e.category = :category")
    List<EscortDetails> findAllByCategory(String category);


}
