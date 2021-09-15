package cl.falabella.msproduct.application.port.output.db;

import cl.falabella.msproduct.application.dto.ProductDto;
import cl.falabella.msproduct.domain.entity.Product;

import java.util.List;

public interface IProductDb {

    Product get(String sku);

    List<Product> getAll();

    Product save(Product product);
    Product delete(String sku);
}
