package tw.idv.petradisespringboot.hotel_owner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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
import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/owner")
public class OwnerChackController {
	
	@Autowired
	private  HotelOwnerServiceImpl hotelOwnerServiceImpl;



	@GetMapping("/OwnerChack")
	public void getAllOwners(HttpServletRequest req, HttpServletResponse res) {
		
		try {	
			String hotelStatus = req.getParameter("hotelStatus");
			if("0".equals(hotelStatus)) {
			List<HotelOwnerVO> list = hotelOwnerServiceImpl.getAll();
			List<HotelOwnerVO> filteredOwners = list.stream().filter(owner -> "0".equals(owner.getHotelStatus()))
			        .collect(Collectors.toList()); //將符合的資料放進新的容器(filteredOwners)
			
			
			// json	
			Gson gson = new Gson();
			String jsonString = gson.toJson(filteredOwners);
			res.setContentType("application/json;charset=utf-8");
			PrintWriter out = res.getWriter();
			System.out.println("查詢成功");
			out.write(jsonString);
			out.close();
			}
		} catch (Exception e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		}
		
	}

	@PostMapping("/OwnerChack")
	public void updateOwner(HttpServletRequest req, HttpServletResponse res) {
		// 獲取前端回應
		String hotelStatus = req.getParameter("hotelStatus");
		String hotelid = req.getParameter("hotelId");
		Integer hotelId = Integer.valueOf(hotelid);
		//找到要做更新的hotelId
		HotelOwnerVO vo = hotelOwnerServiceImpl.findByPrimaryKey(hotelId);

		//因為不會每個項目都更新到,這時候其他沒更新的會回傳null,與資料庫不相符,所以要設判斷
		if (vo != null) {
			vo.setHotelStatus(hotelStatus);
			vo.setHotelId(hotelId);

			try {
				hotelOwnerServiceImpl.update(vo);
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

