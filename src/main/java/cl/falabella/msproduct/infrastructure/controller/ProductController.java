package cl.falabella.msproduct.infrastructure.controller;

import cl.falabella.msproduct.application.dto.ProductDto;
import cl.falabella.msproduct.application.port.input.IProductController;
import cl.falabella.msproduct.application.port.interactor.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController implements IProductController {
	

	@Autowired
	private IProductService service;

	@Autowired
	public ProductController(IProductService service) {
		this.service = service;
	}

	@Override
	@GetMapping("/{sku}")
	public ResponseEntity<ProductDto> get(@PathVariable("sku") String sku) {
		return new ResponseEntity<>(service.get(sku), HttpStatus.OK);
	}

	@Override
	@GetMapping()
	public ResponseEntity<List<ProductDto>> getAll() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}

	@Override
	@PostMapping("/{sku}")
	public ResponseEntity<ProductDto> save(@RequestBody @Valid ProductDto dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}

	@Override
	@DeleteMapping("/{sku}")
	public ResponseEntity<ProductDto> delete(@RequestBody @Valid ProductDto dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}

}
