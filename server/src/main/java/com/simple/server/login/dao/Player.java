package com.simple.server.login.dao;

import javax.persistence.*;

/**
 * @Author：yanweifan
 * @E-mail；a349162727@qq.com
 * @CreateDate：2019/10/28
 * @Description:
 */
@Entity
@Table(name = "player", schema = "freedom", catalog = "")
public class Player {
    private String accName;
    private String passWord;

    @Id
    @Column(name = "acc_name")
    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    @Basic
    @Column(name = "pass_word")
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player that = (Player) o;

        if (accName != null ? !accName.equals(that.accName) : that.accName != null) return false;
        if (passWord != null ? !passWord.equals(that.passWord) : that.passWord != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accName != null ? accName.hashCode() : 0;
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        return result;
    }
}
