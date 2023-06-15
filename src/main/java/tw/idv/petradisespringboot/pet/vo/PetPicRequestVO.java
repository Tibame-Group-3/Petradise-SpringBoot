package tw.idv.petradisespringboot.pet.vo;


import lombok.Data;

// For pet pic transfer with base64 string, not used in database.
@Data
public class PetPicRequestVO {
    private Integer id;
    private Integer petId;
    private String pic;
}
