package assignment1;

public class Note {
	/*
	 * select cust,min(q),max(q),avg(q)
	 * from sales
	 * group by cust
	 * 
	 * => view or with
	 */
	
	/**
	 *WITH BASE AS 
	 * (
	 * select cust,min(q),max(q),avg(q)
	 * from sales
	 * group by cust
	 * ),
	 * min as
	 * (select B.cust,B.MQ,S.prod,S.month,S.year,S.year,S.state 
	 * from BASE B, sales S
	 * where B.cust = B.cust and B.quant = S.quant
	 * ),
	 * max as
	 * (
	 * 
	 * from min,sales
	 * 
	 * )
	 * 
	 **/
}
