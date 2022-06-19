package com.manager.information.service;

import com.manager.information.domain.ResetTokenPassword;
import com.manager.information.repository.IResetTokenPasswordRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ResetTockenPasswordServiceImp implements IResetTockenPasswordService {
    private final IResetTokenPasswordRep resetTokenPasswordRep;

    @Autowired
    public ResetTockenPasswordServiceImp(IResetTokenPasswordRep resetTokenPasswordRep) {
        super();
        this.resetTokenPasswordRep = resetTokenPasswordRep;
    }


    @Override
    public ResetTokenPassword findByToken(String token) {
        return resetTokenPasswordRep.findByToken(token);
    }

    @Override
    public ResetTokenPassword saveToken(ResetTokenPassword resetTokenPassword) {
        return resetTokenPasswordRep.save(resetTokenPassword);
    }
}
