package tw.idv.petradisespringboot.pet.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet_pic")
public class PetPic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_id")
    private Integer petId;

    @Lob
    @Column
    private byte[] pic;

}
