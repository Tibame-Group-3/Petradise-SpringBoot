package tw.idv.petradisespringboot.randomHotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tw.idv.petradisespringboot.roomType.service.RoomTypeService;

@Controller
public class RandomHotelController {
    @Autowired
    RoomTypeService roomTypeService;
}
