package com.liyan.store.controller;

import com.liyan.store.aspect.ManagerAccess;
import com.liyan.store.aspect.UserAccess;
import com.liyan.store.domain.*;
import com.liyan.store.domain.result.UserInfo;
import com.liyan.store.enums.ResultEnum;
import com.liyan.store.properties.AddrProoerties;
import com.liyan.store.repository.ShopRepository;
import com.liyan.store.repository.UserRepository;
import com.liyan.store.service.UserService;
import com.liyan.store.utils.MD5Helper;
import com.liyan.store.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AddrProoerties addrProoerties;

    public UserController() {
    }

    /**
     * 用户注册接口和更新接口，这里后台接受数据后面会修改，不能再这里对身份进行修改
     *
     * @param user 用户信息
     * @return 用户信息
     * @throws Exception
     */


    @PostMapping(value = "/user/register")
    @ResponseBody
    public Result<User> register(@Valid @RequestBody User user) throws Exception {
        User u = userService.register(user);
        u.setIcon(addrProoerties.getAddr() + addrProoerties.getPort() + u.getIcon());
        return ResultUtil.success();
    }
    @UserAccess
    @PostMapping(value = "/user/info")
    @ResponseBody
    public Result<UserInfo> getUserInfo(@Valid @RequestBody Map<String, Object> map) {
        Long userId;
        userId = Long.valueOf(map.get("userId").toString());
        User user = userRepository.findByUserId(userId);
        UserInfo info = new UserInfo();
        info.setUserId(user.getUserId());
        info.setUserName(user.getUserName());
        info.setRealName(user.getRealName());
        info.setAddr(user.getAddr());
        info.setBirthday(user.getBirthday());
        info.setEmail(user.getEmail());
        info.setIcon(addrProoerties.getAddr() + addrProoerties.getPort() + user.getIcon());
        info.setIdentity(user.getIdentity());
        info.setBindingPhone(user.getBindingPhone());
        if (user.getSex() != null){
            info.setSex(user.getSex());
        }
        info.setTel(user.getTel());

        return ResultUtil.success(info);
    }

    /**
     * 用户登陆
     * <p>
     * userName 用户名
     * param @ password 密码
     *
     * @return accessToken等
     */
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Result<Object> login(@Valid @RequestBody Map<String, Object> map) throws Exception{




        return ResultUtil.success(userService.login(map));
    }

    /**
     * 普通用户申请商家权限
     *
     * @return
     * @throws Exception
     */
    //@UserAccess
    @PostMapping(value = "/identity/apply")
    @ResponseBody
    public Result<Object> identityApply(@RequestBody IdentityApply identityApply)
            throws Exception {
        userService.identityApply(identityApply);

        return ResultUtil.success();
    }

    /**
     * 管理员查看商家申请列表，分页显示，一页10条
     */
    @PostMapping(value = "/identity/apply/list")
    @ResponseBody
    public Result<Object> applyList(@RequestBody Map<String, Object> params, @PageableDefault
            (value = 10, sort = {"id"}, direction = Sort.Direction.ASC)
            Pageable pageable) {
        Integer page = Integer.valueOf(params.get("page").toString());
        Integer size = Integer.valueOf(params.get("size").toString());
        Integer status = Integer.valueOf(params.get("status").toString());
        pageable = new PageRequest(page, size);
        return ResultUtil.success(userService.applyList(pageable,status));
    }


    /**
     * 管理员修改用户的身份，如果将用户设为了商家，就同时添加一个shop
     * 如果设为了普通用户，则同时删除相关shop
     * 先判断接口调用者的身份，用accesstoken判断，管理员权限才能修改用户的身份
     * userId 被修改用户的id
     * identity 申请的身份 0普通用户  1：商家   2：管理员
     *
     * @return
     */
    //@ManagerAccess
    @PostMapping(value = "/identity/manager")
    @ResponseBody
    public Result<Object> identityManager(@RequestBody Map<String, Object> map) throws Exception {
        Integer identityApplyId =Integer.valueOf( map.get("identityApplyId").toString());
        //Integer identityApplyId = Double.valueOf(map.get("identityApplyId").toString()).intValue();
        return ResultUtil.success(userService.identityManager(identityApplyId));
    }


    @Autowired
    private ShopRepository shopRepository;

    @PostMapping(value = "/test")
    @ResponseBody
    @Transactional
    public Result<Object> test() {
//        User user=userRepository.getOne(59);
//        user.setIdentity(0);
//        System.out.println(" ShopId:"+user.getShop().getShopId()+"\n"+"userId:"+user.getShop().getUser().getUserId());
//        Shop shop=user.getShop();
//        user.setShop(null);
//        shopRepository.delete(shop);
        userService.test();
        return ResultUtil.success();
    }

}
