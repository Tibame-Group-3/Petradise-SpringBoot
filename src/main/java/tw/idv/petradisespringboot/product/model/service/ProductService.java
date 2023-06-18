package tw.idv.petradisespringboot.product.model.service;

import tw.idv.petradisespringboot.product.model.vo.Product;

import java.util.List;

public interface ProductService {
    Product insert(Product newProduct);
    Product update(Product product);
    Product getOneById(Integer productId);
    List<Product> getAll();
}
