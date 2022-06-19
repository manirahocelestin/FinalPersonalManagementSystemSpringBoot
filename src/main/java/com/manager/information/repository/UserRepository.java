package com.manager.information.repository;

import com.manager.information.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.email = ?1")

    User findByEmail(String email);
    User findByUserName(String userName);


}
