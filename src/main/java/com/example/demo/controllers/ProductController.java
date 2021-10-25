package com.example.demo.controllers;

import com.example.demo.Request.CreateProductRequest;
import com.example.demo.response.ProductResponse;
import com.example.demo.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
@Api(value = "Product", tags = { "Product" })
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all products")
    public ResponseEntity<List<ProductResponse>> getAll() {
       return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }



    @PostMapping
    @ApiOperation(value = "Create product")
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody CreateProductRequest createProductRequest
            ){

        return new ResponseEntity<>(productService.create(createProductRequest), HttpStatus.CREATED);
    }


    @DeleteMapping()
    @ApiOperation(value = "Delete product by id")
    public ResponseEntity<String> deleteProductById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productService.eliminarProductById(id));
    }


    @PutMapping
    @ApiOperation(value="Update Product by product id")
    public ResponseEntity<ProductResponse> updateEstadoByProductId(
            @RequestParam("id") Long id, @RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.updateProduct(id,createProductRequest), HttpStatus.OK);
    }


    @GetMapping(value = "/getProductsByConditions")
    @ApiOperation(value = "Get Products by conditions", tags = {"Product"})

    public ResponseEntity<Collection<ProductResponse>> getProductsByConditions(
            @ApiParam(name = "releaseDate", value = "releaseDate", type = " Timestamp", example = "2021-04-10T12:45:00.000Z")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            @RequestParam("releaseDate") Optional<String> releaseDate,
            @RequestParam("views") Optional<Integer> views) {
        Collection<ProductResponse> products = productService.filterProduct(releaseDate,views);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
