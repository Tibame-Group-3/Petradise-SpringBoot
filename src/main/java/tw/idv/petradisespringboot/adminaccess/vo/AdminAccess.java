package tw.idv.petradisespringboot.adminaccess.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_access")
public class AdminAccess {

    @EmbeddedId
    private AdminAccessId id;

    @Override
    public String toString() {
        return "AdminAccess{" +
                "id=" + id +
                '}';
    }
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class AdminAccessId implements Serializable {
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "function_id")
    private Integer functionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminAccessId that = (AdminAccessId) o;
        return Objects.equals(adminId, that.adminId) && Objects.equals(functionId, that.functionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, functionId);
    }
}
