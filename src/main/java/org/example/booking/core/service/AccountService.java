package org.example.booking.core.service;

import org.example.booking.core.dao.UserAccountDao;
import org.example.booking.core.dao.UserDao;
import org.example.booking.core.model.UserAccount;
import org.example.booking.core.model.UserImpl;
import org.example.booking.mvc.exceptions.MoneyNotEnoughException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class AccountService {
    @Autowired
    private UserAccountDao userAccountDao;
    @Autowired
    private UserDao userDao;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BigDecimal refill(long userId, BigDecimal moneyAmount) {
        UserImpl userImpl = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found for refill account"));
        UserAccount userAccount = userImpl.getUserAccount();
        if (userAccount == null) {
            userAccount = new UserAccount(userImpl.getId(), userImpl, BigDecimal.valueOf(0.0));
        }
        userImpl.setUserAccount(userAccount);
        BigDecimal oldAmount = userAccount.getMoneyAmount();
        BigDecimal newAmount = oldAmount.add(moneyAmount);
        userAccount.setMoneyAmount(newAmount);
        userDao.save(userImpl);
        return newAmount;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BigDecimal check(long userId) {
        UserImpl userImpl = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found for check account"));
        UserAccount userAccount = userAccountDao.findById(userId).orElse(new UserAccount(userImpl.getId(), userImpl, BigDecimal.valueOf(0.0)));
        return userAccount.getMoneyAmount();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public BigDecimal withdraw(long userId, BigDecimal moneyAmount) {
        UserImpl userImpl = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found for withdraw account"));
        UserAccount userAccount = userAccountDao.findById(userId).orElse(new UserAccount(userImpl.getId(), userImpl, BigDecimal.valueOf(0.0)));
        BigDecimal currentAmount = check(userId);
        boolean transactionApproved = currentAmount.compareTo(moneyAmount) >= 0;
        if (!transactionApproved) throw new MoneyNotEnoughException();
        BigDecimal newAmount = currentAmount.subtract(moneyAmount);
        userAccount.setMoneyAmount(newAmount);
        userAccountDao.save(userAccount);
        return newAmount;
    }

//    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
//    public BigDecimal refill(long userId, BigDecimal moneyAmount) {
//        UserImpl userImpl = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found for refill account"));
//        UserAccount userAccount = userAccountDao.findById(userId).orElse(new UserAccount(userImpl.getId(), userImpl, BigDecimal.valueOf(0.0)));
//        userAccount = userAccountDao.save(userAccount);
//        BigDecimal oldAmount = userAccount.getMoneyAmount();
//        BigDecimal newAmount = oldAmount.add(moneyAmount);
//        userAccount.setMoneyAmount(newAmount);
//        userAccountDao.save(userAccount);
//        return newAmount;
//    }

}

