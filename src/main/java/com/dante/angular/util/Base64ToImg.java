package com.dante.angular.util;

import org.springframework.beans.factory.annotation.Value;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created by xsy83 on 2017/1/10.
 */
public class Base64ToImg {

    @Value("${image.path}")
    private static String path;

    public static String createImage(String base64) {
        if (base64 == null || base64.trim() == "") // 图像数据为空
            return null;

        int first = base64.indexOf("/");
        int last = base64.indexOf(";");
        String type = base64.substring(first+1,last);
        // 图片的存放地址，感觉有点麻烦
        if (path == null) {
            try {
                path = new File("").getCanonicalPath().toString() + "/src/main/resources/static/image";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String imgName = UUID.randomUUID() + "." + type;
        String imgPath = path + "/" + imgName;
        BASE64Decoder decoder = new BASE64Decoder();
        String image = base64.substring(base64.indexOf(",")+1);
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(image);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成图片
            OutputStream out = new FileOutputStream(imgPath);
            out.write(bytes);
            out.flush();
            out.close();
            return imgName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片的真是地址，因为这里数据库存放的并不是图片的地址，而是图片的名称
     * @param src
     * @return
     */
    public static String getRealPath(String src) {
        /*if (path == null) {
            try {
                path = new File("").getCanonicalPath().toString() + "/src/main/resources/image";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String imgPath = path + "/" + src;*/
        String imgPath = "/image/"+src;
        return imgPath;
    }
}
