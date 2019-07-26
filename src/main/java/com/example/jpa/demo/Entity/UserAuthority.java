package com.example.jpa.demo.Entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "USER_AUTHORITY")
@Builder
@IdClass(UserAuthorityId.class)
@Data
public class UserAuthority{
    @Id
    private Long authorityId;
    @Id
    private Long userId;
}
