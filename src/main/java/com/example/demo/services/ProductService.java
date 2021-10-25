package com.example.demo.services;

import com.example.demo.Request.CreateProductRequest;
import com.example.demo.entities.CategoryEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.exception.ApiException;
import com.example.demo.mappers.CategoryMapper;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.response.ProductResponse;
import com.example.demo.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager em;

    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository , EntityManager em) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.em=em;
    }

    public List<ProductResponse>  getAll(){
        List<ProductResponse> products = ProductMapper.INSTANCE.productListToProductResponse(ProductMapper.INSTANCE.listProductEntityToListProduct(productRepository.findAll()));
        return products;
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ProductResponse create(CreateProductRequest createProductRequest) {

        this.validateHaslength(createProductRequest);

        String abbreviation= this.validateAbbreviation(createProductRequest);

        Category category = CategoryMapper.INSTANCE.categoryRequestToCategory(createProductRequest.getCategory());
        CategoryEntity categoryEntity =categoryRepository.save(CategoryMapper.INSTANCE.categoryToCategoryEntity(category));

        ProductEntity productEntity = ProductMapper.INSTANCE.productToProductEntity(ProductMapper.INSTANCE.productRequestToProduct(createProductRequest));
        productEntity.setCategory(categoryEntity);
        productEntity.setAbbreviation(abbreviation);

        Product product = ProductMapper.INSTANCE.productEntityToProduct(
                productRepository.save(productEntity));

        return ProductMapper.INSTANCE.productToProductResponse(product);
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String eliminarProductById(Long id) {
        ProductEntity productEntity = productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find any product with Id: " + id));
       productRepository.delete(productEntity);
       return "The product with id: " + id + " successfully delete";

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ProductResponse updateProduct(Long id, CreateProductRequest createProductRequest){
        ProductEntity productEntity = productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find any product with Id: " + id));

        this.validateHaslength(createProductRequest);

        String abbreviation= this.validateAbbreviation(createProductRequest);

      Category category = CategoryMapper.INSTANCE.categoryRequestToCategory(createProductRequest.getCategory());
      category.setId(productEntity.getCategory().getId());
      CategoryEntity categoryEntity =categoryRepository.saveAndFlush(CategoryMapper.INSTANCE.categoryToCategoryEntity(category));

        Product product = ProductMapper.INSTANCE.productRequestToProduct(createProductRequest);
        product.setId(productEntity.getId());
        product.setAbbreviation(abbreviation);
        product.setCategory(category);

         product = ProductMapper.INSTANCE.productEntityToProduct(
                productRepository.saveAndFlush(ProductMapper.INSTANCE.productToProductEntity(product)));

        return ProductMapper.INSTANCE.productToProductResponse(product);


    }


    public Collection<ProductResponse> filterProduct(Optional<String> releaseDate, Optional<Integer> views) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> productEntityRoot = query.from(ProductEntity.class);

        Predicate predicate = null;

        if (releaseDate.isPresent()) {
            Predicate releaseDatePredicate = cb.lessThanOrEqualTo(productEntityRoot.get("release_date"), TimeUtil.getTimeByString(releaseDate.get()));
            if (predicate == null) {
                predicate = releaseDatePredicate;
            } else {
                predicate = cb.and(predicate, releaseDatePredicate);
            }

        }

        if (views.isPresent()) {
            Predicate viewsPredicate = cb.equal(productEntityRoot.get("views"), views.get());
            if (predicate == null) {
                predicate = viewsPredicate;
            } else {
                predicate = cb.and(predicate, viewsPredicate);
            }

        }

        if (predicate != null) {
            query.where(predicate).distinct(true);
        }


        query.orderBy(cb.desc(productEntityRoot.get("release_date")),cb.asc(productEntityRoot.get("views")));

        Collection<ProductResponse> responses = ProductMapper.INSTANCE.productListToProductResponse(ProductMapper.INSTANCE.listProductEntityToListProduct(em.createQuery(query).getResultList()));

        return responses;

    }

    private void validateHaslength(CreateProductRequest createProductRequest){
        if (createProductRequest.getCategory().isHaslength() && createProductRequest.getCategory().getLength()==null){
            throw new ApiException("Validation exception", "User need to insert the length of the product.", 422);
        }
    }

    private String validateAbbreviation(CreateProductRequest createProductRequest){
        if (createProductRequest.getAbbreviation() !=null && createProductRequest.getAbbreviation().length() < 3){
            throw new ApiException("Validation exception", "The Abbreviation will has at least 3 characters.", 422);
        }else if(createProductRequest.getAbbreviation() ==null){
            return this.generateAbbreviation(createProductRequest.getName());
        }else return createProductRequest.getAbbreviation();
    }

    private String generateAbbreviation(String name){

        String result = name.replaceAll("\\B.|\\P{L}", "").toUpperCase();

        if(result.length()==1){
            result =name.substring(0,3).toUpperCase();

        } else if (result.length()==2){
            String[] words = name.split("\\s");
            result=words[0].substring(0,2).concat(words[1].substring(0,1)).toUpperCase();

        }
        return result;
    }


}
