package tw.idv.petradisespringboot.product.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.idv.petradisespringboot.product.model.vo.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
