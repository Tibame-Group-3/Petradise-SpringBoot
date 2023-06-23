package tw.idv.petradisespringboot.lostpetresponse.service;

import tw.idv.petradisespringboot.lostpetresponse.controller.LostPetResponseDTO;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

public interface LostPetResponseService {
	
	LostPetResponseDTO add(LostPetResponseDTO lostPetResponseDTO);
	
	LostPetResponse remove(LostPetResponse lostPetResponse);
	
	LostPetResponse eidt(LostPetResponse lostPetResponse);
	
	LostPetResponse findById(Integer id);
	
	
	
}
