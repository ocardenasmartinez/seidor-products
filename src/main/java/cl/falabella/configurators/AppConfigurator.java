package cl.falabella.configurators;

import cl.falabella.buisness.ProductBusiness;
import cl.falabella.buisness.ProductsBusinessImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigurator {

    @Bean
    public ProductBusiness getProductBusiness() {
        return new ProductsBusinessImpl();
    }

}
