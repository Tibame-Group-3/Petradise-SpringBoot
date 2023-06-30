package tw.idv.petradisespringboot.hotel_owner.service;

//import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

import java.util.List;

public interface HotelOwnerService {
	// 新增
	void insert(HotelOwnerVO hotelOwnerVO);

	// 刪除
	void delete(Integer hotelId);

	// 修改
	void update(HotelOwnerVO hotelOwnerVO);

	// 查詢單筆資料
	HotelOwnerVO findByPrimaryKey(Integer hotelId);

	// 查詢所有資料
	List<HotelOwnerVO> getAll();

	// 查詢權限
	List<HotelOwnerVO> getStatus(String hotelStatus);

	void updateOwnerStatus(Integer hotelId, String hotelStatus);

	HotelOwnerVO login(String account, String password);
}

//@Service
//public class HotelOwnerService {
//	
//	private final HotelOwnerRepository hotelOwnerRepository;
//	
//	@Autowired
//	public HotelOwnerService(HotelOwnerRepository hotelOwnerRepository) {
//		this.hotelOwnerRepository = hotelOwnerRepository;
//	}
//	
//	// 新增
//    public void insert(HotelOwnerVO hotelOwnerVO) {
//        hotelOwnerRepository.save(hotelOwnerVO);
//    }
//    
//    // 刪除
//    public void delete(Integer hotelId) {
//        hotelOwnerRepository.deleteById(hotelId);
//    }
//    	
//    // 修改
//    public void update(HotelOwnerVO hotelOwnerVO) {
//        hotelOwnerRepository.save(hotelOwnerVO);
//    }
//    
//
//    
//    // 查詢單筆資料
//    public HotelOwnerVO findByPrimaryKey(Integer hotelId) {
//        return hotelOwnerRepository.findById(hotelId).orElse(null);
//    }
//    
//    // 查詢所有資料
//    public List<HotelOwnerVO> getAll() {
//        return hotelOwnerRepository.findAll();
//    }

//============servlet寫法=================//
//	private HotelOwnerDAO_interface dao;// 先用介面宣告參數,取得見面內所有方法

//	// 建構子 初始化dao
//	public HotelOwnerService() {
//		dao = new HotelOwnerDao();// 多形,子類址傳給dao,這樣dao就擁有介面的方法跟子類的方法
//	}
//
//	
//	//新增
//	public HotelOwnerVO addHotleOwner(String hotelName, String hotelAddress, String hotelStatus, String hotelLicId,
//			byte[] hotelLicPic, Integer reviewScorePeople, Integer reviewScoreTotal, String ownerAccount,
//			String ownerPassword, String ownerName, String ownerId, String ownerBank, String ownerPhone,
//			String ownerEmail, String ownerAccess) {
//
//		HotelOwnerVO hotelOwnerVO = new HotelOwnerVO();
//
//		hotelOwnerVO.setHotelName(hotelName);
//		hotelOwnerVO.setHotelAddress(hotelAddress);
//		hotelOwnerVO.setHotelStatus(hotelStatus);
//		hotelOwnerVO.setHotelLicId(hotelLicId);
//		hotelOwnerVO.setHotelLicPic(hotelLicPic);
//		hotelOwnerVO.setReviewScorePeople(reviewScorePeople);
//		hotelOwnerVO.setReviewScoreTotal(reviewScoreTotal);
//		hotelOwnerVO.setOwnerAccount(ownerAccount);
//		hotelOwnerVO.setOwnerPassword(ownerPassword);
//		hotelOwnerVO.setOwnerName(ownerName);
//		hotelOwnerVO.setOwnerId(ownerId);
//		hotelOwnerVO.setOwnerBank(ownerBank);
//		hotelOwnerVO.setOwnerPhone(ownerPhone);
//		hotelOwnerVO.setOwnerEmail(ownerEmail);
//		hotelOwnerVO.setOwnerAccess(ownerAccess);
//		
//		
//		dao.insert(hotelOwnerVO);
//
//		return hotelOwnerVO;
//
//	}
//
//	//修改
//	public HotelOwnerVO updateHotelOwner(Integer hotelId,String hotelName, String hotelAddress, String hotelStatus, String hotelLicId,
//			byte[] hotelLicPic, Integer reviewScorePeople, Integer reviewScoreTotal, String ownerAccount,
//			String ownerPassword, String ownerName, String ownerId, String ownerBank, String ownerPhone,
//			String ownerEmail, String ownerAccess) {
//
//		HotelOwnerVO hotelOwnerVO = new HotelOwnerVO();
//		
//		hotelOwnerVO.setHotelId(hotelId);
//		hotelOwnerVO.setHotelName(hotelName);
//		hotelOwnerVO.setHotelAddress(hotelAddress);
//		hotelOwnerVO.setHotelStatus(hotelStatus);
//		hotelOwnerVO.setHotelLicId(hotelLicId);
//		hotelOwnerVO.setHotelLicPic(hotelLicPic);
//		hotelOwnerVO.setReviewScorePeople(reviewScorePeople);
//		hotelOwnerVO.setReviewScoreTotal(reviewScoreTotal);
//		hotelOwnerVO.setOwnerAccount(ownerAccount);
//		hotelOwnerVO.setOwnerPassword(ownerPassword);
//		hotelOwnerVO.setOwnerName(ownerName);
//		hotelOwnerVO.setOwnerId(ownerId);
//		hotelOwnerVO.setOwnerBank(ownerBank);
//		hotelOwnerVO.setOwnerPhone(ownerPhone);
//		hotelOwnerVO.setOwnerEmail(ownerEmail);
//		hotelOwnerVO.setOwnerAccess(ownerAccess);
//		dao.update(hotelOwnerVO);
//		
//		return hotelOwnerVO;
//
//	}
//	//刪除 (只用hotelId去刪,所以不用拿類別建方法)
//	public void deleteHotelOwner(Integer hotelId) {
//		dao.delete(hotelId);
//	}
//	//查單一
//	public HotelOwnerVO getOneHotelOwner(Integer hotelId) {
//		return dao.findByPrimaryKey(hotelId);
//	}
//	//查全部,跟dao層一樣,所以return直接叫用
//	public List<HotelOwnerVO> getAll(){
//		return dao.getAll();
//	}
//	
//	public HotelOwnerVO updateOwnerAccess(Integer hotelId,String ownerAccess) {
//		HotelOwnerVO hotelOwnerVO = new HotelOwnerVO();
//		hotelOwnerVO.setOwnerAccess(ownerAccess);
//		hotelOwnerVO.setHotelId(hotelId);
//
//		return hotelOwnerVO;
//	}
