package tw.idv.petradisespringboot.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.product.model.service.ProductServiceImpl;
import tw.idv.petradisespringboot.product.model.vo.Product;

import java.util.List;

@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/product/add")
    public Product insert(@RequestBody Product product) {
        return productService.insert(product);
    }

    @PutMapping("/product/update/{pdId}")
    public Product update(@PathVariable("pdId") Integer pdId, @RequestBody Product product) {
        Product existingProduct = productService.getOneById(pdId);
        if (existingProduct != null) {
            product.setPdId(pdId);
            productService.update(product);
        }
        return product;
    }

    @GetMapping("/product/get/{pdId}")
    public Product getPdById(@PathVariable("pdId") Integer pdId) {
        return productService.getOneById(pdId);
    }

    @GetMapping("/product/get/all")
    public List<Product> getAll() {
        return productService.getAll();
    }


}
