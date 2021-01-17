package com.fishyRestaurant.account.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    public Account() {}

    public Account(String username, String fullName, String email, String phoneNumber) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Account account = (Account) o;
        return Objects.equals(getId(), account.getId())
          && Objects.equals(getUsername(), account.getUsername())
          && Objects.equals(getFullName(), account.fullName)
          && Objects.equals(getEmail(), account.getEmail())
          && Objects.equals(getPhoneNumber(), account.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getFullName(), getEmail(), getPhoneNumber());
    }

    public String toString() {
        return "Account{" +
          "id=" + id +
          ", username='" + username + '\'' +
          ", fullName='" + fullName + '\'' +
          ", email='" + email + '\'' +
          ", phoneNumber='" + phoneNumber + '\'' +
          '}';
    }
}
