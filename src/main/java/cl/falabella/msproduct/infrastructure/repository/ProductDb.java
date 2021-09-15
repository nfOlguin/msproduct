package cl.falabella.msproduct.infrastructure.repository;

import cl.falabella.msproduct.application.port.output.db.IProductDb;
import cl.falabella.msproduct.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductDb implements IProductDb {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    @Override
    public List<Product> getAll() {
        StringBuilder query = new StringBuilder();

        query.append("SELECT sku, name, brand, size, price, image ");
        query.append("createdDate, updatedDate, active ");
        query.append("FROM product ");

        return jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public Product get(String sku) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT sku, name, brand, size, price, image, createdDate, updatedDate, active ");
        query.append("FROM product ");
        query.append("WHERE UPPER(sku) = ? ");

        return DataAccessUtils.singleResult(jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(Product.class), sku));
    }

    @Override
    public Product save(Product product) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO product ( ");
        query.append("sku, ");
        query.append("name, ");
        query.append("brand, ");
        query.append("size, ");
        query.append("price, ");
        query.append("image, ");
        query.append("createdDate, ");
        query.append("active ) ");
        query.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query.toString(), new String[]{"id"});
            ps.setObject(1, product.getSku());
            ps.setObject(2, product.getName());
            ps.setObject(3, product.getBrand());
            ps.setObject(4, product.getSize());
            ps.setObject(5, product.getPrice());
            ps.setObject(6, product.getImageUrl());
            ps.setObject(7, LocalDateTime.now());
            ps.setObject(8, true);
            return ps;
        }, keyHolder);

        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    public Product delete(String sku){
        return null;
    }


}
