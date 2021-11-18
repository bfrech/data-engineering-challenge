import org.apache.kafka.clients.consumer.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Timestamp;
import java.time.Duration;
import java.util.*;

public class KafkaConsumerExample {

    private static String TOPIC = "Topic1";
    private final static String KAFKA_SERVER = "localhost:29092";
    private static List<String> data = new ArrayList<>();

    // Store data with timestamp
    private static HashMap<Timestamp, String> entries = new HashMap<Timestamp, String>();

    public static void main (String[] args) {
        try {
            runConsumer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static Consumer createConsumer() throws UnknownHostException {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName());
        config.put("group.id", "foo");
        config.put("bootstrap.servers", KAFKA_SERVER);
        config.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(config);
    }

    public static void runConsumer() throws UnknownHostException {
        Consumer consumer = createConsumer();
        consumer.subscribe(Collections.singletonList(TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> processResults(record));
            consumer.commitSync();
        }
    }

    public static void processResults(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
        data.add(record.value());
    }

}
