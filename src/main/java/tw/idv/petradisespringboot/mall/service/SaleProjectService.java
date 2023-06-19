package tw.idv.petradisespringboot.mall.service;

import java.util.List;
import tw.idv.petradisespringboot.mall.po.SaleProject;

public interface SaleProjectService {

	SaleProject add(SaleProject saleProject);

	SaleProject update(SaleProject saleProject);

	SaleProject findById(Integer id);

	List<SaleProject> findAll();

}
