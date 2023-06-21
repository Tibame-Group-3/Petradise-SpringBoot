package tw.idv.petradisespringboot.animlpic.service;

import java.util.List;

import tw.idv.petradisespringboot.animlpic.vo.AnimalPic;



public interface AnimalPicService {
	
	
	List<AnimalPic> getAllAnimalpic();
	

	AnimalPic findAnimalpicById(Integer id);
	
	
	AnimalPic save (AnimalPic animalpic);


	



	

  


	
}
