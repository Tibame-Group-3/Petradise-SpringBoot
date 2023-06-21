package tw.idv.petradisespringboot.mall.service;

import java.util.List;

import tw.idv.petradisespringboot.mall.model.vo.SaleProject;

public interface SaleProjectService {

	SaleProject addSaleProject(SaleProject saleProject);

	SaleProject updateSaleProject(SaleProject saleProject);

	SaleProject getSaleProjectById(Integer id);

	List<SaleProject> getAllSaleProject();

}
