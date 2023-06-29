package tw.idv.petradisespringboot.roomType.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class searchHotelDTO {
    private String location;
    private String petType;
    private Character petSize;
    private LocalDateTime inDay;
    private LocalDateTime outDay;

}
