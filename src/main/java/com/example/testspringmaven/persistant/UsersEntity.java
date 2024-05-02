package com.example.testspringmaven.persistant;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "payaya")
public class UsersEntity {
    @Id
    @Column(name = "nickname", nullable = false, length = 256)
    private String nickname;
    @Basic
    @Column(name = "password", nullable = false, length = 256)
    private String password;
    @Basic
    @Column(name = "age", nullable = false)
    private int age;
    @Basic
    @Column(name = "gender", nullable = false)
    private char gender;
    @Basic
    @Column(name = "pathologie", nullable = true)
    private String pathologie;

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPathologie() {
        return pathologie;
    }

    public void setPathologie(String pathologie) {
        this.pathologie = pathologie;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int role) {
        this.age = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (age != that.age) return false;
        if (gender != that.gender) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (pathologie != null ? !pathologie.equals(that.pathologie) : that.pathologie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    public UsersEntity(String nickname, String password,int age, char gender,String pathologie) {
        this.nickname = nickname;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.pathologie = pathologie;
    }

    public UsersEntity() {
    }
}
