package tw.idv.petradisespringboot.HotelOwner.repo;


import java.util.List;

import tw.idv.petradisespringboot.HotelOwner.vo.HotelOwnerVO;

public interface HotelOwnerDAO_interface {
          public void insert(HotelOwnerVO  hotelOwnerVO);
          public void update(HotelOwnerVO  hotelOwnerVO);
          public void delete(Integer hotelId); 
          public HotelOwnerVO  findByPrimaryKey(Integer hotelId);
          public List<HotelOwnerVO> getAll();
        
}
