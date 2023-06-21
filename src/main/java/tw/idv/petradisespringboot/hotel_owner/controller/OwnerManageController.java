package tw.idv.petradisespringboot.hotel_owner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import tw.idv.petradisespringboot.hotel_owner.service.HotelOwnerService;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/owner")
public class OwnerManageController {

	private final HotelOwnerService hotelOwnerService;

	@Autowired
	public OwnerManageController(HotelOwnerService hotelOwnerService) {
		this.hotelOwnerService = hotelOwnerService;
	}

	@GetMapping("/OwnerManage")
	public void getAllOwners(HttpServletRequest req, HttpServletResponse res) {
		
		try {
			// 查資料庫
			List<HotelOwnerVO> list = hotelOwnerService.getAll();
			
			
//			 for (HotelOwnerVO owner : list) {
//		            byte[] imageBytes = owner.getHotelLicPic();
//		            if (imageBytes != null) {
//		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//		                owner.setBase64Image(base64Image);
//		            }
//		        }
//			
			// json 傳給前端
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			res.setContentType("application/json;charset=utf-8");
			PrintWriter out = res.getWriter();
			System.out.println("查詢成功");
			out.write(jsonString);
			out.close();
		} catch (Exception e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		}
	}

	@PostMapping("/OwnerManage")
	public void updateOwner(HttpServletRequest req, HttpServletResponse res) {
		// 獲取前端回應
		String ownerAccess = req.getParameter("ownerAccess");
		String hotelid = req.getParameter("hotelId");
		Integer hotelId = Integer.valueOf(hotelid);
		//找到要做更新的hotelId
		HotelOwnerVO vo = hotelOwnerService.findByPrimaryKey(hotelId);

		//因為不會每個項目都更新到,這時候其他沒更新的會回傳null,與資料庫不相符,所以要設判斷
		if (vo != null) {
			vo.setOwnerAccess(ownerAccess);
			vo.setHotelId(hotelId);

			try {
				hotelOwnerService.update(vo);
				Gson gson = new Gson();
				String jsonString = gson.toJson(vo);
				res.setContentType("application/json;charset=utf-8");
				PrintWriter out = res.getWriter();
				out.write(jsonString);
				out.close();
			} catch (Exception e) {
				System.out.println("更新失敗");
				e.printStackTrace();
			}
		}
	}
}
