package com.bj.test;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBTest {
    public static void main(String[] args) {
        //连接MongoDB服务器,默认端口为27017

        MongoClient client = new MongoClient("localhost");
        //得到要操作的数据库
        MongoDatabase database = client.getDatabase("databaseName");
        //得到要操作的集合
        MongoCollection<Document> collectonName = database.getCollection("collectonName");
        //得到集合中的所有文档
        FindIterable<Document> documents = collectonName.find();
        //遍历数据
        for (Document doc : documents){
            System.out.println(doc);
        }
    }
}
