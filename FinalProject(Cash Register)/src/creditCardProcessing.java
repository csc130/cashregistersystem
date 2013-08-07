import java.text.DecimalFormat;

public class creditCardProcessing 
{
	private double saleTotalTemp;
	private String cardNumber;
	private String firstFour;
	Stack <Double> visaTemp;
	Stack <Double> masterCardTemp;
	Stack <Double> discoverTemp;
	Stack <Double> americanExpressTemp;
	
	DecimalFormat num = new DecimalFormat("###.00");
	
	creditCardProcessing(double saleTotal, String cardNumber, Stack<Double>visa, Stack<Double>masterCard, 
			Stack<Double>discover, Stack<Double>americanExpress)
	{
		this.saleTotalTemp = Double.valueOf(num.format(saleTotal));
		this.cardNumber = cardNumber;
		this.visaTemp = visa;
		this.masterCardTemp = masterCard;
		this.discoverTemp = discover;
		this.americanExpressTemp = americanExpress;
		creditCardPayment();
	}
	
	public void creditCardPayment()
	{
		if(visaTemp.isEmpty())
		{
			visaTemp.push(0.0);
		}
		
		if(masterCardTemp.isEmpty())
		{
			masterCardTemp.push(0.0);
		}
		
		if(discoverTemp.isEmpty())
		{
			discoverTemp.push(0.0);
		}
		
		if(americanExpressTemp.isEmpty())
		{
			americanExpressTemp.push(0.0);
		}
		
		firstFour = cardNumber.substring(0, 4);
		switch(firstFour)
		{
			case "4108":
				double visaHead = visaTemp.head();
				visaTemp.pop();
				double visaUpdate = visaHead + saleTotalTemp;
				visaTemp.push(visaUpdate);
				break;
			case "5147":
				double masterCardHead = masterCardTemp.head();
				masterCardTemp.pop();
				double masterCardUpdate = masterCardHead + saleTotalTemp;
				masterCardTemp.push(masterCardUpdate);
				break;
			case "8172":
				double discoverHead = discoverTemp.head();
				discoverTemp.pop();
				double discoverUpdate = discoverHead + saleTotalTemp;
				discoverTemp.push(discoverUpdate);
				break;
			case "7901":
				double americanExpressHead = americanExpressTemp.head();
				americanExpressTemp.pop();
				double americanExpressUpdate = americanExpressHead + saleTotalTemp;
				americanExpressTemp.push(americanExpressUpdate);
				break;
		}
	}
}
