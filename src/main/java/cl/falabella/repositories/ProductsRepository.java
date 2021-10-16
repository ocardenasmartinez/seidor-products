package cl.falabella.repositories;

import cl.falabella.entities.data.ProductsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity, Integer> {

    ProductsEntity findBySku(String sku);

}
