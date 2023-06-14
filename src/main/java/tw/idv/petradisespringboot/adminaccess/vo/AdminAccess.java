package tw.idv.petradisespringboot.adminaccess.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "admin_access")
public class AdminAccess {

    @Id
    @GeneratedValue
    @Column(name = "access_id")
    private Integer id;
    @Column(name = "admin_id")
    private Integer adminId;
    @Column(name = "function_id")
    private Integer functionId;

    @Override
    public String toString() {
        return "AdminAccess{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", functionId=" + functionId +
                '}';
    }
}
