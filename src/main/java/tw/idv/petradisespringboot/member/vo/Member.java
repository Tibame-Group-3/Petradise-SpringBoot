package tw.idv.petradisespringboot.member.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Integer id;
    @Column(name = "mem_name")
    private String name;
    @Column(name = "mem_account")
    private String account;
    @Column(name = "mem_password")
    private String password;
    @Column(name = "mem_birthday")
    private Date birthday;
    @Column(name = "mem_phone")
    private String phone;
    @Column(name = "mem_email")
    private String email;
    @Column(name = "mem_address")
    private String address;
    @Column(name = "mem_access", insertable = false)
    private Character access;
    @Column(name = "mem_bonus", insertable = false)
    private Integer bonus;

    @Override
    public String toString() {
        return "Member [memID=" + id + ", memName=" + name + ", memAccount=" + account + ", memPassword="
                + password + ", memBirthday=" + birthday + ", memPhone=" + phone + ", memEmail=" + email
                + ", memAddress=" + address + ", memAccess=" + access + ", memBonus=" + bonus + "]";
    }

}
