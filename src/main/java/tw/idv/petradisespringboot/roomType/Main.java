package tw.idv.petradisespringboot.roomType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tw.idv.petradisespringboot.roomType.repo.RoomTypeRepository;
import tw.idv.petradisespringboot.roomType.service.impl.RoomTypeServiceImpl;
import tw.idv.petradisespringboot.roomType.vo.RoomType;

import java.util.List;

@Component
public class Main implements CommandLineRunner {
    @Autowired
    private RoomTypeServiceImpl roomTypeService;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        List<RoomType> roomTypes = roomTypeRepository.searchHotel("桃園市", "狗", "小型", "喵響小屋");
        for (RoomType roomType :
                roomTypes
        ) {
            System.out.println(roomType);
        }
    }
}
