package tw.idv.petradisespringboot.lostpetresponse.service;

import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

public interface LostPetResponseService {
	
	LostPetResponse add(LostPetResponse lostPetResponse);
	
	LostPetResponse remove(LostPetResponse lostPetResponse);
	
	LostPetResponse eidt(LostPetResponse lostPetResponse);
	
	LostPetResponse findById(Integer id);
	
	
}
