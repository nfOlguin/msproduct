package cl.falabella.msproduct.application.port.interactor;

import cl.falabella.msproduct.application.dto.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto get(String sku);
    List<ProductDto> getAll();

    ProductDto save(ProductDto dto);



    }
