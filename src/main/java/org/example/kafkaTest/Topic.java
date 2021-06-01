package org.example.kafkaTest;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Topic {
    public static void main(String[] args) {
        // 创建topic
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        AdminClient adminClient = AdminClient.create(properties);
        ArrayList<NewTopic> topics = new ArrayList<>();
        NewTopic newTopic = new NewTopic("topic-test", 1, (short) 1);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
