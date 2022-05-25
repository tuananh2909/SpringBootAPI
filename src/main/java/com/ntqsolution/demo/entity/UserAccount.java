package com.ntqsolution.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 200)
    private String username;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private RoleUser roleUser;
}
