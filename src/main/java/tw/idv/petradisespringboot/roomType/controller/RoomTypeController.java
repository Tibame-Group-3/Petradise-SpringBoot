package tw.idv.petradisespringboot.roomType.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

@Controller
@RequestMapping("/roomTypes")
public class RoomTypeController {
    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService service) {
        super();
        this.service = service;
    }

    @GetMapping("/hotelId/{hotelId}")
    @ResponseBody
    List<RoomType> getByHotelId(@PathVariable Integer hotelId) {
        return service.getByHotelId(hotelId);
    }

    @PostMapping("/addRoomType")
    @ResponseBody
    public void addRoomType(@RequestBody RoomType newRoomType) {
        service.addNewRoomType(newRoomType);
    }

    @GetMapping("/{roomTypeId}")
    @ResponseBody
    public RoomType getRoomTypeWithPics(@PathVariable Integer roomTypeId) {
        return service.getRoomType(roomTypeId);
    }

    @PostMapping(path = "/{roomTypeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public RoomType updateRoomType(@PathVariable Integer roomTypeId,
                                   @RequestParam(value = "room_type_name") String roomTypeName,
                                   @RequestParam(value = "room_type_sale_status") Character roomTypeSaleStatus,
                                   @RequestParam(value = "room_type_price") Integer roomTypePrice,
                                   @RequestParam(value = "room_pet_type") String roomPetType,
                                   @RequestParam(value = "room_type_size") Character roomTypeSize,
                                   @RequestParam(value = "room_type_about") String roomTypeAbout,
                                   @RequestParam(value = "room_pic1") MultipartFile file1,
                                   @RequestParam(value = "room_pic2") MultipartFile file2) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeName(roomTypeName);
        roomType.setRoomTypeSaleStatus(roomTypeSaleStatus);
        roomType.setRoomTypePrice(roomTypePrice);
        roomType.setRoomPetType(roomPetType);
        roomType.setRoomTypeSize(roomTypeSize);
        roomType.setRoomTypeAbout(roomTypeAbout);
        return service.updateRoomType(roomTypeId, roomType, file1, file2);
    }


}
