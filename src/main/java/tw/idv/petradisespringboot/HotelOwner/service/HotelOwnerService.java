package tw.idv.petradisespringboot.HotelOwner.service;

import java.util.List;

import tw.idv.petradisespringboot.HotelOwner.repo.HotelOwnerDAO_interface;
import tw.idv.petradisespringboot.HotelOwner.repo.HotelOwnerDao;
import tw.idv.petradisespringboot.HotelOwner.vo.HotelOwnerVO;

public class HotelOwnerService {
	private HotelOwnerDAO_interface dao;// 先用介面宣告參數,取得見面內所有方法

	// 建構子 初始化dao
	public HotelOwnerService() {
		dao = new HotelOwnerDao();// 多形,子類址傳給dao,這樣dao就擁有介面的方法跟子類的方法
	}

	
	//新增
	public HotelOwnerVO addHotleOwner(String hotelName, String hotelAddress, String hotelStatus, String hotelLicId,
			byte[] hotelLicPic, Integer reviewScorePeople, Integer reviewScoreTotal, String ownerAccount,
			String ownerPassword, String ownerName, String ownerId, String ownerBank, String ownerPhone,
			String ownerEmail, String ownerAccess) {

		HotelOwnerVO hotelOwnerVO = new HotelOwnerVO();

		hotelOwnerVO.setHotelName(hotelName);
		hotelOwnerVO.setHotelAddress(hotelAddress);
		hotelOwnerVO.setHotelStatus(hotelStatus);
		hotelOwnerVO.setHotelLicId(hotelLicId);
		hotelOwnerVO.setHotelLicPic(hotelLicPic);
		hotelOwnerVO.setReviewScorePeople(reviewScorePeople);
		hotelOwnerVO.setReviewScoreTotal(reviewScoreTotal);
		hotelOwnerVO.setOwnerAccount(ownerAccount);
		hotelOwnerVO.setOwnerPassword(ownerPassword);
		hotelOwnerVO.setOwnerName(ownerName);
		hotelOwnerVO.setOwnerId(ownerId);
		hotelOwnerVO.setOwnerBank(ownerBank);
		hotelOwnerVO.setOwnerPhone(ownerPhone);
		hotelOwnerVO.setOwnerEmail(ownerEmail);
		hotelOwnerVO.setOwnerAccess(ownerAccess);
		
		
		dao.insert(hotelOwnerVO);

		return hotelOwnerVO;

	}

	//修改
	public HotelOwnerVO updateHotelOwner(Integer hotelId,String hotelName, String hotelAddress, String hotelStatus, String hotelLicId,
			byte[] hotelLicPic, Integer reviewScorePeople, Integer reviewScoreTotal, String ownerAccount,
			String ownerPassword, String ownerName, String ownerId, String ownerBank, String ownerPhone,
			String ownerEmail, String ownerAccess) {

		HotelOwnerVO hotelOwnerVO = new HotelOwnerVO();
		
		hotelOwnerVO.setHotelId(hotelId);
		hotelOwnerVO.setHotelName(hotelName);
		hotelOwnerVO.setHotelAddress(hotelAddress);
		hotelOwnerVO.setHotelStatus(hotelStatus);
		hotelOwnerVO.setHotelLicId(hotelLicId);
		hotelOwnerVO.setHotelLicPic(hotelLicPic);
		hotelOwnerVO.setReviewScorePeople(reviewScorePeople);
		hotelOwnerVO.setReviewScoreTotal(reviewScoreTotal);
		hotelOwnerVO.setOwnerAccount(ownerAccount);
		hotelOwnerVO.setOwnerPassword(ownerPassword);
		hotelOwnerVO.setOwnerName(ownerName);
		hotelOwnerVO.setOwnerId(ownerId);
		hotelOwnerVO.setOwnerBank(ownerBank);
		hotelOwnerVO.setOwnerPhone(ownerPhone);
		hotelOwnerVO.setOwnerEmail(ownerEmail);
		hotelOwnerVO.setOwnerAccess(ownerAccess);
		dao.update(hotelOwnerVO);
		
		return hotelOwnerVO;

	}
	//刪除 (只用hotelId去刪,所以不用拿類別建方法)
	public void deleteHotelOwner(Integer hotelId) {
		dao.delete(hotelId);
	}
	
	public HotelOwnerVO getOneHotelOwner(Integer hotelId) {
		return dao.findByPrimaryKey(hotelId);
	}
	public List<HotelOwnerVO> getAll(Integer hotelId){
		return dao.getAll();
	}

}
