package com.liyan.store;

import com.liyan.store.domain.*;
import com.liyan.store.repository.*;
import com.liyan.store.utils.MD5Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {
    @Autowired
    ShopingCarRepositry shopingCarRepositry;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductImgRepository productImgRepository;


    @Autowired
    private ProductRepository productRepository;
    @Test
    @Transactional
    public void test() {
        System.out.println(new MD5Helper().encrypt32("1" + new Date().getTime()));
        //添加用户和购物车
//        for(int i=0;i<20;i++){
//            User user=new User();
//            user.setUserName("liyan"+i);
//            user.setPassWord("1234");
//            user.setTel("18148429651");
//            user.getShopingCar().setUser(user);
//
//            userRepository.saveAndFlush(user);
//        }


//        //添加商品,例：添加类型为1、2、3的商品,到店铺id为1的商店里面
//        //商品图片为t1\t2\t3\t4\t5
//        Product product=new Product();
//        List<ProductCategory> productCategoryList=product.getProductCategoryList();
//        productCategoryList.add(productCategoryRepository.getOne(1));
//        productCategoryList.add(productCategoryRepository.getOne(2));
//        productCategoryList.add(productCategoryRepository.getOne(3));
//        product.setProductCategoryList(productCategoryList);
//        product.setShop(shopRepository.getOne(1));
//
//        //其它的设置完了，保存图片在数据库，一张一张存，存一张，向ProductImgs里添加一张，最后保存product对象
//        List<ProductImg> imgs=new ArrayList<>();
//        for(int i=0;i<4;i++){
//            ProductImg img=new ProductImg();
//            img.setImgAddr("testAddr");
//            imgs.add(img);
//            img.setProduct(product);
//        }
//        product.setProductImgs(imgs);
//        Product product1=productRepository.saveAndFlush(product);
//        System.out.println("临时product 里面img的数量："+product.getProductImgs().size()+"\n"
//                +"数据库product里面img的数量："+productRepository.getOne(product1.getProductId()).getProductImgs().size());

        List<Product> products=productRepository.findAll();
        System.out.println("productID:");
        for (Product product:products){
            System.out.println(":"+product.getProductId());
        }

    }

    @Test
    @Transactional
    public void test2(){

    }


}
