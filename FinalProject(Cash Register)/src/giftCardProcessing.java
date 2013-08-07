import java.text.DecimalFormat;

public class giftCardProcessing 
{
	@SuppressWarnings("unused")
	private String cardNumber;
	private Stack <Double> giftCardStackTemp;
	private double saleTotalTemp;
	
	DecimalFormat num = new DecimalFormat("###.00");
	
	public giftCardProcessing(String cardNumber, Stack<Double>giftCardStack, double saleTotal)
	{
		this.cardNumber = cardNumber;
		this.giftCardStackTemp = giftCardStack;
		this.saleTotalTemp = Double.valueOf(num.format(saleTotal));
		giftCardPayment();
	}
	
	public void giftCardPayment()
	{
		if(giftCardStackTemp.isEmpty())
		{
			giftCardStackTemp.push(0.0);
		}
		
		double giftCardHead = giftCardStackTemp.head();
		giftCardStackTemp.pop();
		double giftCardUpdate = saleTotalTemp + giftCardHead;
		giftCardStackTemp.push(giftCardUpdate);
	}
}
