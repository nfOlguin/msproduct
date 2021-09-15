package cl.falabella.msproduct.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    @NotNull
    //validar max min sku incluye caracteres
    @Min(1000000)
    @Max(99999999)
    private String sku;

    @NotNull
    @NotBlank(message = "name of product must not be blank!")
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @NotBlank(message = "name of product must not be blank!")
    @Size(min = 3, max = 50)
    private String brand;

    @NotBlank(message = "size of name must not be blank!")
    private String size;

    @NotNull(message = "The product's price cannot be null.")
    @Size(min = 1, max =  99999999)
    private Long price;

    @NotNull
    private String image;

    private List<String> otherProductOptionalImages;
}
