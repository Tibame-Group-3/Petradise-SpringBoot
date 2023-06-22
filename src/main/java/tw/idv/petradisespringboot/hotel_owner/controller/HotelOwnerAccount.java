package tw.idv.petradisespringboot.hotel_owner.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/")
public class HotelOwnerAccount {

	@Autowired
	private HttpSession session;

	@Autowired
	private HotelOwnerServiceImpl hotelOwnerServiceImpl;

//	查是哪個業主登入
	 @GetMapping("/{hotelId}")
	    public ResponseEntity<HotelOwnerVO> getHotelOwner(@PathVariable("hotelId") Integer hotelId) {
			

//			個人測試
		 HotelOwnerVO hotelOwnerVO = hotelOwnerServiceImpl.findByPrimaryKey(hotelId);
		 if (hotelOwnerVO != null) {
		        return ResponseEntity.ok(hotelOwnerVO);
		    } else {
		        return ResponseEntity.notFound().build();
		    }		
	    }
	 
//	  透過session取得連線的用戶
//     int hotelId = (int) session.getAttribute("hotelId");       
//     HotelOwnerVO vo = hotelOwnerServiceImpl.findByPrimaryKey(hotelId);
//     
//     if (vo != null) {
//         return ResponseEntity.ok(vo);
//     } else {
//         return ResponseEntity.notFound().build();
//     }
	 
	 
	 
	 // 修改業主資料
	 @PostMapping("/{hotelId}")
	 public ResponseEntity<String> updateHotelOwner(@PathVariable int hotelId,@RequestBody HotelOwnerVO hotelOwnerVO){
		  hotelOwnerServiceImpl.update(hotelOwnerVO);
		return ResponseEntity.ok("更新成功");    
	}
}
