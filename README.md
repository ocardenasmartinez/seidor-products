# Products Service

## Technology
- Java 11
- Maven 3
- Spring Boot
- H2

## Build and execution
In the console

```Build
1.- git clone https://github.com/ocardenasmartinez/seidor-products.git
2.- cd seidor-products
3.- mvn package
4.- java -jar target/seidor-products-1.0.jar
```
```bash
Insert product
curl -X POST -H "Content-Type: application/json" \
    -d '{
    "sku": "FAL-1000001",
    "name": "Bicicleta Baltoro Aro 26",
    "brand": "JEEP",
    "size": "ST",
    "price": 39999.99,
    "image": "http://falabella.scene7.com/is/image/Falabella/881952283_1"
}' \
 http://localhost:8080/products/insert

Update product
curl -X PUT -H "Content-Type: application/json" \
    -d '{
    "sku": "FAL-1000001",
    "name": "Bicicleta Baltoro Aro 28",
    "brand": "JEEP",
    "size": "ST",
    "price": 39999.99,
    "image": "http://falabella.scene7.com/is/image/Falabella/881952283_1"
}' \
    http://localhost:8080/products/update
    
Obtener producto por SKU
curl http://localhost:8080/products/get?sku=FAL-1000001

Obtener todos los productos
curl http://localhost:8080/products/getall

Borrar product
curl -X "DELETE" http://localhost:8080/products/delete?sku=FAL-1000001
```
## Brief explanation about architectura

Three-layer architecture, controller layer for endpoints, business layer for handle business
logic and data layer for handle data