package tw.idv.petradisespringboot.pet.vo;

import lombok.Data;

import java.util.List;

@Data
public class NewPetDTO {

    private Pet pet;
    private List<String> pics;
}
