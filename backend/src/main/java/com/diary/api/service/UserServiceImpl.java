package com.diary.api.service;

import com.diary.api.db.entity.User;
import com.diary.api.db.repository.UserRepository;
import com.diary.api.db.repository.UserRepositorySupport;
import com.diary.api.request.UserLoginReq;
import com.diary.api.request.UserSignupReq;
import com.diary.api.response.UserRes;
import com.diary.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;
//    public User createUser (UserLoginReq userReq) {
//        String userId = userReq.getUserId();
//        String password = userReq.getUserPwd();
//
//        User user = new User();
//        user.setUserId(userId);
////        user.setPassword(passwordEncoder.encode(password));
//        user.setUserPwd(password);
//        return userRepository.save(user);
//
//    }

    @Override
    public boolean signupUser(UserSignupReq userSignupReq) {
        if (userRepository.findByUserId(userSignupReq.getUserId()).isPresent()) {
            return false;
        }
        User user = new User();
        user.setUserId(userSignupReq.getUserId());

        String encryptedPwd = passwordEncoder.encode(userSignupReq.getUserPwd());
        System.out.println(encryptedPwd);

        user.setUserPwd(encryptedPwd);
        user.setUserName(userSignupReq.getUserName());
        user.setUserNickname(userSignupReq.getUserNickname());
        user.setUserProfile("test");

        System.out.println(userSignupReq.getUserName());

        if (userRepository.save(user) == null) return false;
        return true;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).get();
    }

    @Override
    public UserRes authenticate(UserLoginReq userLoginReq) {
        System.out.println(userLoginReq.getUserPwd());
        if (!userRepository.findByUserId(userLoginReq.getUserId()).isPresent())
            return null;

        User user = userRepository.findByUserId(userLoginReq.getUserId()).get();

        if (!passwordEncoder.matches(userLoginReq.getUserPwd(), user.getUserPwd())) {
            return null;
        }

        return new UserRes(user, JwtTokenUtil.getToken(user.getUserId()));
    }
}
