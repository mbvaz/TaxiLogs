package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DAO.LogDAO;
import DAO.MongoLogDao;
import connection.Conexao;
import domain.LogsGPS;
import utils.Cronometro;

public class Tester {

	public static void main(String[] args) throws ParseException, NumberFormatException, IOException {
		Conexao c = new Conexao();

		LogDAO lDao = new LogDAO(c.abrirConexao());

		try {
			FileReader ler1 = new FileReader("C://Users//m_bva//Desktop//Dados//100.txt");
			BufferedReader reader = new BufferedReader(ler1);  
			String linha;

			Cronometro.start();

			while( (linha = reader.readLine()) != null ){  

				String[] split = linha.split(",");

				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.sql.Date dataEntrada = new java.sql.Date(fmt.parse(split[1]).getTime());

				LogsGPS log = new LogsGPS(split[2],split[3],dataEntrada,Integer.parseInt(split[0]));
				lDao.save(log);
			}
			Cronometro.stop();
			System.out.println("Tempo da inserção: "+ Cronometro.retornaTempo()+"ms");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cronometro.start();
		lDao.list();
		Cronometro.stop();
		System.out.println("Tempo de retorno da consulta: "+ Cronometro.retornaTempo()+"ms");
		System.out.println("----------------------------------");
		MongoLogDao mongo = new MongoLogDao();
		
		MongoLogDao.save();
		MongoLogDao.list();
	}

}
