package cl.falabella.msproduct.application.port.output.db;

import cl.falabella.msproduct.domain.entity.ProductOptionalImage;

public interface IProductOptionalImageDb {
    ProductOptionalImage get(Long productId);
    ProductOptionalImage save(ProductOptionalImage optionalImage);

    }
