package com.aip;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * Created by LiBingyi on 2019/7/24 10:13
 */
public class Sample {

    public static final String APP_ID = "16871837";
    public static final String API_KEY = "qHRW7iMMpu9OkupYnUuDs1aY";
    public static final String SECRET_KEY = "jt8TwK1OioZ0bmvKSmePBWTacmupLMBV";

    public static void main(String[] args) {
        // 初始化一个AipImageClassifyClient
//        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        AipImageClassify client = AipImageClassifyClientFactory.getInstance();
        File file = new File("C:\\Users\\67011\\Desktop\\image");
        File[] files = file.listFiles();
        for(File image : files){
            // 图像主题识别
            //图像主体检测 返回数据参数详情
            //参数	类型	是否必须	说明	示例
            //log_id	number	是	唯一的log id，用于问题定位	507499361
            //result	object	是	裁剪结果	-
            //+left	number	是	表示定位位置的长方形左上顶点的水平坐标	50
            //+top	number	是	表示定位位置的长方形左上顶点的垂直坐标	60
            //+width	number	是	表示定位位置的长方形的宽度	200
            //+height	number	是	表示定位位置的长方形的高度	200
            JSONObject res = client.objectDetect(image.getAbsolutePath(), new HashMap());
            System.out.println(res.toString(2));

            // 通用物体识别
            HashMap<String, String> options = new HashMap();
            options.put("baike_num", "5");
            JSONObject res1 = client.advancedGeneral(image.getAbsolutePath(), options);
            System.out.println(res1.toString(2));
        }
    }

}
