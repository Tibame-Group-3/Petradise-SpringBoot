package tw.idv.petradisespringboot.mall.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.idv.petradisespringboot.mall.model.vo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
