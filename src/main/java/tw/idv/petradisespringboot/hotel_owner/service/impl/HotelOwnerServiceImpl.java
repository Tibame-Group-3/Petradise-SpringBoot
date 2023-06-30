package tw.idv.petradisespringboot.hotel_owner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.petradisespringboot.hotel_owner.exceptions.AccountNotFoundException;
import tw.idv.petradisespringboot.hotel_owner.exceptions.NotVerifiedException;
import tw.idv.petradisespringboot.hotel_owner.repo.HotelOwnerRepository;
import tw.idv.petradisespringboot.hotel_owner.service.HotelOwnerService;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerAccess;
import tw.idv.petradisespringboot.hotel_owner.vo.HotelOwnerVO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HotelOwnerServiceImpl implements HotelOwnerService {

	private final HotelOwnerRepository hotelOwnerRepository;

	@Autowired
	public HotelOwnerServiceImpl(HotelOwnerRepository hotelOwnerRepository) {
		this.hotelOwnerRepository = hotelOwnerRepository;
	}

	@Override
	public void insert(HotelOwnerVO hotelOwnerVO) {
		if (hotelOwnerVO.getOwnerName() == null || hotelOwnerVO.getOwnerName().isEmpty()) {
			throw new IllegalArgumentException("業主名稱不能為空");
		}
		if (hotelOwnerVO.getOwnerPhone() == null || hotelOwnerVO.getOwnerPhone().isEmpty()) {
			throw new IllegalArgumentException("電話不能為空");
		}
		if (hotelOwnerVO.getOwnerId() == null || hotelOwnerVO.getOwnerId().isEmpty()) {
			throw new IllegalArgumentException("身分證不能為空");
		}
		if (hotelOwnerVO.getOwnerEmail() == null || hotelOwnerVO.getOwnerEmail().isEmpty()) {
			throw new IllegalArgumentException("信箱不能為空");
		}
		if (hotelOwnerVO.getOwnerPassword() == null || hotelOwnerVO.getOwnerPassword().isEmpty()) {
			throw new IllegalArgumentException("密碼不能為空");
		}
		if (hotelOwnerVO.getOwnerBank() == null || hotelOwnerVO.getOwnerBank().isEmpty()) {
			throw new IllegalArgumentException("密碼不能為空");
		}
		if (hotelOwnerVO.getHotelName() == null || hotelOwnerVO.getHotelName().isEmpty()) {
			throw new IllegalArgumentException("旅館名稱不能為空");
		}
		if (hotelOwnerVO.getOwnerAccount() == null || hotelOwnerVO.getOwnerAccount().isEmpty()) {
			throw new IllegalArgumentException("統一編號不能為空");
		}
		if (hotelOwnerVO.getHotelAddress() == null || hotelOwnerVO.getHotelAddress().isEmpty()) {
			throw new IllegalArgumentException("地址不能為空");
		}
		if (hotelOwnerVO.getHotelLicId() == null || hotelOwnerVO.getHotelLicId().isEmpty()) {
			throw new IllegalArgumentException("證號不能為空");
		}
		if (hotelOwnerVO.getHotelLicPic() == null || hotelOwnerVO.getHotelLicPic().length == 0) {
			throw new IllegalArgumentException("照片不能為空");
		}
		hotelOwnerRepository.save(hotelOwnerVO);
	}

	@Override
	public void delete(Integer hotelId) {
		hotelOwnerRepository.deleteById(hotelId);
	}

	@Override
	public void update(HotelOwnerVO hotelOwnerVO) {
		hotelOwnerRepository.save(hotelOwnerVO);
	}

	@Override
	public HotelOwnerVO findByPrimaryKey(Integer hotelId) {
		return hotelOwnerRepository.findById(hotelId).orElse(null);
	}

	@Override
	public List<HotelOwnerVO> getAll() {
		return hotelOwnerRepository.findAll();
	}

	@Override
	public List<HotelOwnerVO> getStatus(String hotelStatus) {
		List<HotelOwnerVO> list = getAll();
		// 過濾status為2的選項
		list = list.stream().filter(vo -> "0".equals(vo.getHotelStatus())).collect(Collectors.toList());
		return list;
	}

	@Override
	public void updateOwnerStatus(Integer hotelId, String hotelStatus) {
		HotelOwnerVO vo = findByPrimaryKey(hotelId);

		// 因為不會每個項目都更新到,這時候其他沒更新的會回傳null,與資料庫不相符,所以要設判斷
		if (vo != null) {
			vo.setHotelStatus(hotelStatus);
			vo.setHotelId(hotelId);
			hotelOwnerRepository.save(vo);
		} else {
			throw new NoSuchElementException("Hotel not found with id: " + hotelId);
		}
	}

	@Override
	public HotelOwnerVO login(String account, String password) {
		var optionalVO = hotelOwnerRepository.findByOwnerAccountAndOwnerPassword(account, password);
		if (optionalVO.isEmpty()) {
			throw new AccountNotFoundException("帳號或密碼錯誤");
		}
		final var vo = optionalVO.get();
		if (Objects.equals(vo.getOwnerAccess(), HotelOwnerAccess.SUSPENDED)) {
			throw new AccountNotFoundException("帳號已被停權");
		}
		if (Objects.equals(vo.getOwnerAccess(), HotelOwnerAccess.NOT_VERIFIED)) {
			throw new NotVerifiedException("帳號尚未驗證");
		}
		return vo;
	}

}
