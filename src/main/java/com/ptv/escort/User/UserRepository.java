package com.ptv.escort.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

    User findByEmail(String email);

//    @Query("select u from User u where u.category = :category")
//    List<User> findAllByCategory(String category);

    @Modifying
    @Transactional
    @Query("update User u set u.registrationPayment = 1 where u.id = :id")
    int updateRegistrationPayment(long id);
}
