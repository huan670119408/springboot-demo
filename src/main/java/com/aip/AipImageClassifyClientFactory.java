package com.aip;

import com.baidu.aip.imageclassify.AipImageClassify;

/**
 * Created by LiBingyi on 2019/7/24 10:55
 */
public class AipImageClassifyClientFactory {

    private AipImageClassifyClientFactory() {

    }

    private static class AipImageClassifyInstance {
        private static final String APP_ID = "16871837";
        private static final String API_KEY = "qHRW7iMMpu9OkupYnUuDs1aY";
        private static final String SECRET_KEY = "jt8TwK1OioZ0bmvKSmePBWTacmupLMBV";
        private static final AipImageClassify instance = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
    }

    public static AipImageClassify getInstance() {
        return AipImageClassifyInstance.instance;
    }

}
