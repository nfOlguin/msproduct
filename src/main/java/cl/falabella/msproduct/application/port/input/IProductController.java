package cl.falabella.msproduct.application.port.input;


import cl.falabella.msproduct.application.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IProductController {

    ResponseEntity<ProductDto> get(@PathVariable("sku") String sku);

    ResponseEntity<List<ProductDto>> getAll();

    ResponseEntity<ProductDto> save(@RequestBody @Valid ProductDto dto);

    ResponseEntity<ProductDto> delete(@RequestBody @Valid ProductDto dto);

}