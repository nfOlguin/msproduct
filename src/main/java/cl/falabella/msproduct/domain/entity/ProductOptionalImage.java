package cl.falabella.msproduct.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductOptionalImage {

    private Long id;
    private Long idProduct;
    private String url;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean active;
}
