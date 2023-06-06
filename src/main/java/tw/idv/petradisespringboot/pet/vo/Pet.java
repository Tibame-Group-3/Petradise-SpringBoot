package tw.idv.petradisespringboot.pet.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer petID;
    @Column(name = "mem_id")
    private Integer memID;
    @Column(name = "pet_name")
    private String petName;
    @Column(name = "pet_type")
    private String petType;
    @Column(name = "pet_size")
    private Character petSize;
    @Column(name = "pet_status", insertable = false)
    private Character petStatus;

    @Override
    public String toString() {
        return "Pet [petID=" + petID + ", memID=" + memID + ", petName=" + petName + ", petType=" + petType
                + ", petSize=" + petSize + ", petStatus=" + petStatus + "]";
    }

}
