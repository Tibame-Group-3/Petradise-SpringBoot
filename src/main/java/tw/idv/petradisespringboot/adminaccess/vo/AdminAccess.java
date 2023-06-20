package tw.idv.petradisespringboot.adminaccess.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.idv.petradisespringboot.admin.vo.Admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_access")
public class AdminAccess {

    @EmbeddedId
    private AdminAccessId id;

    @ManyToOne
    @MapsId("adminId")
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class AdminAccessId implements Serializable {
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "function_id")
    private Integer functionId;

}
