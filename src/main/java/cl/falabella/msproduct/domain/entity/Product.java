package cl.falabella.msproduct.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Product {

    private Long id;
    private String sku;
    private String name;
    private String brand;
    private String size;
    private Long price;
    private String imageUrl;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean active;
}
