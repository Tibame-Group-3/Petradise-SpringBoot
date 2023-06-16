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
			// 獲取前端回應
			List<HotelOwnerVO> list = hotelOwnerService.getAll();
			// json
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
//    @GetMapping("/OwnerManage")
//    public void hotelOwnerManage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//        req.setCharacterEncoding("UTF-8");
//        // 前端(Ajax)發送action請求
//        String action = req.getParameter("action");
//
//        if ("getAll".equals(action)) {
//            /** 查資料 **/
//
//            // 呼叫 Service 的 getAll 方法，獲取資料
//            List<HotelOwnerVO> list = hotelOwnerService.getAll();
////            for (HotelOwnerVO vo : list) {
////                // 只需獲取圖片的數組
////                byte[] imageBytes = vo.getHotelLicPic(); // 假设 getImage() 方法用于获取图片字段的字节数组
////                // 只要圖片不為空 就轉為Base64
////                if (imageBytes != null) {
////                    String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
////
////                    // VO那邊建一個getter和setter的Base64,好讓下方可放進資料
////                    vo.setImageBase64(imageBase64);
////                }
////            }
//
//            // 將 list 轉換為 JSON 字串
//            Gson gson = new Gson();
//            String jsonString = gson.toJson(list);
//            // 設置回應的內容類型為 application/json
//            res.setContentType("application/json;charset=utf-8");
//            // 獲取回應的 PrintWriter 物件
//            PrintWriter out = res.getWriter();
//            System.out.println("OK");
//            // 將 JSON 字串寫入回應
//            out.write(jsonString);
//            // 關閉 PrintWriter
//            out.close();
//
//        }
//        if ("update".equals(action)) {
//            /** 修改資料 **/
//
////            String ownerAccess = req.getParameter("ownerAccess");
//            String hotelid = req.getParameter("hotelId");
//
//            Integer hotelId = Integer.valueOf(hotelid);
//            hotelOwnerService.update(null);
//            try {
//                HotelOwnerVO vo = hotelOwnerService.findByPrimaryKey(hotelId);
//                Gson gson = new Gson();
//                String jsonString = gson.toJson(vo);
//                // 設置回應的內容類型為 application/json
//                res.setContentType("application/json;charset=utf-8");
//                // 獲取回應的 PrintWriter 物件
//                PrintWriter out = res.getWriter();
//                System.out.println("修改成功");
//                // 將 JSON 字串寫入回應
//                out.write(jsonString);
//                // 關閉 PrintWriter
//                out.close();
//
//            } catch (Exception e) {
//                System.out.println("修改失敗");
//                e.printStackTrace();
//            }
//        }
//    }
//}
