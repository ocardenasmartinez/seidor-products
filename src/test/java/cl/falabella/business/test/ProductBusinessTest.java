package cl.falabella.business.test;

import cl.falabella.buisness.ProductBusiness;
import cl.falabella.buisness.ProductsBusinessImpl;
import cl.falabella.controllers.exceptions.*;
import cl.falabella.entities.data.ProductsEntity;
import cl.falabella.entities.request.ProductRequest;
import cl.falabella.entities.responses.ListProductResponse;
import cl.falabella.entities.responses.OneProductResponse;
import cl.falabella.entities.responses.ProductResponse;
import cl.falabella.repositories.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductBusinessTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductBusiness productBusiness = new ProductsBusinessImpl();

    @Test
    public void insertProductSKUNull() {
        assertThrows(SKUNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku(null).build());
        });
    }

    @Test
    public void insertProductSKUBadFormat() {
        assertThrows(SKUFormatException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku("").build());
        });
    }

    @Test
    public void insertProductNameNull() {
        assertThrows(NameNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku("FAL-1000000").name(null).build());
        });
    }

    @Test
    public void insertProductBrandNull() {
        assertThrows(BrandNullException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder().sku("FAL-1000000").name("").brand(null).build());
        });
    }

    @Test
    public void insertProductSizeNull() {
        assertThrows(SizeNullException.class, () -> {
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
        assertThrows(PriceNullException.class, () -> {
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
        assertThrows(ImageNullException.class, () -> {
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
        assertThrows(NameFormatException.class, () -> {
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
        assertThrows(BrandFormatException.class, () -> {
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
        assertThrows(PriceFormatException.class, () -> {
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
    public void insertProductImageBadFormat() {
        assertThrows(ImageFormatException.class, () -> {
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

    @Test
    public void insertProductProductExist() {
        when(productsRepository.findBySku(any(String.class))).thenReturn(ProductsEntity.builder().build());
        assertThrows(ProductExistException.class, () -> {
            productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("name")
                    .brand("brand")
                    .size("")
                    .price(555D)
                    .image("http://localhost:8080/")
                    .build());
        });
    }

    @Test
    public void insertProductOK() throws Exception {
        when(productsRepository.findBySku(any(String.class))).thenReturn(null);
        assertEquals(productBusiness.insertProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("name")
                    .brand("brand")
                    .size("")
                    .price(555D)
                    .image("http://localhost:8080/")
                    .build()), ProductResponse.builder().state("OK").build());

    }

    @Test
    public void updateProductNotExist() {
        when(productsRepository.findBySku(any(String.class))).thenReturn(null);
        assertThrows(ProductNotExistException.class, () -> {
            productBusiness.updateProduct(ProductRequest.builder()
                    .sku("FAL-1000000")
                    .name("name")
                    .brand("brand")
                    .size("")
                    .price(555D)
                    .image("http://localhost:8080/")
                    .build());
        });
    }

    @Test
    public void updateProductOK() throws Exception {
        when(productsRepository.findBySku(any(String.class))).thenReturn(ProductsEntity.builder().build());
        assertEquals(productBusiness.updateProduct(ProductRequest.builder()
            .sku("FAL-1000000")
            .name("name")
            .brand("brand")
            .size("")
            .price(555D)
            .image("http://localhost:8080/")
            .build()), ProductResponse.builder().state("OK").build());

    }

    @Test
    public void getProductNotExist() {
        when(productsRepository.findBySku(any(String.class))).thenReturn(null);
        assertThrows(ProductNotExistException.class, () -> {
            productBusiness.getProduct("");
        });
    }

    @Test
    public void getProductOK() throws Exception {
        when(productsRepository.findBySku(any(String.class))).thenReturn(createProductEntity());
        assertEquals(productBusiness.getProduct(""), createOneProductRequest());
    }

    @Test
    public void getAllProductOK() throws Exception {
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        productsEntityList.add(createProductEntity());
        when(productsRepository.findAll()).thenReturn(productsEntityList);
        assertEquals(productBusiness.getProducts(), createListProductResponse());
    }

    @Test
    public void deleteProductNotExist() {
        when(productsRepository.findBySku(any(String.class))).thenReturn(null);
        assertThrows(ProductNotExistException.class, () -> {
            productBusiness.deleteProduct("");
        });
    }

    @Test
    public void deleteProductOK() throws Exception {
        when(productsRepository.findBySku(any(String.class))).thenReturn(createProductEntity());
        assertEquals(productBusiness.deleteProduct(""), ProductResponse.builder().state("OK").build());
    }

    private ProductsEntity createProductEntity() {
        return ProductsEntity.builder().sku("").image("").price(0D).image("").size("").brand("").name("").build();
    }

    private OneProductResponse createOneProductRequest() {
        return OneProductResponse.builder().sku("").image("").price(0D).image("").size("").brand("").name("").build();
    }

    private ListProductResponse createListProductResponse() {
        List<OneProductResponse> oneProductResponseList = new ArrayList<>();
        oneProductResponseList.add(createOneProductRequest());
        return ListProductResponse.builder().product(oneProductResponseList).build();
    }

}
