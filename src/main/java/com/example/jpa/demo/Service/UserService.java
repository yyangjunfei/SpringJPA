package com.example.jpa.demo.Service;
import com.example.jpa.demo.Dao.UserDao;
import com.example.jpa.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User insert(User userInfo){
        encryptPassword(userInfo);
        User user =userDao.save(userInfo);

        return user;
    }

    private void encryptPassword(User userInfo){
        String password = userInfo.getUserPwd();
        password = new BCryptPasswordEncoder().encode(password);
        userInfo.setUserPwd(password);
    }


    public User getUser(String userName){
       return userDao.findByUserName(userName);
    }

}
