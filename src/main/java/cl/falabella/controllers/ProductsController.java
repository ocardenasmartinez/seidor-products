package cl.falabella.controllers;

import cl.falabella.buisness.ProductBusiness;
import cl.falabella.entities.request.ProductRequest;
import cl.falabella.entities.responses.ListProductResponse;
import cl.falabella.entities.responses.OneProductResponse;
import cl.falabella.entities.responses.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController extends ExceptionsHandlerController{

    @Autowired
    private ProductBusiness productBusiness;

    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> insertProduct(@RequestBody ProductRequest request) throws Exception {
        return new ResponseEntity<>(productBusiness.insertProduct(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest request) throws Exception {
        return new ResponseEntity<>(productBusiness.updateProduct(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OneProductResponse> getProduct(@RequestParam String sku) throws Exception {
        return new ResponseEntity<>(productBusiness.getProduct(sku), HttpStatus.OK);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListProductResponse> getProducts() throws Exception {
        return new ResponseEntity<>(productBusiness.getProducts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> deleteProduct(@RequestParam String sku) throws Exception {
        return new ResponseEntity<>(productBusiness.deleteProduct(sku), HttpStatus.OK);
    }

}
