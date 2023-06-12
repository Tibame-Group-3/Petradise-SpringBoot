package tw.idv.petradisespringboot.HotelOwner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;

import tw.idv.petradisespringboot.HotelOwner.repo.HotelOwnerDao;
import tw.idv.petradisespringboot.HotelOwner.vo.HotelOwnerVO;

@Controller
public class OwnerManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@RequestMapping("/vendors/OwnerManage")
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/** 查資料 **/
			HotelOwnerDao dao = new HotelOwnerDao();
			// 呼叫 DAO 的 getAll 方法，獲取資料
			List<HotelOwnerVO> list = dao.getAll();
			for (HotelOwnerVO vo : list) {
				// 只需獲取圖片的數組
				byte[] imageBytes = vo.getHotelLicPic(); // 假设 getImage() 方法用于获取图片字段的字节数组
				// 只要圖片不為空 就轉為Base64
				if (imageBytes != null) {
					String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

					// VO那邊建一個getter和setter的Base64,好讓下方可放進資料
					vo.setImageBase64(imageBase64);
				}
			}

			// 將 list 轉換為 JSON 字串
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			// 設置回應的內容類型為 application/json
			res.setContentType("application/json;charset=utf-8");
			// 獲取回應的 PrintWriter 物件
			PrintWriter out = res.getWriter();
			System.out.println("OK");
			// 將 JSON 字串寫入回應
			out.write(jsonString);
			// 關閉 PrintWriter
			out.close();

		}
	}

}
