package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		//database connection information
		String usr = "postgres";
		String pwd = "123456";
		String url = "jdbc:postgresql://localhost:5432/mydb";

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		List<Sale> salelist = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			while (rs.next()) {
				salelist.add(new Sale(rs.getString("cust"), rs.getString("prod"), rs.getString("day"),
						rs.getString("month"), rs.getString("year"), rs.getString("quant"), rs.getString("state")));
			}
		}

		catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
		
		Map<String, Customer> custmap = new HashMap<>();

		for (Sale sale : salelist) {
			String cust = sale.getCust();
			if (custmap.containsKey(cust)) { // looking for if the quantity bigger or smaller than saved 
				if (Integer.valueOf(custmap.get(cust).getMinquant().getQuant()) > Integer.valueOf(sale.getQuant())) {
					// update min
					custmap.get(cust).setMinquant(sale);
				}
				if (Integer.valueOf(custmap.get(cust).getMaxquant().getQuant()) < Integer.valueOf(sale.getQuant())) {
					// update max
					custmap.get(cust).setMaxquant(sale);
				}
				//count++
				custmap.get(cust).setCount(custmap.get(cust).getCount() + 1);
				//sumquant += sale.quant
				custmap.get(cust).setSumquant(Integer.valueOf(sale.getQuant()) + custmap.get(cust).getSumquant());
			} else {
				//create a new customer and save to the map, with min and max are both sale
				Customer customer = new Customer(cust, sale, sale);
				customer.setCount(1);
				customer.setSumquant(Integer.valueOf(sale.getQuant()));
				custmap.put(cust, customer);
			}
		}
		Map<String, Customer> prodmap = new HashMap<>();
		for (Sale sale : salelist) {
			String custPord = sale.getCust() + "," + sale.getProd();
			if (!prodmap.containsKey(custPord)) {
				prodmap.put(custPord, new Customer());	//create a new customer with nothing in it
			}
			String state = sale.getState();
			switch (state) {//update the information 
			case "NY":
				if (prodmap.get(custPord).getMinNY() == null || Integer
						.valueOf(prodmap.get(custPord).getMinNY().getQuant()) > Integer.valueOf(sale.getQuant()))
					prodmap.get(custPord).setMinNY(sale);
				break;
			case "NJ":
				if (prodmap.get(custPord).getMaxNJ() == null || Integer
						.valueOf(prodmap.get(custPord).getMaxNJ().getQuant()) < Integer.valueOf(sale.getQuant()))
					prodmap.get(custPord).setMaxNJ(sale);
				break;
			case "CT":
				if (prodmap.get(custPord).getMinCT() == null || Integer
						.valueOf(prodmap.get(custPord).getMinCT().getQuant()) > Integer.valueOf(sale.getQuant()))
					prodmap.get(custPord).setMinCT(sale);
				break;
			default:
				break;

			}

		}
		System.out.println();
		System.out.println("CUSTOMER  MIN_Q  MIN_PROD  MIN_DATE    ST  MAX_Q  MAX_PROD  MAX_DATE    ST  AVG_Q");
		System.out.println("========  =====  ========  ==========  ==  =====  ========  ==========  ==  =====");
		for (Customer cust : custmap.values()) {
			String mindate = String.format("%02d%s%02d%s%4d",Integer.valueOf(cust.getMinquant().getMonth() ),"/",Integer.valueOf(cust.getMinquant().getDay()),"/"
					,Integer.valueOf(cust.getMinquant().getYear()));
			String maxdate = String.format("%02d%s%02d%s%4d",Integer.valueOf(cust.getMaxquant().getMonth() ),"/",Integer.valueOf(cust.getMaxquant().getDay()),"/"
					,Integer.valueOf(cust.getMaxquant().getYear()));
			System.out.println(String.format("%-10s%5d%s%-10s%-12s%-4s%5s%s%-10s%-12s%-4s%5s", cust.getCustname(),
					Integer.valueOf(cust.getMinquant().getQuant()),"  ", cust.getMinquant().getProd(), mindate, cust.getMinquant().getState(),
					cust.getMaxquant().getQuant(),"  ", cust.getMaxquant().getProd(), maxdate, cust.getMaxquant().getState(),
					cust.getAvg()));
		}

		System.out.println();
		System.out.println("CUSTOMER  PORDUCT  NJ_MAX  DATE        NY_MIN  DATE        CT_MIN  DATE        ");
		System.out.println("========  =======  ======  ==========  ======  ==========  ======  ==========  ");
		for (Map.Entry<String, Customer> e : prodmap.entrySet()) {
			String njdate = "null";
			String nydate = "null";
			String ctdate = "null";
			String[] nameprod = e.getKey().split(",");
			String[] quants = new String[3];
			if (e.getValue().getMaxNJ() != null) {
				quants[0] = e.getValue().getMaxNJ().getQuant();
				njdate = String.format("%02d%s%02d%s%4d", Integer.valueOf(e.getValue().getMaxNJ().getDay()) , "/" ,Integer.valueOf( e.getValue().getMaxNJ().getMonth()) , "/"
						, Integer.valueOf(e.getValue().getMaxNJ().getYear()));
			}
			if (e.getValue().getMinNY() != null) {
				quants[1] = e.getValue().getMinNY().getQuant();
				nydate = String.format("%02d%s%02d%s%4d",Integer.valueOf(e.getValue().getMinNY().getDay()) , "/" ,Integer.valueOf(e.getValue().getMinNY().getMonth()),"/"
						, Integer.valueOf(e.getValue().getMinNY().getYear()));
			}
			if (e.getValue().getMinCT() != null) {
				quants[2] = e.getValue().getMinCT().getQuant();
				ctdate = String.format("%02d%s%02d%s%4d",Integer.valueOf(e.getValue().getMinCT().getDay()) , "/" , Integer.valueOf(e.getValue().getMinCT().getMonth()) , "/"
						,Integer.valueOf(e.getValue().getMinCT().getYear()));
			}
			System.out.println(String.format("%-10s%-9s%6s%12s%8s%12s%8s%12s", nameprod[0], nameprod[1],
					quants[0], njdate, quants[1], nydate, quants[2], ctdate));
		}
	}
}
