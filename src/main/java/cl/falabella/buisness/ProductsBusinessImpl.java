package cl.falabella.buisness;

import cl.falabella.controllers.exceptions.*;
import cl.falabella.entities.data.ProductsEntity;
import cl.falabella.entities.request.ProductRequest;
import cl.falabella.entities.responses.ListProductResponse;
import cl.falabella.entities.responses.OneProductResponse;
import cl.falabella.entities.responses.ProductResponse;
import cl.falabella.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ProductsBusinessImpl implements ProductBusiness {

    private static final Predicate<String> GREATER_THAN_TREE = name -> name.length() >= 3;
    private static final Predicate<String> LESS_THAN_FIFTY = name -> name.length() <= 50;
    private static final Predicate<Double> GREATER_THAN_ONE = x -> x >= 1;
    private static final Predicate<Double> LESS_THAN_99999999 = x -> x <= 99999999;
    private static final String OK = "OK";
    public static final String URL_PATTER = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String SKU_PATTER = "^(FAL-)\\d{7,8}$";
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public ProductResponse insertProduct(ProductRequest request) throws Exception {
        validRequestProduct(request);
        if(ofNullable(productsRepository.findBySku(request.getSku())).isPresent()) throw new ProductExistException();
        saveProduct(request);
        return ProductResponse.builder().state(OK).build();
    }

    @Override
    public ProductResponse updateProduct(ProductRequest request) throws Exception {
        validRequestProduct(request);
        ProductsEntity productsEntity = productsRepository.findBySku(request.getSku());
        if(!ofNullable(productsEntity).isPresent()) throw new ProductNotExistException();
        productsRepository.save(ProductsEntity.builder()
                .id(productsEntity.getId())
                .sku(request.getSku())
                .name(request.getName())
                .brand(request.getBrand())
                .size(request.getSize())
                .price(request.getPrice())
                .image(request.getImage())
                .build());
        return ProductResponse.builder().state(OK).build();
    }

    @Override
    public OneProductResponse getProduct(String sku) throws Exception {
        ProductsEntity productsEntity = productsRepository.findBySku(sku);
        if(!ofNullable(productsEntity).isPresent()) throw new ProductNotExistException();
        return OneProductResponse.builder()
                .sku(productsEntity.getSku())
                .name(productsEntity.getName())
                .brand(productsEntity.getBrand())
                .size(productsEntity.getBrand())
                .price(productsEntity.getPrice())
                .image(productsEntity.getImage())
                .build();
    }

    @Override
    public ListProductResponse getProducts() {
        final List<OneProductResponse> products = new ArrayList<>();
        productsRepository.findAll().forEach(getProductsEntityConsumer(products));
        return ListProductResponse.builder().product(products).build();
    }

    private Consumer<ProductsEntity> getProductsEntityConsumer(List<OneProductResponse> products) {
        return product -> {
            products.add(OneProductResponse.builder()
                    .sku(product.getSku())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .size(product.getBrand())
                    .price(product.getPrice())
                    .image(product.getImage())
                    .build());
        };
    }

    @Override
    public ProductResponse deleteProduct(String sku) throws Exception {
        ProductsEntity productsEntity = productsRepository.findBySku(sku);
        if(!ofNullable(productsEntity).isPresent()) throw new ProductNotExistException();
        productsRepository.delete(productsEntity);
        return ProductResponse.builder().state(OK).build();
    }


    private ProductsEntity saveProduct(ProductRequest request) {
        return productsRepository.save(ProductsEntity.builder()
                .sku(request.getSku())
                .brand(request.getBrand())
                .name(request.getName())
                .price(request.getPrice())
                .size(request.getSize())
                .image(request.getImage()).build());
    }

    private void validRequestProduct(ProductRequest request) throws Exception {
        ofNullable(request.getSku()).orElseThrow(SKUNullException::new);
        if(!Pattern.compile(SKU_PATTER).matcher(request.getSku()).matches()) throw new SKUFormatException();
        ofNullable(request.getName()).orElseThrow(NameNullException::new);
        ofNullable(request.getBrand()).orElseThrow(BrandNullException::new);
        ofNullable(request.getSize()).orElseThrow(SizeNullException::new);
        ofNullable(request.getPrice()).orElseThrow(PriceNullException::new);
        ofNullable(request.getImage()).orElseThrow(ImageNullException::new);
        if(!ofNullable(request.getName()).filter(GREATER_THAN_TREE).filter(LESS_THAN_FIFTY).isPresent()) throw new NameFormatException();
        if(!ofNullable(request.getBrand()).filter(GREATER_THAN_TREE).filter(LESS_THAN_FIFTY).isPresent()) throw new BrandFormatException();
        if(!ofNullable(request.getPrice()).filter(GREATER_THAN_ONE).filter(LESS_THAN_99999999).isPresent()) throw new PriceFormatException();
        if(!Pattern.compile(URL_PATTER).matcher(request.getImage()).matches()) throw new ImageFormatException();
    }

}
