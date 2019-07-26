package com.example.jpa.demo.Entity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="AUTHORITY")
@Data
@Builder
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;

    @Column(nullable = false,name = "authority_name",length = 32)
    private String authorityName;

    @ManyToMany(mappedBy = "authorityList")
    private List<User> userList;
}
