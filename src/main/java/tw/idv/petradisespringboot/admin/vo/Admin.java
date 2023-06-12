package tw.idv.petradisespringboot.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

<<<<<<< HEAD
=======
@Entity
>>>>>>> 27
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
@Entity
@Table(name = "admin")
public class Admin {
=======

@Table(name = "admin")
public class Admin {

>>>>>>> 27
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer id;
<<<<<<< HEAD

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
=======
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
    private Character title;
    @Column(name = "admin_status", insertable = false)
    private Character status;

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
>>>>>>> 27
}
