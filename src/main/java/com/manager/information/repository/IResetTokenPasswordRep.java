package com.manager.information.repository;

import com.manager.information.domain.ResetTokenPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IResetTokenPasswordRep extends JpaRepository<ResetTokenPassword, Long> {
    ResetTokenPassword findByToken(String token);

}
