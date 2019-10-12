package com.liyan.store.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, length = 20, unique = true)
    @NotBlank(message = "用户名不能为空")//没有默认为电话号码
    private String userName;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer identity = 0;   //0：顾客   1:商家   2:管理员

    @Column(length = 50)
    private String icon;    //头像地址，这里应该设置一个默认头像

    @Column(length = 20)
    private String realName;

    private String introduce;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "请填写您的电话")
    private String tel;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String addr;

    private Integer sex = 3;    //0：男  1：女 2：不祥

    private Timestamp birthday;

    private Integer bindingPhone = 0;
    @Column(length = 50,unique = true)
    private String accessToken;

    @Column(length = 50,unique = true)
    private String imToken;

    @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Shop.class)
    @JoinColumn(name="shopId")
    private Shop shop;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Orders.class)
    @JoinColumn(name = "ordersId")
    private List<Orders> ordersList;

    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=ShopingCar.class)
    @JoinColumn(name="carId")
    private ShopingCar shopingCar=new ShopingCar();

    public ShopingCar getShopingCar() {
        return shopingCar;
    }

    public void setShopingCar(ShopingCar shopingCar) {
        this.shopingCar = shopingCar;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Shop getShop() {
        if(this.shop==null)
            return new Shop();
        else return shop;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Integer getBindingPhone() {
        return bindingPhone;
    }

    public void setBindingPhone(Integer bindingPhone) {
        this.bindingPhone = bindingPhone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
