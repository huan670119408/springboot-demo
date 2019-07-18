import com.DemoApplication;
import com.rabbitmq.FanoutRabbitMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiBingyi on 2019/7/18 14:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RabbitMqProducerTest {

    @Autowired
    private FanoutRabbitMQProducer producer;

    @Test
    public void hello() {
         for(int i=0;i<10;i++){
             producer.send();
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }

}
