package org.example.booking.core.dao;

import org.example.booking.core.model.UserImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserImpl, Long> {

    @Modifying(flushAutomatically = true)
    @Query(value = "update USER_IMPL set NAME = ?2, email = ?3 where id = ?1", nativeQuery = true)
    int update111(long id, String name, String email);

//    private Storage storage;
//
//    public void setStorage(Storage storage) {
//        this.storage = storage;
//    }
//
//    public User getUserById(long userId) {
//        return storage.getUserById(userId);
//    }
//
//    public User getUserByEmail(String email) {
//        return storage.getUserByEmail(email);
//    }
//
//    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
//        return storage.getUsersByName(name, pageSize, pageNum);
//    }
//
//    public User createUser(User user) {
//        return storage.createUser(user);
//    }
//
//    public User updateUser(User user) {
//        return storage.updateUser(user);
//    }
//
//    public boolean deleteUser(long userId) {
//        return storage.deleteUser(userId);
//    }
}
