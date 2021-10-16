package cl.falabella.buisness;

import cl.falabella.entities.request.ProductRequest;
import cl.falabella.entities.responses.ListProductResponse;
import cl.falabella.entities.responses.OneProductResponse;
import cl.falabella.entities.responses.ProductResponse;

public interface ProductBusiness {

    ProductResponse insertProduct(ProductRequest request) throws Exception;
    ProductResponse updateProduct(ProductRequest request) throws Exception;
    OneProductResponse getProduct(String sku) throws Exception;
    ListProductResponse getProducts() throws Exception;
    ProductResponse deleteProduct(String sku) throws Exception;
}
