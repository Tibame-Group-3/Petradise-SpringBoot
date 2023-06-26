package tw.idv.petradisespringboot.hotel_owner.controller;

import java.util.Base64;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;



@RestController
@RequestMapping("/ownerAccount")
public class HotelOwnerAccount {

//	@Autowired
//	private HttpSession session;

	@Autowired
	private HotelOwnerServiceImpl hotelOwnerServiceImpl;

//	查是哪個業主登入
	 @GetMapping("/{hotelId}")
	    public ResponseEntity<HotelOwnerVO> getHotelOwner(@PathVariable("hotelId") Integer hotelId) {	
		 
//			個人測試
		 HotelOwnerVO hotelOwnerVO = hotelOwnerServiceImpl.findByPrimaryKey(hotelId);
		 
		 //回應前端		 
		 if (hotelOwnerVO != null) {
			// 只需獲取圖片的數組
				byte[] imageBytes = hotelOwnerVO.getHotelLicPic(); 
				// 只要圖片不為空 就轉為Base64
				if (imageBytes != null) {
					String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

					// VO那邊建一個getter和setter的Base64,好讓下方可放進資料
					hotelOwnerVO.setBase64Image(imageBase64);
				}
		        return ResponseEntity.ok(hotelOwnerVO);
		    }else{
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
		HotelOwnerVO updateOwner = hotelOwnerServiceImpl.findByPrimaryKey(hotelId);
	

		updateOwner.setOwnerName(hotelOwnerVO.getOwnerName());
	    updateOwner.setOwnerEmail(hotelOwnerVO.getOwnerEmail());
	    updateOwner.setOwnerPhone(hotelOwnerVO.getOwnerPhone());
	    updateOwner.setOwnerBank(hotelOwnerVO.getOwnerBank());
	    updateOwner.setHotelName(hotelOwnerVO.getHotelName());
	    updateOwner.setHotelAddress(hotelOwnerVO.getHotelAddress());
	    updateOwner.setHotelLicId(hotelOwnerVO.getHotelLicId());
	    if(hotelOwnerVO.getOwnerPassword() != null) {
	    updateOwner.setOwnerPassword(hotelOwnerVO.getOwnerPassword());
	    }
	    // 執行更新操作
	    hotelOwnerServiceImpl.update(updateOwner);
		return ResponseEntity.ok("更新成功");    
	}
}
