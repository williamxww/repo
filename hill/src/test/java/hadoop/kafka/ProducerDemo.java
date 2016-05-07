package hadoop.kafka;

import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


/**
 * TODO 添加类的描述
 *
 * @author acer
 * @version C10 2016年1月17日
 * @since SDP V300R003C10
 */
public class ProducerDemo
{
    public static void main(String[] args)
    {
        

        // 设置配置属性
        Properties props = new Properties();
        props.put("metadata.broker.list", "10.211.93.154:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // key.serializer.class默认为serializer.class
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        // 可选配置，如果不配置，则使用默认的partitioner
        // props.put("partitioner.class", "hadoop.kafka.PartitionerDemo");
        // 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
        // 值为0,1,-1,可以参考 http://kafka.apache.org/08/configuration.html
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        
        // 创建producer
        Producer<String, String> producer = new Producer<String, String>(config);
        // 产生并发送消息
        long start = System.currentTimeMillis();
        int events = 100;
        for (long i = 0; i < events; i++)
        {
            long runtime = new Date().getTime();
            String key = "key-" + i;// rnd.nextInt(255);
            String msg = runtime + ",www.hill.com," + key;
            // 如果topic不存在，则会自动创建，默认replication-factor为1，partitions为0
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("vv-test", key, msg);
            producer.send(data);
        }
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        // 关闭producer
        producer.close();
    }
    
}
