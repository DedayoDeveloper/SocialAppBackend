package com.ptv.escort.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscortReposiroty extends JpaRepository<EscortDetails, Long> {

}
