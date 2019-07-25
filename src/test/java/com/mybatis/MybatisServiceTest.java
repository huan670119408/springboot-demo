package com.mybatis;

import com.DemoApplication;
import com.aip.AipImageClassifyClientFactory;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.common.StringUtil;
import com.mybatis.bean.Aip;
import com.mybatis.dao.AipMapper;
import com.mybatis.dao.PoemMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MybatisServiceTest {

    @Autowired
    private PoemMapper poemMapper;

    @Autowired
    private AipMapper aipMapper;

    @Test
    public void test() throws Exception {
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
//            System.out.println(res1.toString(2));
            Aip cell = new Aip();
            cell.setId(StringUtil.uuid());
            cell.setAbsolutePath(image.getAbsolutePath());
            cell.setFileName(image.getName());
            cell.setResult(res1.toString(2));
            aipMapper.insertAip(cell);
        }

    }

}