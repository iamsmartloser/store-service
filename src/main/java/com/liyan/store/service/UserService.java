package com.liyan.store.service;

import com.liyan.store.domain.IdentityApply;
import com.liyan.store.domain.Shop;
import com.liyan.store.domain.ShopCategory;
import com.liyan.store.domain.User;
import com.liyan.store.domain.result.IdentityApplyResult;
import com.liyan.store.enums.ResultEnum;
import com.liyan.store.exception.ReqException;
import com.liyan.store.properties.AddrProoerties;
import com.liyan.store.repository.IdentityApplyRepository;
import com.liyan.store.repository.ShopCategoryRepository;
import com.liyan.store.repository.ShopRepository;
import com.liyan.store.repository.UserRepository;
import com.liyan.store.utils.ImgSaveUtil;
import com.liyan.store.utils.MD5;
import com.liyan.store.utils.MD5Helper;
import com.liyan.store.utils.ResultUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdentityApplyRepository identityApplyRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;

    @Autowired
    private AddrProoerties addrProoerties;

    @Transactional
    public User register(User user) throws Exception {
        String icon = user.getIcon();
        if (icon != null) {
            ImgSaveUtil imgSaveUtil = new ImgSaveUtil();
            String path = "G:/ideaCode/work/icon/";
            String iconName = imgSaveUtil.saveImg(user.getUserName(), user.getIcon(), path);
            user.setIcon("/store/icon/" + iconName);
        }
        user.setAccessToken(null);
        user.getShopingCar().setUser(user);
        User user1=userRepository.saveAndFlush(user);
        //请求融云token,相当于注册一下，以免有可能没有token时发生崩溃
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId",user.getUserId().toString());
        params.add("name", user.getUserName());
        params.add("portraitUri",addrProoerties.getAddr() + addrProoerties.getPort()+user.getIcon());
        new RongHttpClient().client("http://api.cn.ronghub.com/user/getToken.json", HttpMethod.POST, params);
        return user1;
    }

    public Map<String, Object> login(Map<String, Object> map) throws Exception {
        String accessToken, userName, password;
        userName = map.get("userName").toString();
        password = map.get("password").toString();
        User user = userRepository.findByUserName(userName);
        if (user != null) {

            if (MD5.verify(user.getPassword(),"9dt4wtV3J4JFXQgq",password)) {
                //请求融云token
                MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
                params.add("userId",user.getUserId().toString());
                params.add("name", user.getUserName());
                params.add("portraitUri",addrProoerties.getAddr() + addrProoerties.getPort()+user.getIcon());
                String jsonBody = new RongHttpClient().client("http://api.cn.ronghub.com/user/getToken.json", HttpMethod.POST, params);
                JSONObject jsonObject = new JSONObject(jsonBody);
                String imToken = jsonObject.getString("token");
                accessToken = new MD5Helper().encrypt32(user.getUserId() + password + new Date().getTime());//获取token
                user.setAccessToken(accessToken);
                userRepository.saveAndFlush(user);
                Map<String, Object> m = new HashMap<>();
                m.put("accessToken", accessToken);
                m.put("userId", user.getUserId());
                m.put("imToken", imToken);
                return m;
            } else
                throw new ReqException(ResultEnum.PASSWORD_ERROR);
        }

        throw  new ReqException(ResultEnum.USER_NAME_NON_EXISTENT);
    }

    @Transactional
    public void identityApply(IdentityApply identityApply) throws Exception {
        ShopCategory shopCategory = shopCategoryRepository.getOne(identityApply.getShopCategoryId());
        IdentityApply tempApply = identityApplyRepository.findByUserId(identityApply.getUserId());
        if (null != tempApply) {
            identityApply.setId(tempApply.getId());
        }
        identityApply.setStatus(0);//新申请将会设置为未处理状态
        identityApply.setShopCategoryName(shopCategory.getName());
        if (null != identityApply.getIcon()) {
            ImgSaveUtil imgSaveUtil = new ImgSaveUtil();
            String path = "G:/ideaCode/work/icon/";
            System.out.println("getIcon:" + identityApply.getIcon());
            String iconName = imgSaveUtil.saveImg(identityApply.getUserName()+new Date().getTime(), identityApply.getIcon(), path);
            identityApply.setIcon("/store/icon/" + iconName);
        }

        if (null != identityApply.getBusinessLicense()) {
            ImgSaveUtil imgSaveUtil = new ImgSaveUtil();
            String path = "G:/ideaCode/work/other/";
            System.out.println("getUserName:" + identityApply.getUserName());
            String iconName = imgSaveUtil.saveImg(identityApply.getUserName(), identityApply.getBusinessLicense(), path);
            identityApply.setBusinessLicense("/store/other/" + iconName);
        }

        identityApplyRepository.saveAndFlush(identityApply);

    }

    @Transactional
    public Map<String, Object> applyList(Pageable pageable, Integer status) {
        Page<IdentityApply> identityApplies;
        if (status == 2) {//全部
            identityApplies = identityApplyRepository.findAll(pageable);
        } else {
            identityApplies = identityApplyRepository.findByStatus(pageable, status);
        }

        //Page<IdentityApply> identityApplies = identityApplyRepository.findAll(pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("total_pages", identityApplies.getTotalPages());
        map.put("total_elements", identityApplies.getTotalElements());

        // List<IdentityApply> identityApplyList=new ArrayList<>();
        List<IdentityApplyResult> results=new ArrayList<>();
        for (IdentityApply identityApply : identityApplies.getContent()) {
            IdentityApplyResult result=new IdentityApplyResult();
            result.setId(identityApply.getId());
            result.setStatus(identityApply.getStatus());
            result.setShopName(identityApply.getShopName());
            result.setUserId(identityApply.getUserId());
            result.setUserName(identityApply.getUserName());
            result.setCompanyName(identityApply.getCompanyName());
            if (identityApply.getBusinessLicense() != null && !"".equals(identityApply.getBusinessLicense())) {
                String s=identityApply.getBusinessLicense();
                System.out.println(s);
                //identityApply.setBusinessLicense(addrProoerties.getAddr() + addrProoerties.getPort() + s);
                result.setBusinessLicense(addrProoerties.getAddr() + addrProoerties.getPort() +s);
            }
        results.add(result);
        }
        map.put("apply_list", results);
        return map;
    }


    @Transactional
    public Map<String, Object> identityManager(Integer identityApplyId)
            throws Exception {
        IdentityApply identityApply = identityApplyRepository.getOne(identityApplyId);
        User user = userRepository.findByUserId(identityApply.getUserId());
        user.setIdentity(identityApply.getIdentity());
        if (identityApply.getIdentity() == 1) {
            ShopCategory shopCategory = shopCategoryRepository.getOne(identityApply.getShopCategoryId());
            Shop shop = user.getShop();
            shop.setShopCategory(shopCategory);
            shop.setShopName(identityApply.getShopName());
            shop.setUser(user);
            shop.setAddress("test addr");
            user.setShop(shop);
            userRepository.saveAndFlush(user);
            identityApply.setStatus(1);
            identityApplyRepository.saveAndFlush(identityApply);
        }
        if (identityApply.getIdentity() == 0) {
            user.setIdentity(0);
            System.out.println(" ShopId:" + user.getShop().getShopId() + "\n" + "userId:" + user.getShop().getUser().getUserId());
            Shop shop1 = user.getShop();
            user.setShop(null);
            shopRepository.delete(shop1);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", identityApply.getUserId());
        map.put("identity", identityApply.getIdentity());
        return map;
    }

    @Transactional
    public void test() {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("userId", "436");
        params.add("name", "1234");
        params.add("portraitUri", "http://192.168.1.105:8080/store/icon/52d04dc20036dbd8.png");

        new RongHttpClient().client("http://api.cn.ronghub.com/user/getToken.json", HttpMethod.POST, params);
        System.out.println("test()");
    }
}
