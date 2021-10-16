package cl.falabella.entities.data;

import lombok.*;

import javax.persistence.*;

@Entity(name = "products")
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String sku;
    @Column
    private String name;
    @Column
    private String brand;
    @Column
    private String size;
    @Column
    private Double price;
    @Column
    private String image;

}
