package tw.idv.petradisespringboot.hotel_owner.controller;

import java.util.Arrays;
import java.util.Base64;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/")
public class OwnerSignUpController {
	
	@Autowired
	private  HotelOwnerServiceImpl hotelOwnerServiceImpl;
	
	
	@PostMapping("/SignUp")
	public void insertOwners(@RequestBody HotelOwnerVO hotelOwnerVO) {
		try {
		byte[]imgData = Base64.getDecoder().decode(hotelOwnerVO.getBase64Image());
		hotelOwnerVO.setHotelLicPic(imgData);
		hotelOwnerServiceImpl.insert(hotelOwnerVO);
		
		 System.out.println("Image data: " + Arrays.toString(imgData));
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
		}
	}
}
