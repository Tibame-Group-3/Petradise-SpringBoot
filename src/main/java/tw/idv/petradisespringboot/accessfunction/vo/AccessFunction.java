package tw.idv.petradisespringboot.accessfunction.vo;

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

@Table(name = "access_function")
public class AccessFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id")
    private Integer id;
    @Column(name = "function_name")
    private Character name;

    @Override
    public String toString() {
        return "AccessFunction{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
