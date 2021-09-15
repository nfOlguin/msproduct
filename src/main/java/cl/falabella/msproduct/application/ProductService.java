package cl.falabella.msproduct.application;


import cl.falabella.msproduct.application.dto.ProductDto;
import cl.falabella.msproduct.application.port.interactor.IProductService;
import cl.falabella.msproduct.application.port.output.db.*;
import cl.falabella.msproduct.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDb productDb;

    @Autowired
    private IProductOptionalImageDb optionalImageDb;

    @Override
    public ProductDto get(String sku) {

        Product product = productDb.get(sku);

        if (product == null) {
            throw new ApplicationException("Error. Product not found!", HttpStatus.NOT_FOUND);
        }

        ProductDto out = new ProductDto();
        out.setSku(product.getSku());
        out.setName(product.getName());
        out.setBrand(product.getBrand());
        out.setPrice(product.getPrice());
        out.setImage(product.getImageUrl());

        List<ProductOptionalImage> optionalImageList = optionalImageDb.getAll(product.getId());

        if (optionalImageList != null) {
            List<String> OptionalImagesList = new ArrayList<>();
            for (ProductOptionalImage image : optionalImageList) {
                OptionalImagesList.add(image.getUrl());
            }
            out.setOtherProductOptionalImages(OptionalImagesList);
        }

        return out;
    }


    @Override
    public List<ProductDto> getAll() {

        List<Product> products = productDb.getAll();
        if (products.isEmpty()) {
            throw new ApplicationException("Error. No product found!", HttpStatus.NOT_FOUND);
        }

        List<ProductDto> response = new ArrayList<>();

        for (Product product : products) {
            ProductDto out = new ProductDto();
            out.setSku(product.getSku());
            out.setName(product.getName());
            out.setBrand(product.getBrand());
            out.setPrice(product.getPrice());
            out.setImage(product.getImageUrl());

            List<ProductOptionalImage> optionalImageList = optionalImageDb.getAll(product.getId());

            if (optionalImageList != null) {
                List<String> OptionalImagesList = new ArrayList<>();
                for (ProductOptionalImage image : optionalImageList) {
                    OptionalImagesList.add(image.getUrl());
                }
                out.setOtherProductOptionalImages(OptionalImagesList);

            }
            response.add(out);
        }
        return response;
    }

    @Override
    public ProductDto save(ProductDto productIn) {
        Product product = new Product();
        product.setSku(productIn.getSku());
        product.setBrand(productIn.getBrand());
        product.setName(productIn.getName());
        product.setSize(productIn.getSize().trim());
        product.setPrice(productIn.getPrice());
        product.setImageUrl(productIn.getImage().trim());
        product.setCreatedDate(LocalDateTime.now());
        product = productDb.save(product);

        if (!productIn.getOtherProductOptionalImages().isEmpty()) {
            List<ProductOptionalImage> imageList = new ArrayList<>();

            for (String image : productIn.getOtherProductOptionalImages()) {
                ProductOptionalImage optionalImage = new ProductOptionalImage();

                optionalImage.setIdProduct(product.getId());
                optionalImage.setUrl(image);
                optionalImage.setCreatedDate(LocalDateTime.now());

                imageList.add(optionalImage);
            }

            optionalImageDb.saveAll(product.getId(), imageList);
        }
        return productIn;

    }

}
