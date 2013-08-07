public class addMoney 
{
	Stack <Integer> tempStack;
	
	String moneySelect;
	int addAmount;
	boolean setDefault;
	
	public addMoney(Stack <Integer> tempStack, String moneySelect, int addAmount)
	{
		this.tempStack = tempStack;
		this.moneySelect = moneySelect;
		this.addAmount = addAmount;
		
		execute();
	}
	
	public void execute()
	{
		switch(moneySelect)
		{
			case "20": 
				int head20 = tempStack.head();
				tempStack.pop();
				int add20 = addAmount + head20;
				tempStack.push(add20);
				break;
			case "10":
				int head10 = tempStack.head();
				tempStack.pop();
				int add10 = addAmount + head10;
				tempStack.push(add10);
				break;
			case "5":
				int head5 = tempStack.head();
				tempStack.pop();
				int add5 = addAmount + head5;
				tempStack.push(add5);
				break;
			case "1":
				int head1 = tempStack.head();
				tempStack.pop();
				int add1 = addAmount + head1;
				tempStack.push(add1);
				break;
			case "25c":
				int head25c = tempStack.head();
				tempStack.pop();
				int add25c = addAmount + head25c;
				tempStack.push(add25c);
				break;	
			case "10c":
				int head10c = tempStack.head();
				tempStack.pop();
				int add10c = addAmount + head10c;
				tempStack.push(add10c);
				break;
			case "5c":
				int head5c = tempStack.head();
				tempStack.pop();
				int add5c = addAmount + head5c;
				tempStack.push(add5c);
				break;
			case "1c":
				int head1c = tempStack.head();
				tempStack.pop();
				int add1c = addAmount + head1c;
				tempStack.push(add1c);
				break;
		}	
	}
}
