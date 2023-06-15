package tw.idv.petradisespringboot.roomType.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.idv.petradisespringboot.roomType.service.RoomTypeService;

import tw.idv.petradisespringboot.roomType.vo.RoomType;

@RestController
public class RoomTypeController {
	private final RoomTypeService service;

	public RoomTypeController(RoomTypeService service) {
		super();
		this.service = service;
	}

	@GetMapping("/roomTypes/hotelId/{hotelId}")
	List<RoomType> getByHotelId(@PathVariable Integer hotelId) {
		return service.getByHotelId(hotelId);
	}


	@PostMapping("/addNewRoomType")
	public RoomType addNewRoomType(@RequestBody RoomType roomType ) {
		
	    
//	    // 儲存圖片文件並獲取其路徑
//	    String roomPic1Path = saveFileAndGetPath(roomPic1);
//	    String roomPic2Path = saveFileAndGetPath(roomPic2);
	    
	    return service.addNewRoomType(roomType);
	}

	// 將 MultipartFile 儲存為檔案並返回其路徑
	public String saveFileAndGetPath(MultipartFile file) throws IOException {
	    // 您需要定義您要保存文件的位置
	    String path = "/path/to/save/files/" + file.getOriginalFilename();

	    // 創建一個新的文件輸出流並將文件寫入該流
	    try (FileOutputStream fos = new FileOutputStream(path)) {
	        fos.write(file.getBytes());
	    }

	    return path;
	}

}