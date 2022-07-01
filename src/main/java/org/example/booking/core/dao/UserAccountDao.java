package org.example.booking.core.dao;

import org.example.booking.core.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {
    
}
