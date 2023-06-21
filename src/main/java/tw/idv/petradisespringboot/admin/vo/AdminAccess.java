package tw.idv.petradisespringboot.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_access")
public class AdminAccess {

    @EmbeddedId
    private AdminAccessId id;

    @ManyToOne
    @MapsId("functionId")
    @JoinColumn(name = "function_id")
    private AccessFunction accessFunction;
}
