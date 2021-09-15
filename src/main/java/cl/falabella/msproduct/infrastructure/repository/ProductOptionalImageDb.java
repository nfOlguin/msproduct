package cl.falabella.msproduct.infrastructure.repository;

import cl.falabella.msproduct.application.port.output.db.IProductOptionalImageDb;
import cl.falabella.msproduct.domain.entity.ProductOptionalImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class ProductOptionalImageDb implements IProductOptionalImageDb {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductOptionalImageDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductOptionalImage get(Long productId) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT opi.id, opi.idproduct, opi.createddate, opi.updatedDate, opi.active ");
        query.append("FROM productoptionalimage o INNER JOIN product p ON p.id = opi.idproduct  ");
        query.append("WHERE p.id = ? ");

        return DataAccessUtils.singleResult(jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<>(ProductOptionalImage.class), productId));
    }

    @Override
    public ProductOptionalImage save(ProductOptionalImage optionalImage) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO optionalimage ( ");
        query.append("idproduct, ");
        query.append("createddate, ");
        query.append("active ) ");
        query.append("VALUES (?, ?, ?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query.toString(), new String[]{"id"});
            ps.setObject(1, optionalImage.getIdProduct());
            ps.setObject(2, LocalDateTime.now());
            ps.setObject(3, true);
            return ps;
        }, keyHolder);

        optionalImage.setId(keyHolder.getKey().longValue());

        return optionalImage;
    }
}
