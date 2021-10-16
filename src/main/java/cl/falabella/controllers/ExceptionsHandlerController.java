package cl.falabella.controllers;

import cl.falabella.controllers.exceptions.*;
import cl.falabella.entities.responses.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionsHandlerController {

    @ExceptionHandler(SKUNullException.class)
    public ResponseEntity<ProductResponse> SKUNullException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("SKU must be include").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameNullException.class)
    public ResponseEntity<ProductResponse> NameNullException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Name must be include").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandNullException.class)
    public ResponseEntity<ProductResponse> BrandNullException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Brand must be include").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SizeNullException.class)
    public ResponseEntity<ProductResponse> SizeNullException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Size must be include").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceNullException.class)
    public ResponseEntity<ProductResponse> PriceNullException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Price must be include").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNullException.class)
    public ResponseEntity<ProductResponse> ImageNullException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Image must be include").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SKUFormatException.class)
    public ResponseEntity<ProductResponse> SKUFormatException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Invalid SKU format").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameFormatException.class)
    public ResponseEntity<ProductResponse> NameFormatException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Invalid name format").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandFormatException.class)
    public ResponseEntity<ProductResponse> BrandFormatException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Invalid brand format").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceFormatException.class)
    public ResponseEntity<ProductResponse> PriceFormatException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Invalid price format").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageFormatException.class)
    public ResponseEntity<ProductResponse> ImageFormatException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("Invalid image format").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<ProductResponse> productExistException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("The product already exists").build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ProductResponse> productNotExistException(Exception e) {
        return new ResponseEntity<>(ProductResponse.builder().state("The product does not exists").build(), HttpStatus.NOT_ACCEPTABLE);
    }



}
