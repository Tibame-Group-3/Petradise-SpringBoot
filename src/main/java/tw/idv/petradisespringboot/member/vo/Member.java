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
    private Integer memID;
    @Column(name = "mem_name")
    private String memName;
    @Column(name = "mem_account")
    private String memAccount;
    @Column(name = "mem_password")
    private String memPassword;
    @Column(name = "mem_birthday")
    private Date memBirthday;
    @Column(name = "mem_phone")
    private String memPhone;
    @Column(name = "mem_email")
    private String memEmail;
    @Column(name = "mem_address")
    private String memAddress;
    @Column(name = "mem_access", insertable = false)
    private Character memAccess;
    @Column(name = "mem_bonus", insertable = false)
    private Integer memBonus;

    @Override
    public String toString() {
        return "Member [memID=" + memID + ", memName=" + memName + ", memAccount=" + memAccount + ", memPassword="
                + memPassword + ", memBirthday=" + memBirthday + ", memPhone=" + memPhone + ", memEmail=" + memEmail
                + ", memAddress=" + memAddress + ", memAccess=" + memAccess + ", memBonus=" + memBonus + "]";
    }

}
