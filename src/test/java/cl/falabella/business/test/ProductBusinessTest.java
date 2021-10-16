package cl.falabella.business.test;

import cl.falabella.buisness.ProductBusiness;
import cl.falabella.buisness.ProductsBusinessImpl;
import cl.falabella.controllers.exceptions.*;
import cl.falabella.entities.request.ProductRequest;
import cl.falabella.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductBusinessTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductBusiness productBusiness = new ProductsBusinessImpl();

    @Test
    public void insertProductSKUNull() {
        Assertions.assertThrows(SKUNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku(null).build());
        });
    }

    @Test
    public void insertProductSKUBadFormat() {
        Assertions.assertThrows(SKUFormatException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku("").build());
        });
    }

    @Test
    public void insertProductNameNull() {
        Assertions.assertThrows(NameNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku("FAL-1000000").name(null).build());
        });
    }

    @Test
    public void insertProductBrandNull() {
        Assertions.assertThrows(BrandNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku("FAL-1000000").name("").brand(null).build());
        });
    }

    @Test
    public void insertProductSizeNull() {
        Assertions.assertThrows(SizeNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("")
                    .brand("")
                    .size(null)
                    .build());
        });
    }

    @Test
    public void insertProductPriceNull() {
        Assertions.assertThrows(PriceNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("")
                    .brand("")
                    .size("")
                    .price(null)
                    .build());
        });
    }

    @Test
    public void insertProductImageNull() {
        Assertions.assertThrows(ImageNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("")
                    .brand("")
                    .size("")
                    .price(0D)
                    .image(null)
                    .build());
        });
    }

    @Test
    public void insertProductNameBadFormat() {
        Assertions.assertThrows(NameFormatException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("")
                    .brand("")
                    .size("")
                    .price(0D)
                    .image("")
                    .build());
        });
    }

    @Test
    public void insertProductBrandBadFormat() {
        Assertions.assertThrows(BrandFormatException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("name")
                    .brand("")
                    .size("")
                    .price(0D)
                    .image("")
                    .build());
        });
    }

    @Test
    public void insertProductPriceBadFormat() {
        Assertions.assertThrows(PriceFormatException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("name")
                    .brand("brand")
                    .size("")
                    .price(0D)
                    .image("")
                    .build());
        });
    }

    @Test
    public void insertProductPriceImageBadFormat() {
        Assertions.assertThrows(ImageFormatException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("name")
                    .brand("brand")
                    .size("")
                    .price(555D)
                    .image("")
                    .build());
        });
    }

    //when(universityRepository.findByName(any(String.class))).thenReturn(null);

}
