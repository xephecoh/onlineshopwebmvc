package com.khamutov.dao.jdbc;

import com.khamutov.dao.ProductDao;
import com.khamutov.entities.Product;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {
    private final QueryExecutor queryExecutor;

    @Autowired
    public JdbcProductDao( PGSimpleDataSource dataSource) {
        this.queryExecutor = new QueryExecutor(dataSource);
    }

    public List<Product> findAllProducts() {
        return queryExecutor.getAllProducts();
    }

    public void delete(int id) {
        queryExecutor.deleteItem(id);
    }

    public boolean update(Product product) {
        return queryExecutor.update(product);
    }

    public void save(Product product) {
        queryExecutor.save(product);
    }
}
