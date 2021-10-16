package cl.falabella.entities.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String sku;
    private String name;
    private String brand;
    private String size;
    private Double price;
    private String image;

}
