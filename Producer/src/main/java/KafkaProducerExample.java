import com.google.gson.Gson;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class KafkaProducerExample {

    private static String TOPIC = "Topic1";
    private final static String KAFKA_SERVER = "localhost:29092";

    public static void main(String[] args) throws IOException {
        try {
            runProducer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<Record> readFile() throws IOException {
        Pattern pattern = Pattern.compile(",");
        BufferedReader br = new BufferedReader(new FileReader("sample_data.csv"));
        List<Record> records = br.lines().skip(1).map(line -> {
            String[] x = pattern.split(line);

            return new Record(x[0], x[1], x[2],x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11], x[12], x[13], x[14],
                    x[15], x[16], x[17], x[18], x[19], x[20], x[21], x[22], x[23], x[24], x[25], x[26], x[27], x[28]);
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
        List<Record> data = readFile();
        Producer<Long, String> producer = createProducer();
        Gson gson = new Gson();

        for (Record entry: data){
            String json = gson.toJson(entry);
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, json);
            producer.send(record);
            Thread.sleep((long)(Math.random() * 1000));
        }
    }

}

