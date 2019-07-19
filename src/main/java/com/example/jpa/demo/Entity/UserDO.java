package com.example.jpa.demo.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体类
 * @author yang
 * @since 2018-03-12
 */

@Entity
@Table(name="AUTH_USER")
public class UserDO {

    @Id
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private  String acount;

    @Column(length = 64)
    private  String pwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
