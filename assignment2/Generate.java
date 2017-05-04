package assignment2;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Generate {

	public void generateTable1(List<Sale> salelist) {
		//Integer[0] => sum, Integer[1] => nums
		HashMap<String, Integer[]> custprod = new HashMap<>();	//all state
		HashMap<String, Integer[]> custstate = new HashMap<>();	//all prod
		HashMap<String, Integer[]> custprodstate = new HashMap<>();	

		for (Sale sale : salelist) {
			String cps = sale.getCust() + "," + sale.getProd() + "," + sale.getState();
			if (custprodstate.containsKey(cps)) {
				Integer[] value = custprodstate.get(cps);
				value[0] += sale.getQuant();
				value[1]++;
				custprodstate.put(cps, value);
			} else {
				Integer[] value = { sale.getQuant(), 1 };
				custprodstate.put(cps, value);
			}
			String cp = sale.getCust() + "," + sale.getProd();
			if (custprod.containsKey(cp)) {
				Integer[] value = custprod.get(cp);
				value[0] += sale.getQuant();
				value[1]++;
				custprod.put(cp, value);
			} else {
				Integer[] value = { sale.getQuant(), 1 };
				custprod.put(cp, value);
			}
			String cs = sale.getCust() + "," + sale.getState();
			if (custstate.containsKey(cs)) {
				Integer[] value = custstate.get(cs);
				value[0] += sale.getQuant();
				value[1]++;
				custstate.put(cs, value);
			} else {
				Integer[] value = { sale.getQuant(), 1 };
				custstate.put(cs, value);
			}
		}
		System.out.println();
		System.out.println("Report#1");
		System.out.println("CUSTOMER  PRODUCT  STATE  CUST_AVG  OTHER_STATE_AVG  OTHER_PROD_AVG");
		System.out.println("========  =======  =====  ========  ===============  ==============  ");
		for (Entry<String, Integer[]> e : custprodstate.entrySet()) {

			String[] key = e.getKey().split(",");
			Integer[] custavg = e.getValue();
			int ca = custavg[0] / custavg[1];

			String otherstate = key[0] + "," + key[1];
			Integer[] allstate = custprod.get(otherstate);
			//allstate - state = otherstate
			int otherstateavg = (allstate[0] - custavg[0]) / (allstate[1] - custavg[1]);
			String otherprod = key[0] + "," + key[2];
			Integer[] otherProdAvg = custstate.get(otherprod);
			//allprod - prod = otherprod
			int opa = (otherProdAvg[0] - custavg[0]) / (otherProdAvg[1] - custavg[1]);	
			System.out.println(String.format("%-10s%-9s%-5s%10d%17d%16d", key[0], key[1], key[2], ca, otherstateavg, opa));
		}
	}

	public void generateTable2(List<Sale> salelist) {
		HashMap<String, Integer[]> map = new HashMap<>();
		for (Sale sale : salelist) {
			String key = sale.getCust() + "," + sale.getProd() + "," + sale.getMonth();
			if (!map.containsKey(key)) {
				Integer[] v = { sale.getQuant(), 1 };
				map.put(key, v);
			} else {
				Integer[] v = map.get(key);
				v[0] += sale.getQuant();
				v[1]++;
			}
		}
		System.out.println();
		System.out.println("Report#2");
		System.out.println("CUSTOMER  PRODUCT  MONTH  BEFORE_AVG  AFTER_AVG");
		System.out.println("========  =======  =====  ==========  =========");
		for (Entry<String, Integer[]> e : map.entrySet()) {
			String[] key = e.getKey().split(",");
			String before = key[0] + "," + key[1] + "," + (Integer.valueOf(key[2]) - 1);	//get before month
			String bAvg;
			if (!map.containsKey(before)) {
				bAvg = "<Null>";
			} else {
				Integer[] v = map.get(before);
				bAvg = String.valueOf((int) v[0] / v[1]);
			}
			String after = key[0] + "," + key[1] + "," + (Integer.valueOf(key[2]) + 1);	//get after month
			String aAvg;
			if (!map.containsKey(after)) {
				aAvg = "<Null>";
			} else {
				Integer[] v = map.get(after);
				aAvg = String.valueOf((int) v[0] / v[1]);
			}
			// System.out.println(key[0]+","+key[1]+","+key[2]+","+bAvg+","+aAvg);
			System.out.println(String.format("%-10s%-9s%5s%12s%11s", key[0], key[1], key[2], bAvg, aAvg));
		}

	}

	public void generateTable3(List<Sale> salelist) {
		HashMap<String, Integer> month = new HashMap<>();
		HashMap<String, Integer> custprod = new HashMap<>();
		for (Sale sale : salelist) {
			String mkey = sale.getCust() + "," + sale.getProd() + "," + sale.getMonth();
			if (!month.containsKey(mkey)) {
				month.put(mkey, sale.getQuant());
			} else {
				month.put(mkey, month.get(mkey) + sale.getQuant());
			}
			String ckey = sale.getCust() + "," + sale.getProd();
			if (!custprod.containsKey(ckey)) {
				custprod.put(ckey, sale.getQuant());
			} else {
				custprod.put(ckey, custprod.get(ckey) + sale.getQuant());
			}
		}
		System.out.println();
		System.out.println("Report#3");
		System.out.println("CUSTOMER  PRODUCT  1/3 PURCHASED BY MONTH");
		System.out.println("========  =======  ======================");
		for (Entry<String, Integer> e : custprod.entrySet()) {
			String[] ckey = e.getKey().split(",");
			
			int onethird = (int) Math.ceil(e.getValue() / 3);
			
			//System.out.println(ckey[0]+","+ckey[1]+",sum: "+e.getValue()+", onethird: "+onethird);
			int mon = 0;
			int sum = 0;
			for (int i = 1; i <= 12; i++) {	//sum all month until equals or greater than 1/3
				String key = ckey[0] + "," + ckey[1] + "," + i;
				if (month.containsKey(key)) {
					sum += month.get(key);
				}
				if (sum >= onethird) { 
					mon = i;
					break;
				}
				//System.out.println(key+"  sum: "+sum+" i: "+i);
			}
			// System.out.println(ckey[0]+","+ckey[1]+","+mon);
			System.out.println(String.format("%-10s%-9s%-10s", ckey[0], ckey[1], mon));
		}
	}
}
