package org.example.booking.core.service;

import org.example.booking.core.dao.UserDao;
import org.example.booking.core.model.UserImpl;
import org.example.booking.intro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(long userId) {
//        return userDao.getUserById(userId);
        return userDao.findById(userId).orElse(null);
    }

    public User getUserByEmail(String email) {
//        return userDao.getUserByEmail(email);
        UserImpl userExample = new UserImpl();
        userExample.setEmail(email);
        ExampleMatcher ignoringPropsMatcher = ExampleMatcher.matchingAny()
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withIgnorePaths("id", "name");

        return userDao.findOne(Example.of(userExample, ignoringPropsMatcher)).orElse(null);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
//        return userDao.getUsersByName(name, pageSize, pageNum);
        UserImpl userExample = new UserImpl();
        userExample.setEmail(name);
        ExampleMatcher ignoringPropsMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                .withIgnorePaths("id", "email");


        return convertToUserList(userDao.findAll(Example.of(userExample, ignoringPropsMatcher), PageRequest.of(pageNum, pageSize)).toList());

    }

    public User createUser(User user) {
//        return userDao.createUser(user);
        return userDao.save((UserImpl) user);
    }

    @Transactional
    public User updateUser(User user) {
        System.out.println("uaerservice.updateUser");
        int rowsUpdated = userDao.update111(user.getId(), user.getName(), user.getEmail());
        try {
            System.out.println("updated");
            if (rowsUpdated > 0) return (User) ((UserImpl) user).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloninig of user exception", e);
        }
        return null;
    }

    public boolean deleteUser(long userId) {
//        return userDao.deleteUser(userId);
        userDao.deleteById(userId);
        return true;
    }

    private List<User> convertToUserList(List<UserImpl> userImplList) {
        return userImplList.stream()
                .map(userImpl -> (User) userImpl)
                .collect(Collectors.toList());
    }
}
