package tw.idv.petradisespringboot.animalapplication.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.animalapplication.repo.AdoptedApplicationRepository;
import tw.idv.petradisespringboot.animalapplication.service.AdoptedApplicationService;
import tw.idv.petradisespringboot.animalapplication.vo.AdoptedApplication;



@Service
public class AdoptedApplicationServiceImpl implements AdoptedApplicationService {

	private final AdoptedApplicationRepository repository;

	AdoptedApplicationServiceImpl(AdoptedApplicationRepository repository) {
		this.repository = repository;

	}

	@Override
	public List<AdoptedApplication> getAllAdoptedApplication() {
		
		return repository.findAll();
	}

	@Override
	public AdoptedApplication findAdoptedApplicationById(Integer adoptedId) {
	
		return repository
                .findById(adoptedId)
                .orElseThrow(() -> new AdoptedApplicationNotFoundException(adoptedId));
	}


	@Override
	public AdoptedApplication save(AdoptedApplication adoptedApplication) {
		
		return repository.save(adoptedApplication);
	}

	
	class AdoptedApplicationNotFoundException extends RuntimeException {
		AdoptedApplicationNotFoundException(Integer id) {
			super("找不到會員ID: " + id);
		}
		
	}

	}

	





