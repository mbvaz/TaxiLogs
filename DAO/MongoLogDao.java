package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import domain.LogsGPS2;
import utils.Cronometro;

public class MongoLogDao {

	private static MongoClient mongo;
	private static MongoDatabase db;
	private static MongoCollection<Document> colecao;
	
	public static void save() throws NumberFormatException, IOException, ParseException {
		mongo = new MongoClient("localhost", 27017);
		mongo.dropDatabase("logTaxi");
		db = mongo.getDatabase("logTaxi");
		colecao = db.getCollection("logTaxi");

		FileReader ler1 = new FileReader("C://Users//m_bva//Desktop//Dados//100.txt");
		BufferedReader reader = new BufferedReader(ler1);
		String linha;

		Cronometro.start();

		while ((linha = reader.readLine()) != null) {

			String[] split = linha.split(",");

			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dataEntrada = new Date(fmt.parse(split[1]).getTime());

			LogsGPS2 log = new LogsGPS2(split[2], split[3], dataEntrada, Integer.parseInt(split[0]));

			colecao.insertOne(new Document().append("taxiID", log.getIdTaxi()).append("DateTime", log.getData())
					.append("longitude", log.getLongitude()).append("latitude", log.getLatitude()));

		}
		Cronometro.stop();
		System.out.println("Tempo da inserção: " + Cronometro.retornaTempo() + "ms");
	}

	public static void list() {
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDatabase("logTaxi");
		Cronometro.start();
		MongoCollection<Document> colecao = db.getCollection("logTaxi");
		colecao.find();
		Cronometro.stop();
		System.out.println("Tempo da consulta: " + Cronometro.retornaTempo() + "ms");
	}
	
	
}
