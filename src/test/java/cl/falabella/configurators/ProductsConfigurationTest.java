package cl.falabella.configurators;

import cl.falabella.buisness.ProductBusiness;
import cl.falabella.business.mocks.ProductBusinessMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductsConfigurationTest {

    @Bean
    public ProductBusiness getProductBusiness() {
        return new ProductBusinessMock();
    }

}
