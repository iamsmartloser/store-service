package com.liyan.store.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImgSaveUtil {

    public String saveImg(String name,String img,String path) {
        //试着存储，若能存储成功，则注册成功
        String pName = new MD5Helper().encrypt16(name);//md5命名图片
        String iconName = pName + ".png";


        //给图片解码
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        try {
            bytes = decoder.decodeBuffer(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        // 生成png图片，保存在数据库存储的路径中
        File f = new File(path + iconName);
        if (!f.exists()) {
            try {
                f.createNewFile();
                OutputStream out = new FileOutputStream(f);
                out.write(bytes);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return iconName;
    }
}
