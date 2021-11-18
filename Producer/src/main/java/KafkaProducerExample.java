import com.google.gson.Gson;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class KafkaProducerExample {

    private static String TOPIC = "Topic1";
    private final static String KAFKA_SERVER = "localhost:29092";
    private static Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        try {
            runProducer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<HashMap<String, String>> readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sample_data.csv"));
        Pattern pattern = Pattern.compile(",");
        String[] labels = pattern.split(br.readLine());
        List<HashMap<String, String>> records = br.lines().skip(1).map( line -> {
            HashMap<String, String> record = new HashMap<>();
            String[] x = pattern.split(line);
            int i = 0;
            for(String l: labels){
                record.put(l, x[i++]);
            }
            return record;
        }).collect(Collectors.toList());
        return records;
    }

    private static Producer<Long,String> createProducer() throws UnknownHostException {
        Properties props = new Properties();
        props.put("client.id", InetAddress.getLocalHost().getHostName());
        props.put("bootstrap.servers", KAFKA_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put("acks", "all");
        return new KafkaProducer<>(props);
    }

    private static void runProducer() throws IOException, InterruptedException {
        List<HashMap<String, String>> data = readFile();
        for (HashMap<String, String> entry: data){
           sendDataAsJson(entry);
        }
    }

    private static void sendDataAsJson(HashMap<String, String> entry) throws UnknownHostException, InterruptedException {
        Producer<Long, String> producer = createProducer();
        String json = gson.toJson(entry);
        final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, json);
        producer.send(record);
        Thread.sleep((long)(Math.random() * 1000));
    }
}

