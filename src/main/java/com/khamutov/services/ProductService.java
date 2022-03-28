package com.khamutov.services;

import com.khamutov.dao.ProductDao;
import com.khamutov.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getProductList() {
        return (List<Product>) productDao.findAllProducts();
    }

    public void deleteById(int id) {
        productDao.delete(id);
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public boolean update(Product product) {
        return productDao.update(product);
    }
}
