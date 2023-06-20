package tw.idv.petradisespringboot.adminaccess.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.idv.petradisespringboot.accessfunction.vo.AccessFunction;
import tw.idv.petradisespringboot.admin.vo.Admin;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @ManyToOne
    @MapsId("functionId")
    @JoinColumn(name = "function_id")
    private AccessFunction accessFunction;
}

