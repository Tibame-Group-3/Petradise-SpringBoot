package tw.idv.petradisespringboot.admin.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_access")
public class AdminAccess {

    @EmbeddedId
    private AdminAccessId id;

    @JsonBackReference
    @ManyToOne
    @MapsId("adminId")
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @MapsId("functionId")
    @JoinColumn(name = "function_id")
    private AccessFunction accessFunction;
}
