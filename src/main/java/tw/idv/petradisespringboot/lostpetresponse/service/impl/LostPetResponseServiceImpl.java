package tw.idv.petradisespringboot.lostpetresponse.service.impl;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.lostpetresponse.repo.LostPetResponseRepo;
import tw.idv.petradisespringboot.lostpetresponse.service.LostPetResponseService;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@Service
public class LostPetResponseServiceImpl implements LostPetResponseService{

	private LostPetResponseRepo lostPetResponseRepo;
	
	public LostPetResponseServiceImpl(LostPetResponseRepo lostPetResponseRepo) {
		this.lostPetResponseRepo = lostPetResponseRepo;
	}

	@Override
	public LostPetResponse add(LostPetResponse lostPetResponse) {
		return lostPetResponseRepo.save(lostPetResponse);
	}

	@Override
	public LostPetResponse remove(LostPetResponse lostPetResponse) {
		lostPetResponseRepo.delete(lostPetResponse);
		System.out.println("success delete!");
		return null;
	}

	@Override
	public LostPetResponse eidt(LostPetResponse lostPetResponse) {
		return lostPetResponseRepo.save(lostPetResponse);
	}


	

}
