package com.xuecheng.framework.domain.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * 测试mongoDB数据库连接
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
public class MongoDBTest {

    public static void main(String[] args) {
        // 创建客户端
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // 获取需要查询的数据库
        MongoDatabase database = mongoClient.getDatabase("test");
        // 获取需要查询的collection
        MongoCollection<Document> student = database.getCollection("student");
        // 查询该集合的第一个文档
        Document first = student.find().first();
        // 将json文档转换为字符串
        String s = first.toJson();
        System.out.println(s);
    }
}
