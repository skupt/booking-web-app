package org.example.booking.core.dao;

import org.example.booking.core.model.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserImpl, Long> {

}
