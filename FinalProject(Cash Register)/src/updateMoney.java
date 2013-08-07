import java.text.DecimalFormat;

public class updateMoney
{
	private double money;
	Stack <Integer> twentiesTemp;
	Stack <Integer> tensTemp;
	Stack <Integer> fivesTemp;
	Stack <Integer> onesTemp;
	Stack <Integer> quartersTemp;
	Stack <Integer> dimesTemp;
	Stack <Integer> nickelsTemp;
	Stack <Integer> penniesTemp;
	
	public updateMoney(double money, Stack<Integer>twenties, Stack<Integer>tens, Stack<Integer>fives,
			Stack<Integer>ones, Stack<Integer>quarters, Stack<Integer>dimes, Stack<Integer>nickels, 
			Stack<Integer>pennies)
	{
		this.money = money;
		this.twentiesTemp = twenties;
		this.tensTemp = tens;
		this.fivesTemp = fives;
		this.onesTemp = ones;
		this.quartersTemp = quarters;
		this.dimesTemp = dimes;
		this.nickelsTemp = nickels;
		this.penniesTemp = pennies;
		
		execute();	
	}
	
	public void execute()
	{
		DecimalFormat num = new DecimalFormat("###.00");
		money = Double.valueOf(num.format(money));
	
		int twenties = (int)money / 20;
		int twentiesR = (int)money %20;
		int tens = twentiesR / 10;
		int tensR = twentiesR % 10;
		int fives = tensR / 5;
		int fivesR = tensR % 5;
		int ones = fivesR / 1;
		
		double cents = Double.valueOf(num.format(money - (int)money));
		double quarters = Double.valueOf(num.format(cents / 0.25));
		double quartersR = Double.valueOf(num.format((cents % 0.25)));
		double dimes = Double.valueOf(num.format((quartersR / 0.10)));
		double dimesR = Double.valueOf(num.format((quartersR % 0.10)));
		double nickels = Double.valueOf(num.format((dimesR / 0.05)));
		double nickelsR = Double.valueOf(num.format((dimesR % 0.05)));
		double pennies = Double.valueOf(num.format((nickelsR / 0.01)));
			
		int twentiesHead = twentiesTemp.head();
		twentiesTemp.pop();
		int twentiesUpdate = twentiesHead + twenties;
		twentiesTemp.push(twentiesUpdate);

		int tensHead = tensTemp.head();
		tensTemp.pop();
		int tensUpdate = tensHead + tens;
		tensTemp.push(tensUpdate);
		
		int fivesHead = fivesTemp.head();
		fivesTemp.pop();
		int fivesUpdate = fivesHead + fives;
		fivesTemp.push(fivesUpdate);
		
		int onesHead = onesTemp.head();
		onesTemp.pop();
		int onesUpdate = onesHead + ones;
		onesTemp.push(onesUpdate);
		
		int quartersHead = quartersTemp.head();
		quartersTemp.pop();
		int quartersUpdate = quartersHead + (int)quarters;
		quartersTemp.push(quartersUpdate);
		
		int dimesHead = dimesTemp.head();
		dimesTemp.pop();
		int dimesUpdate = dimesHead + (int)dimes;
		dimesTemp.push(dimesUpdate);
		
		int nickelsHead = nickelsTemp.head();
		nickelsTemp.pop();
		int nickelsUpdate = nickelsHead + (int)nickels;
		nickelsTemp.push(nickelsUpdate);
		
		int penniesHead = penniesTemp.head();
		penniesTemp.pop();
		int penniesUpdate = penniesHead + (int)pennies;
		penniesTemp.push(penniesUpdate);
	}
}
