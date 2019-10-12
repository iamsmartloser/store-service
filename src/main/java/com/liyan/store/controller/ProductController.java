package com.liyan.store.controller;

import com.liyan.store.domain.Product;
import com.liyan.store.domain.Result;
import com.liyan.store.domain.Shop;
import com.liyan.store.domain.requ.UploadProduct;
import com.liyan.store.domain.result.ProductResult;
import com.liyan.store.repository.ProductRepository;
import com.liyan.store.service.ProductService;
import com.liyan.store.service.UserService;
import com.liyan.store.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    //@UserAccess
    @PostMapping(value = "/product/upload")
    @ResponseBody
    private Result<Object> uploadProduct(@RequestBody UploadProduct uploadProduct) throws Exception{

        if (null==uploadProduct){
            System.out.println(uploadProduct.toString());
        }
        productService.uploadProduct(uploadProduct);

        return ResultUtil.success();
    }

    /**
     * 商品的关键字与分类查询,后期可以添加个价格和权重排序
     *
     * @param params
     * @param pageable
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/product/query")
    @ResponseBody
    public Result<Object> queryProduct(@RequestBody Map<String, Object> params, @PageableDefault
            (value = 10, sort = {"productId"}, direction = Sort.Direction.ASC)
            Pageable pageable) throws Exception {
        //System.out.println("" + params.get("productName").toString());
        Integer page = Integer.valueOf(params.get("page").toString());
        Integer size = Integer.valueOf(params.get("size").toString());
        Integer categoryId = null;
        if (null != params.get("categoryId")) {
            categoryId = Integer.valueOf(params.get("categoryId").toString());
        }

        List<String> productName = null;
        if (params.get("productName") instanceof ArrayList<?>) {
            productName = (List<String>) params.get("productName");
        }
        pageable = new PageRequest(page, size);
        return ResultUtil.success(productService.findProduct(productName, categoryId, pageable));
    }

    @PostMapping(value = "/product/detail")
    @ResponseBody
    public Result<Object> productDetail(@RequestBody ProductResult productResult) throws Exception{
        //productService.productDetail(productId)
        System.out.println("PRODUCTID:"+productResult.getProductId());
        return ResultUtil.success(productService.productDetail(productResult.getProductId()));
        //return ResultUtil.success();
    }

    @GetMapping(value = "/product/test")
    @ResponseBody
    public Result<Object> test(){
        Integer page =0;
        Integer size = 20;

        Pageable pageable = new PageRequest(page, size);
        Specification querySpecifi = new Specification<Product>() {
            List<Predicate> predicates = new ArrayList<>();//or
            List<Predicate> predicates2 = new ArrayList<>();//and
            Predicate temp=null;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {



                System.out.println("predicates2.size::::" + predicates2.size());


                //return criteriaBuilder.and(predicates2.toArray(new Predicate[predicates2.size()]));
                //return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
                //return criteriaQuery.where(predicates2.toArray(new Predicate[predicates2.size()])).getRestriction();
                //criteriaBuilder.min(root.get("productCategory").get("productCategoryId"));
                Order order=criteriaBuilder.asc(root.get("shop").get("shopId"));
                //criteriaBuilder.count(root.get("productCategory").get("productCategoryId"));
//                criteriaBuilder.lessThan(criteriaBuilder.count(root.get("productCategory").get("productCategoryId")),
//                        Long.valueOf(3));
                return criteriaQuery.orderBy(order)
                        .groupBy(root.get("shop").get("shopId"))
                        .having(
                            criteriaBuilder.greaterThan(criteriaBuilder.count(root.get("shop").get("shopId")),
                                Long.valueOf(0)))
                        .getRestriction();
            }

        };

        return ResultUtil.success(productRepository.findAll(querySpecifi, pageable));
    }
}
