package tw.idv.petradisespringboot.hotel_owner.controller;

import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/ownerChack")
public class OwnerCheckController {

	@Autowired
	private HotelOwnerServiceImpl hotelOwnerServiceImpl;

	@GetMapping("/getAll")
	public void getAllOwners(HttpServletRequest req, HttpServletResponse res) {

		try {
			String hotelStatus = req.getParameter("hotelStatus");
//			if("0".equals(hotelStatus)) {
			List<HotelOwnerVO> list = hotelOwnerServiceImpl.getAll();
			List<HotelOwnerVO> filteredOwners = list.stream().filter(owner -> "0".equals(owner.getHotelStatus())) // .stream()就像是迴圈
					.collect(Collectors.toList()); // 將符合的資料放進新的容器(filteredOwners)
			for (HotelOwnerVO vo : list) {
				// 只需獲取圖片的數組
				byte[] imageBytes = vo.getHotelLicPic();
				// 只要圖片不為空 就轉為Base64
				if (imageBytes != null) {
					String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

					// VO那邊建一個getter和setter的Base64,好讓下方可放進資料
					vo.setBase64Image(imageBase64);
				}
			}

			// json
			Gson gson = new Gson();
			String jsonString = gson.toJson(filteredOwners);
			res.setContentType("application/json;charset=utf-8");
			PrintWriter out = res.getWriter();
			System.out.println("查詢成功");
			out.write(jsonString);
			out.close();
//			}
		} catch (Exception e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		}

	}

	@PostMapping("/update")
	public void updateOwner(HttpServletRequest req, HttpServletResponse res) {
		// 獲取前端回應
		String hotelStatus = req.getParameter("hotelStatus");
		String hotelid = req.getParameter("hotelId");
		Integer hotelId = Integer.valueOf(hotelid);
		// 找到要做更新的hotelId
		HotelOwnerVO vo = hotelOwnerServiceImpl.findByPrimaryKey(hotelId);

		// 因為不會每個項目都更新到,這時候其他沒更新的會回傳null,與資料庫不相符,所以要設判斷
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
