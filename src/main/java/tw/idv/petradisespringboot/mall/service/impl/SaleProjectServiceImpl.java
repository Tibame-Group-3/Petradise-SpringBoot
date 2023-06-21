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
	public SaleProject addSaleProject(SaleProject saleProject) {
		return saleProjectRepository.save(saleProject);
	}

	@Override
	public SaleProject updateSaleProject(SaleProject saleProject) {
		return saleProjectRepository.save(saleProject);
	}

	@Override
	public SaleProject getSaleProjectById(Integer saleProId) {
		return saleProjectRepository.findById(saleProId).orElseThrow(() -> new SaleProjectNotFoundException(saleProId));
	}

	@Override
	public List<SaleProject> getAllSaleProject() {
		return saleProjectRepository.findAll();
	}

}

class SaleProjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6301165073809004299L;

	SaleProjectNotFoundException(Integer saleProId) {
		super("SaleProject not found, id: " + saleProId);
	}
}
