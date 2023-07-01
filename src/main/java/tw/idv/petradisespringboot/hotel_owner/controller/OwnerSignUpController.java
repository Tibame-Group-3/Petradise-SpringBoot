package tw.idv.petradisespringboot.hotel_owner.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void insertOwner(HttpServletRequest req, @RequestBody HotelOwnerVO hotelOwnerVO) {

		HttpSession session = req.getSession();

		try {
			byte[] imgData = Base64.getDecoder().decode(hotelOwnerVO.getBase64Image());
			hotelOwnerVO.setHotelLicPic(imgData);
			session.setAttribute("hotel_owner", hotelOwnerVO);
			System.out.println("Image data: " + Arrays.toString(imgData));
			hotelOwnerServiceImpl.insert(hotelOwnerVO);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
	}

	@PostMapping("/checkMail")
	public void checkMail(HttpServletRequest req, @RequestBody Map<String, String> payload) {
		HttpSession session = req.getSession();
		String token = UUID.randomUUID().toString().substring(0, 6);
		String subject = "Email Verification";
		String text = "Your verification code is: " + token;
		String owenrEmail = payload.get("ownerEmail");
		emailService.sendEmail(owenrEmail, subject, text);
		session.setAttribute("email_verification_token", token);
		session.setAttribute("email", owenrEmail);
	}

	@PostMapping("/verify-email")
	public String verifyEmail(HttpServletRequest req, @RequestParam("token") String token) {
		HttpSession session = req.getSession();

		String storedToken = (String) session.getAttribute("email_verification_token");
		String email = (String) session.getAttribute("email");

		if (token.equals(storedToken)) {
			String subject = "註冊成功通知";
			String text = "請耐心等候審核通知～";
			emailService.sendEmail(email, subject, text);

			return "信箱驗證成功";
		}

		return "信箱驗證失敗";
	}

}
