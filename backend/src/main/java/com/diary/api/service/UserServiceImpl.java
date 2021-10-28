package com.diary.api.service;

import com.diary.api.db.entity.User;
import com.diary.api.db.repository.UserRepository;
import com.diary.api.request.UserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public User createUser (UserReq userReq) {
        String userId = userReq.getUserId();
        String password = userReq.getPassword();

        User user = new User();
        user.setUserId(userId);
//        user.setPassword(passwordEncoder.encode(password));
        user.setUserPwd(password);
        return userRepository.save(user);

    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).get();
    }
}
