package com.example.jpa.demo.Service;
import com.example.jpa.demo.Dao.AuthorityDao;
import com.example.jpa.demo.Entity.Authority;
import com.example.jpa.demo.Entity.User;
import com.example.jpa.demo.Utils.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service(value = "myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired   //UserDetailsService验证用户名、密码和授权
    private UserService userService;

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查询用户信息
        User userInfo = userService.getUser(username);
        if (userInfo == null){
            throw new UsernameNotFoundException("用户不存在！");
        }

        //查询权限信息
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

        //根据UserId数据库查询权限
        List<Authority> authorityList = authorityDao.findAuthorityByUserId(userInfo.getUserId());

        authorityList.forEach(item->simpleGrantedAuthorities.add(new SimpleGrantedAuthority(LanguageUtils.getPinYin(item.getAuthorityName()))));
        return new MyUser(username, userInfo.getUserPwd(), simpleGrantedAuthorities);
    }

    //注册
    public User register( User userInfo){
       return userService.insert(userInfo);
    }
}
