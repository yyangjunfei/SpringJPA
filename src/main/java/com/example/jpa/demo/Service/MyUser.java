package com.example.jpa.demo.Service;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)    //注解控制null不序列化
public class MyUser implements UserDetails {
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> simpleGrantedAuthorities;

    public MyUser(String username, String password, List<SimpleGrantedAuthority> simpleGrantedAuthorities) {
        this.password = password;
        this.username = username;
        this.simpleGrantedAuthorities= simpleGrantedAuthorities;
    }

    public MyUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
