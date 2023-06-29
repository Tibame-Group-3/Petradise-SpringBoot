package tw.idv.petradisespringboot.hotel_owner.controller;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.petradisespringboot.email.EmailService;
import tw.idv.petradisespringboot.hotel_owner.service.impl.HotelOwnerServiceImpl;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

@RestController
@RequestMapping("/ownerSignUP")
public class OwnerSignUpController {

	private final HotelOwnerServiceImpl hotelOwnerServiceImpl;
	private final EmailService emailService;

	@Autowired
	public OwnerSignUpController(HotelOwnerServiceImpl hotelOwnerServiceImpl, EmailService emailService) {
		this.hotelOwnerServiceImpl = hotelOwnerServiceImpl;
		this.emailService = emailService;
	}

	@PostMapping("/insert")
	public void insertOwners(@RequestBody HotelOwnerVO hotelOwnerVO) {
		try {
			byte[] imgData = Base64.getDecoder().decode(hotelOwnerVO.getBase64Image());
			hotelOwnerVO.setHotelLicPic(imgData);
			hotelOwnerServiceImpl.insert(hotelOwnerVO);

			String to = hotelOwnerVO.getOwnerEmail();
			String subject = "註冊成功通知";
			String text = "請耐心等候審核通知～";

			emailService.sendEmail(to, subject, text);

			System.out.println("Image data: " + Arrays.toString(imgData));
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
	}
}
