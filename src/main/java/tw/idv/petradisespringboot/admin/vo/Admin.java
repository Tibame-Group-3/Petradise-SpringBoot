package tw.idv.petradisespringboot.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.petradisespringboot.adminaccess.vo.AdminAccess;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

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
    private Character title;
    @Column(name = "admin_status", insertable = false)
    private Character status;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdminAccess> accesses;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", title=" + title +
                ", status=" + status +
                ", adminAccesses=" + accesses +
                '}';
    }
}
