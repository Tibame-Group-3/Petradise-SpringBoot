package tw.idv.petradisespringboot.mall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.mall.model.repo.SaleProjectRepository;
import tw.idv.petradisespringboot.mall.model.vo.SaleProject;
import tw.idv.petradisespringboot.mall.service.SaleProjectService;

@Service
public class SaleProjectServiceImpl implements SaleProjectService {

	private final SaleProjectRepository saleProjectRepository;

	public SaleProjectServiceImpl(SaleProjectRepository saleProjectRepository) {
		this.saleProjectRepository = saleProjectRepository;
	}

	@Override
	public SaleProject add(SaleProject saleProject) {
		return saleProjectRepository.save(saleProject);
	}

	@Override
	public SaleProject update(SaleProject saleProject) {
		return saleProjectRepository.save(saleProject);
	}

	@Override
	public SaleProject findById(Integer id) {
		return saleProjectRepository.findById(id).orElseThrow(() -> new SaleProjectNotFoundException(id));
	}

	@Override
	public List<SaleProject> findAll() {
		return saleProjectRepository.findAll();
	}

}

class SaleProjectNotFoundException extends RuntimeException {
	SaleProjectNotFoundException(Integer id) {
		super("SaleProject not found, id: " + id);
	}
}
