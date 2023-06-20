package tw.idv.petradisespringboot.pet.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.idv.petradisespringboot.pet.vo.enums.PetSize;
import tw.idv.petradisespringboot.pet.vo.enums.PetStatus;
import tw.idv.petradisespringboot.pet.vo.enums.PetType;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

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
    private PetType petType;
    @Column(name = "pet_size")
    private PetSize petSize;
    @Column(name = "pet_status", insertable = false)
    private PetStatus petStatus = PetStatus.NORMAL;


}
