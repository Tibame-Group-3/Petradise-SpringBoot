package tw.idv.petradisespringboot.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer id;

    @Column(name = "admin_name")
    private String name;

    @Column(name = "admin_account")
    private String account;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "admin_phone")
    private String phone;

    @Column(name = "admin_address")
    private String address;

    @Column(name = "admin_email")
    private String email;

    @Column(name = "admin_title")
    private String title;

    @Column(name = "admin_status", insertable = false)
    private Character status;
}
