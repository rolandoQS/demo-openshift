package com.example.demo;

import com.example.demo.exception.ApiException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        DemoApplication.class,
        TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class UserUnitTests {

//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    ProductRepository productRepository;
//
//
//
//
//    @Test
//    public void when_createProducts__then_create_products() {
//
//        CreateCategoryRequest createCategoryRequest= createCategoryRequest("Movie",true,7200L);
//
//        CreateProductRequest createProductRequest= createProduct("Spaiderman","Accion","2021-04-10T12:45:00.000Z","2021-04-10T12:45:00.000Z",3,"SP",createCategoryRequest);
//
//
//        Assertions.assertThrows(ApiException.class, () -> this.productService.create(createProductRequest));
//
//
//        CreateCategoryRequest createCategoryRequest1= createCategoryRequest("Movie",true,null);
//
//        CreateProductRequest  createProductRequest1= createProduct("Spaiderman","Accion","2021-04-10T12:45:00.000Z","2021-04-10T12:45:00.000Z",3,"SPI",createCategoryRequest1);
//
//
//        Assertions.assertThrows(ApiException.class, () -> this.productService.create(createProductRequest1));
//
//
//        CreateCategoryRequest createCategoryRequest2= createCategoryRequest("Movie",true,7200L);
//
//        CreateProductRequest  createProductRequest2= createProduct("Spaiderman","Accion","2021-04-10T12:45:00.000Z","2021-04-10T12:45:00.000Z",3,"SPI",createCategoryRequest2);
//
//        this.productService.create(createProductRequest2);
//
//
//        CreateCategoryRequest createCategoryRequest3= createCategoryRequest("Movie",true,7200L);
//
//        CreateProductRequest  createProductRequest3= createProduct("Hip Man","Accion","2021-04-10T12:45:00.000Z","2022-04-10T12:45:00.000Z",5,null,createCategoryRequest3);
//
//        this.productService.create(createProductRequest3);
//
//
//        final int ALL = this
//                .productService
//                .getAll()
//                .size();
//
//        assertThat(ALL)
//                .isEqualTo(2);
//
//    }
//
//
//    @Test
//    public void when_updateProduct() {
//        Long id = productRepository.findAll().get(0).getId();
//
//        CreateCategoryRequest createCategoryRequest= createCategoryRequest("Movie",true,7200L);
//
//        CreateProductRequest  createProductRequest= createProduct("Spaiderman Return","Accion","2022-04-10T12:45:00.000Z","2021-04-10T12:45:00.000Z",4,null,createCategoryRequest);
//
//        productService.updateProduct(id,createProductRequest);
//
//        ProductEntity productEntity=productRepository.findById(id).get();
//
//        assertThat(productEntity.getName())
//                .isEqualTo("Spaiderman Return");
//
//        assertThat(productEntity.getAbbreviation())
//                .isEqualTo("SPR");
//
//
//    }
//
//    @Test
//    public void when_getProductByConditions() {
//        Optional<String> releaseDate = Optional.of("2022-04-10T12:45:00.000Z");
//        Optional<Integer> views = Optional.of(4);
//
//        final int ALL = productService.filterProduct(releaseDate,views).size();
//
//        assertThat(ALL)
//                .isEqualTo(1);
//
//
//         releaseDate = Optional.of("2022-04-10T12:45:00.000Z");
//         views = Optional.empty();
//
//        final int ALL1 = productService.filterProduct(releaseDate,views).size();
//
//        assertThat(ALL1)
//                .isEqualTo(2);
//
//        releaseDate = Optional.empty();
//        views = Optional.of(5);
//
//        final int ALL2 = productService.filterProduct(releaseDate,views).size();
//
//        assertThat(ALL2)
//                .isEqualTo(1);
//
//        releaseDate = Optional.empty();
//        views = Optional.empty();
//
//        final int ALL3 = productService.filterProduct(releaseDate,views).size();
//
//        assertThat(ALL3)
//                .isEqualTo(2);
//
//
//    }
//
//
//    @Test
//    public void when_deleteProduct() {
//
//       Collection<ProductEntity> productEntities = productRepository.findAll();
//       final int sizeBefore= productEntities.size();
//
//       productService.eliminarProductById(((List<ProductEntity>) productEntities).get(0).getId());
//
//        assertThat(sizeBefore-1)
//                .isEqualTo(productRepository.findAll().size());
//
//    }
//
//
//
//        private CreateProductRequest createProduct(String name, String type,String release_date,String insert_date, Integer views, String abbreviation, CreateCategoryRequest createCategoryRequest) {
//        CreateProductRequest toReturn = new CreateProductRequest();
//        toReturn.setViews(views);
//        toReturn.setAbbreviation(abbreviation);
//        toReturn.setType(type);
//        toReturn.setCategory(createCategoryRequest);
//        toReturn.setInsert_date(insert_date);
//        toReturn.setName(name);
//        toReturn.setRelease_date(release_date);
//        return toReturn;
//    }
//
//
//    private CreateCategoryRequest createCategoryRequest(String type,Boolean haslength,Long length) {
//        CreateCategoryRequest toReturn = new CreateCategoryRequest();
//        toReturn.setType(type);
//        toReturn.setHaslength(haslength);
//        toReturn.setLength(length);
//
//        return toReturn;
//    }


}
