package com.example.demo.mappers;

import com.example.demo.Request.CreateProductRequest;
import com.example.demo.entities.ProductEntity;
import com.example.demo.models.Product;
import com.example.demo.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CommonMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productEntityToProduct(ProductEntity productEntity);
    ProductEntity productToProductEntity(Product product);
    List<Product> listProductEntityToListProduct(List<ProductEntity> productEntities);

    ProductResponse productToProductResponse(Product product);
    List<ProductResponse> productListToProductResponse(List<Product> products);

    @Mapping(target = "category", ignore = true)
    Product productRequestToProduct(CreateProductRequest createProductRequest);



}
