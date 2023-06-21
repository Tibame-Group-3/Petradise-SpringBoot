package tw.idv.petradisespringboot.mall.service;

import java.util.List;

import tw.idv.petradisespringboot.mall.model.vo.Product;

public interface ProductService {
    Product insert(Product newProduct);
    Product update(Product product);
    Product getOneById(Integer productId);
    List<Product> getAll();
}
