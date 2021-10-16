package cl.falabella.business.mocks;

import cl.falabella.buisness.ProductBusiness;
import cl.falabella.entities.request.ProductRequest;
import cl.falabella.entities.responses.ListProductResponse;
import cl.falabella.entities.responses.OneProductResponse;
import cl.falabella.entities.responses.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductBusinessMock implements ProductBusiness {

    private static final String OK = "OK";

    @Override
    public ProductResponse insertProduct(ProductRequest request) throws Exception {
        return ProductResponse.builder().state(OK).build();
    }

    @Override
    public ProductResponse updateProduct(ProductRequest request) throws Exception {
        return ProductResponse.builder().state(OK).build();
    }

    @Override
    public OneProductResponse getProduct(String sku) throws Exception {
        return OneProductResponse.builder().sku("").brand("").name("").price(0D).size("").image("").build();
    }

    @Override
    public ListProductResponse getProducts() throws Exception {
        List<OneProductResponse> oneProductResponseList = new ArrayList<>();
        oneProductResponseList.add(OneProductResponse.builder().sku("").brand("").name("").price(0D).size("").image("").build());
        return ListProductResponse.builder().product(oneProductResponseList).build();
    }

    @Override
    public ProductResponse deleteProduct(String sku) throws Exception {
        return ProductResponse.builder().state(OK).build();
    }
}
