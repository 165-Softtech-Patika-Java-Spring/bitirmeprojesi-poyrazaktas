package com.poyrazaktas.bitirme.usr.entity;

import com.poyrazaktas.bitirme.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USR_USER")
@Getter
@Setter
public class UsrUser extends BaseEntity {
    @Id
    @SequenceGenerator(name = "UsrUser", sequenceName = "SEQ_USR_USER_ID")
    @GeneratedValue(generator = "UsrUser")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
