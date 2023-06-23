package tw.idv.petradisespringboot.lostpetresponse.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.lostpetarticle.repo.LostPetArticleRepository;
import tw.idv.petradisespringboot.lostpetarticle.vo.LostPetArticle;
import tw.idv.petradisespringboot.lostpetresponse.controller.LostPetResponseDTO;
import tw.idv.petradisespringboot.lostpetresponse.repo.LostPetResponseRepo;
import tw.idv.petradisespringboot.lostpetresponse.service.LostPetResponseService;
import tw.idv.petradisespringboot.lostpetresponse.vo.LostPetResponse;

@Service
public class LostPetResponseServiceImpl implements LostPetResponseService{

	private LostPetResponseRepo lostPetResponseRepo;

	private LostPetArticleRepository articleRepository;
	
	public LostPetResponseServiceImpl(LostPetResponseRepo lostPetResponseRepo,
			LostPetArticleRepository articleRepository) {
		this.lostPetResponseRepo = lostPetResponseRepo;
		this.articleRepository = articleRepository;
	}
	
	@Transactional
	@Override
	public LostPetResponseDTO add(LostPetResponseDTO responseDTO) {
		var response = responseDTO.getResponse();
		var newResponse = new LostPetResponse();
		var responseID = responseDTO;
        var articleID = new LostPetArticle().getArticleId();
        responseID.setArticleId(articleID);
        responseID.setResponse(response);
        


        return responseID;
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

	@Override
	public LostPetResponse findById(Integer id) {
		return lostPetResponseRepo.findById(id).orElseThrow();
	}


	

}
