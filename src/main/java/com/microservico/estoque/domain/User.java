package com.microservico.estoque.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "security", name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "user_code", sequenceName = "user_code", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_code")
    private Long code;

    @NotBlank
    @Size(min = 5, max = 30)
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "security", name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_code", referencedColumnName = "code")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private List<Authority> authorities;

    public Long getCode() {
        return code;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(code, user.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
