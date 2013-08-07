import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class RegisterGUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JPanel infoPanel, registerPanel, paymentPanel, mainPanel;
	JTextArea runningTotal, infoDisplay, moneyCount;
	JTextField itemDescription, itemPrice, payment, cardNumber;
	JButton addItem, total, cash, creditCard, giftCard, paymentEnter, next, signOff;
	JButton displayTill, addTwenties, addTens, addFives, addOnes, addQuarters, addDimes, addNickels, addPennies;
	JLabel itemDescriptionLabel, itemPriceLabel, paymentLabel, cardNumberLabel;
	Item aItem;
	LinkedList <Item> itemList = new LinkedList<Item>();
	
	Stack <Integer> twenties = new Stack<Integer>();
	Stack <Integer> tens = new Stack<Integer>();
	Stack <Integer> fives = new Stack<Integer>();
	Stack <Integer> ones = new Stack<Integer>();
	Stack <Integer> quarters = new Stack<Integer>();
	Stack <Integer> dimes = new Stack<Integer>();
	Stack <Integer> nickels = new Stack<Integer>();
	Stack <Integer> pennies = new Stack<Integer>();
	
	Stack <Double> visa = new Stack<Double>();
	Stack <Double> masterCard = new Stack<Double>();
	Stack <Double> discover = new Stack<Double>();
	Stack <Double> americanExpress = new Stack<Double>();
	Stack <Double> giftCardStack = new Stack<Double>();
	Stack <Double> cashStack = new Stack<Double>();
	
	Stack <Double> salesTotalStack = new Stack<Double>();
	Stack <Double> moneyAddedStack = new Stack<Double>();
	
	double saleTotal = 0;
	String tillString;

	private int count20 = 0;
	private int count10 = 0;
	private int count5 = 0;
	private int count1 = 0;
	private int count25c = 0;
	private int count10c = 0;
	private int count5c = 0;
	private int count1c = 0;
	private double totalMoneyAdded = 0;
	
	String paymentSelect;
	
	DecimalFormat num = new DecimalFormat("###.00");
	
	public RegisterGUI()
	{
		initializeGUI();
	}
	
	public void initializeGUI()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(2,2));
		
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(1370, 100));
		infoPanel.setBackground(Color.black);
		infoPanel.setLayout(null);
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		
		registerPanel = new JPanel();
		registerPanel.setPreferredSize(new Dimension(1370, 0));
		registerPanel.setBackground(Color.gray);
		registerPanel.setLayout(null);
		mainPanel.add(registerPanel, BorderLayout.CENTER);
		
		paymentPanel = new JPanel();
		paymentPanel.setPreferredSize(new Dimension(500, 0));
		paymentPanel.setBackground(Color.gray);
		paymentPanel.setLayout(null);
		paymentPanel.setVisible(false);
		mainPanel.add(paymentPanel, BorderLayout.EAST);
		
		runningTotal = new JTextArea();
		runningTotal.setBackground(Color.black);
		runningTotal.setForeground(Color.white);
		runningTotal.setBounds(475, 20, 500, 75);
		runningTotal.setEditable(false);
		Font totalFont = new Font(Font.SERIF, Font.BOLD, 45);
		runningTotal.setFont(totalFont);
		runningTotal.setText("BALANCE DUE: $0.00");
		infoPanel.add(runningTotal);
		
		signOff = new JButton("Sign Off");
		signOff.setBackground(Color.white);
		signOff.setBounds(1270, 5, 80, 40);
		infoPanel.add(signOff);
		signOff.addActionListener(this);
		
		Font textFont = new Font(Font.SERIF, Font.BOLD, 15);
		
		itemDescription = new JTextField();
		itemDescriptionLabel = new JLabel("Enter Item Desciption");
		itemDescription.setFont(textFont);
		itemDescription.setBounds(10, 30, 150, 30);
		itemDescriptionLabel.setBounds(10, 5, 150, 30);
		registerPanel.add(itemDescription);
		registerPanel.add(itemDescriptionLabel);
		
		itemPrice = new JTextField();
		itemPriceLabel = new JLabel("Enter Price");
		itemPrice.setFont(textFont);
		itemPrice.setBounds(200, 30, 150, 30);
		itemPriceLabel.setBounds(200, 5, 150, 30);
		registerPanel.add(itemPrice);
		registerPanel.add(itemPriceLabel);
		
		addItem = new JButton("Add Item");
		addItem.setBackground(Color.yellow);
		addItem.setBounds(390, 30, 100, 30);
		registerPanel.add(addItem);
		addItem.addActionListener(this);
		
		Font infoFont = new Font(Font.MONOSPACED, Font.BOLD, 15);
		infoDisplay = new JTextArea();
		infoDisplay.setEditable(false);
		infoDisplay.setFont(infoFont);
		infoDisplay.setBounds(10, 75, 480, 500);
		registerPanel.add(infoDisplay);
		
		total = new JButton("Total");
		total.setBackground(Color.yellow);
		total.setBounds(500, 505, 100, 70);
		registerPanel.add(total);
		total.addActionListener(this);
		
		displayTill = new JButton("Display Till");
		displayTill.setBackground(Color.yellow);
		displayTill.setBounds(500, 30, 165, 35);
		registerPanel.add(displayTill);
		displayTill.addActionListener(this);
		
		addTwenties = new JButton("Add $20");
		addTwenties.setBackground(Color.yellow);
		addTwenties.setBounds(500, 75, 80, 35);
		registerPanel.add(addTwenties);
		addTwenties.addActionListener(this);
		
		addTens = new JButton("Add $10");
		addTens.setBackground(Color.yellow);
		addTens.setBounds(585, 75, 80, 35);
		registerPanel.add(addTens);
		addTens.addActionListener(this);
		
		addFives = new JButton("Add $5");
		addFives.setBackground(Color.yellow);
		addFives.setBounds(670, 75, 80, 35);
		registerPanel.add(addFives);
		addFives.addActionListener(this);
		
		addOnes = new JButton("Add $1");
		addOnes.setBackground(Color.yellow);
		addOnes.setBounds(755, 75, 80, 35);
		registerPanel.add(addOnes);
		addOnes.addActionListener(this);
		
		addQuarters = new JButton("Add 25¢");
		addQuarters.setBackground(Color.yellow);
		addQuarters.setBounds(500, 125, 80, 35);
		registerPanel.add(addQuarters);
		addQuarters.addActionListener(this);
		
		addDimes = new JButton("Add 10¢");
		addDimes.setBackground(Color.yellow);
		addDimes.setBounds(585, 125, 80, 35);
		registerPanel.add(addDimes);
		addDimes.addActionListener(this);
		
		addNickels = new JButton("Add 5¢");
		addNickels.setBackground(Color.yellow);
		addNickels.setBounds(670, 125, 80, 35);
		registerPanel.add(addNickels);
		addNickels.addActionListener(this);
		
		addPennies = new JButton("Add 1¢");
		addPennies.setBackground(Color.yellow);
		addPennies.setBounds(755, 125, 80, 35);
		registerPanel.add(addPennies);
		addPennies.addActionListener(this);
		
		moneyCount = new JTextArea();
		moneyCount.setEditable(false);
		moneyCount.setFont(textFont);
		moneyCount.setBounds(500, 175, 335, 250);
		registerPanel.add(moneyCount);
		
		cash = new JButton("Cash");
		cash.setBackground(Color.yellow);
		cash.setBounds(10, 10, 65, 40);
		paymentPanel.add(cash);
		cash.addActionListener(this);
		
		creditCard = new JButton("Credit Card");
		creditCard.setBackground(Color.yellow);
		creditCard.setBounds(190, 10, 100, 40);
		paymentPanel.add(creditCard);
		creditCard.addActionListener(this);
		
		giftCard = new JButton("Gift Card");
		giftCard.setBackground(Color.yellow);
		giftCard.setBounds(400, 10, 90, 40);
		paymentPanel.add(giftCard);
		giftCard.addActionListener(this);
		
		payment = new JTextField();
		payment.setBounds(150, 100, 200, 40);
		paymentLabel = new JLabel("Enter Payment: $");
		paymentLabel.setBounds(45, 95, 100, 50);
		paymentPanel.add(paymentLabel);
		paymentPanel.add(payment);
		
		paymentEnter = new JButton("Process");
		paymentEnter.setBackground(Color.yellow);
		paymentEnter.setBounds(360, 100, 100, 40);
		paymentPanel.add(paymentEnter);
		paymentEnter.addActionListener(this);
		
		next = new JButton("Next");
		next.setBackground(Color.yellow);
		next.setBounds(360, 150, 100, 40);
		paymentPanel.add(next);
		next.addActionListener(this);
		
		cardNumber = new JTextField();
		cardNumber.setBounds(150, 150, 200, 40);
		cardNumberLabel = new JLabel("Enter Card Number:");
		cardNumberLabel.setBounds(20, 145, 125, 50);
		cardNumber.setVisible(false);
		cardNumberLabel.setVisible(false);
		paymentPanel.add(cardNumberLabel);
		paymentPanel.add(cardNumber);
		
		add(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1370, 725);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "Add Item")
		{
			String description = itemDescription.getText();
			double price = Double.parseDouble(itemPrice.getText());
			aItem = new Item(description, price);
			itemList.insert(aItem);
			infoDisplay.setText(itemList.toString());
			double getItemPrice =  aItem.getPrice();
			saleTotal = saleTotal + getItemPrice;
			runningTotal.setText("BALANCE DUE: $"+num.format(saleTotal));	
			itemDescription.setText("");
			itemPrice.setText("");
		}
		
		if(e.getActionCommand() == "Display Till")
		{	
			tillString = "Twenties Count: "+twenties.head()+"\nTens Count: "+tens.head()+
					"\nFives Count: "+fives.head()+"\nOnes Count: "+ones.head()+
					"\nQuarters Count: "+quarters.head()+"\nDimes Count: "+dimes.head()+
					"\nNickels Count: "+nickels.head()+"\nPennies Count: "+pennies.head();
			moneyCount.setText(tillString);
			
			try
			{
			
				if(twenties.head() <= 0)
				{
					throw new Exception("$20 Stack Empty.  Add $20");
				}
				
				if(tens.head() <= 0)
				{
					throw new Exception("$10 Stack Empty.  Add $10");
				}
				
				if(fives.head() <= 0)
				{
					throw new Exception("$5 Stack Empty.  Add $5");
				}
				
				if(ones.head() <= 0)
				{
					throw new Exception("$1 Stack Empty.  Add $1");
				}
				
				if(quarters.head() <= 0)
				{
					throw new Exception("25¢ Stack Empty.  Add 25¢");
				}
				
				if(dimes.head() <= 0)
				{
					throw new Exception("10¢ Stack Empty.  Add 10¢");
				}
				
				if(nickels.head() <= 0)
				{
					throw new Exception("5¢ Stack Empty.  Add 5¢");
				}
				
				if(pennies.head() <= 0)
				{
					throw new Exception("1¢ Stack Empty.  Add 1¢");
				}
			}
			
			catch(Exception b)
			{
				moneyCount.setText(b.getMessage());
			}
		}
		
		if(e.getActionCommand() == "Add $20")
		{
			if(count20 == 0)
			{
				twenties.push(0);
			}
			
			count20 += 1;
			totalMoneyAdded += 100.00;
			
			@SuppressWarnings("unused")
			addMoney passTwenties = new addMoney(twenties, "20", 5);
		}
		
		if(e.getActionCommand() == "Add $10")
		{
			if(count10 == 0)
			{
				tens.push(0);
			}
			
			count10 += 1;
			totalMoneyAdded += 100.00;
			
			@SuppressWarnings("unused")
			addMoney passTens = new addMoney(tens, "10", 10);
		}
		
		if(e.getActionCommand() == "Add $5")
		{
			if(count5 == 0)
			{
				fives.push(0);
			}
			
			count5 += 1;
			totalMoneyAdded += 50.00;
			
			@SuppressWarnings("unused")
			addMoney passFives = new addMoney(fives, "5", 10);
		}
		
		if(e.getActionCommand() == "Add $1")
		{
			if(count1 == 0)
			{
				ones.push(0);
			}
			
			count1 += 1;
			totalMoneyAdded += 50.00;
			
			@SuppressWarnings("unused")
			addMoney passOnes = new addMoney(ones, "1", 50);
		}
		
		if(e.getActionCommand() == "Add 25¢")
		{
			if(count25c == 0)
			{
				quarters.push(0);
			}
			
			count25c += 1;
			totalMoneyAdded += 10.00;
			
			@SuppressWarnings("unused")
			addMoney passQuarters = new addMoney(quarters, "25c", 40);
		}
		
		if(e.getActionCommand() == "Add 10¢")
		{
			if(count10c == 0)
			{
				dimes.push(0);
			}
			
			count10c += 1;
			totalMoneyAdded += 5.00;
			
			@SuppressWarnings("unused")
			addMoney passDimes = new addMoney(dimes, "10c", 50);
		}
		
		if(e.getActionCommand() == "Add 5¢")
		{
			if(count5c == 0)
			{
				nickels.push(0);
			}
			
			count5c += 1;
			totalMoneyAdded += 2.00;
			
			@SuppressWarnings("unused")
			addMoney passNickels = new addMoney(nickels, "5c", 40);
		}
		
		if(e.getActionCommand() == "Add 1¢")
		{
			if(count1c == 0)
			{
				pennies.push(0);
			}
			
			count1c += 1;
			totalMoneyAdded += 0.50;
			
			@SuppressWarnings("unused")
			addMoney passPennies = new addMoney(pennies, "1c", 50);
		}
		
		if(e.getActionCommand() == "Total")
		{
			paymentPanel.setVisible(true);
			saleTotal = ((saleTotal * 0.05) + saleTotal);
			runningTotal.setText("BALANCE DUE: $"+num.format(saleTotal));
			
		}
		
		if(e.getActionCommand() == "Cash")
		{
			paymentSelect = "Cash";
		}
		
		if(e.getActionCommand() == "Credit Card")
		{
			cardNumber.setVisible(true);
			cardNumberLabel.setVisible(true);
			payment.setEditable(false);
			paymentSelect = "Credit Card";
		}
		
		if(e.getActionCommand() == "Gift Card")
		{
			cardNumber.setVisible(true);
			cardNumberLabel.setVisible(true);
			payment.setEditable(false);
			paymentSelect = "Gift Card";
		}	
		
		if(e.getActionCommand() == "Process")
		{
			if(paymentSelect == "Credit Card")
			{	
				try
				{
					if(cardNumber.getText().length() == 16)
					{
						@SuppressWarnings("unused")
						creditCardProcessing pass = new creditCardProcessing(saleTotal, 
								cardNumber.getText(), visa, masterCard, discover, americanExpress);
					}
					
					else
					{
						throw new Exception("Invalid Card Length");
					}
				}
				
				catch(Exception i)
				{
					cardNumber.setText(i.getMessage());
				}
			}
			
			if(paymentSelect == "Cash")
			{
				try
				{
					if(Double.valueOf(payment.getText()) < saleTotal)
					{
						throw new Exception("Not Enough Cash");
					}
					
					else
					{
						Double change = Double.valueOf(payment.getText());
						change = change - saleTotal;
						String change1 = num.format(change);
						infoDisplay.setText((change1));
						@SuppressWarnings("unused")
						removeMoney passChange = new removeMoney(change, twenties, tens, fives,
								ones, quarters, dimes, nickels, pennies);
						@SuppressWarnings("unused")
						updateMoney passMoney = new updateMoney(Double.valueOf(payment.getText()), 
								twenties, tens, fives, ones, 
								quarters, dimes, nickels, pennies);
						cashStack.push(saleTotal);
					}	
				}
				
				catch(Exception n)
				{
					payment.setText(n.getMessage());
				}
			}
			
			if(paymentSelect == "Gift Card")
			{
				@SuppressWarnings("unused")
				giftCardProcessing pass = new giftCardProcessing(cardNumber.getText(), 
						giftCardStack, saleTotal);
			}
		}
		
		if(e.getActionCommand() == "Next")
		{
			nextTransaction();
		}
		
		if(e.getActionCommand() == "Sign Off")
		{		
			PrintWriter outputStream = null;
			
			SimpleDateFormat dateF = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
			String date = dateF.format(Calendar.getInstance().getTime());
			
			double tillTotal = Double.valueOf(num.format((twenties.head()*20)+(tens.head()*10)+(fives.head()*5)
					+(ones.head()*1)+(quarters.head()*0.25)+(dimes.head()*0.10)
					+(nickels.head()*0.05)+(pennies.head()*0.01)));
			
			double cashTotal = Double.valueOf(cashStack.head() + 317.50);
			
			String fileName = "Sales Report";
			try
			{
				outputStream = new PrintWriter(new FileWriter(fileName, true));
				outputStream.println("Time Stamp: "+date);
				outputStream.println("Sale Total: "+num.format(salesTotalStack.head()));
				outputStream.println("Till Contents: ");
				outputStream.println(tillString);
				outputStream.println("Till Count: "+num.format(tillTotal));
				outputStream.println("Cash Count: "+num.format(cashTotal));
				outputStream.println("Money Added: "+num.format(moneyAddedStack.head()));
				outputStream.println("Cash Total: "+num.format(cashStack.head()));
				outputStream.println("Visa Total: "+visa.head());
				outputStream.println("MasterCard Total: "+masterCard.head());
				outputStream.println("Discover Total: "+discover.head());
				outputStream.println("American Express Total: "+americanExpress.head());
				outputStream.println("Gift Card Total: "+giftCardStack.head());
				outputStream.println("");
				outputStream.close();
			}
			
			catch(Exception a)
			{
				System.out.println("Error Writing File");
				System.exit(0);
			}
			
			dispose();
		}
	}
	
	public void nextTransaction()
	{
		if(salesTotalStack.isEmpty())
		{
			salesTotalStack.push(0.0);
		}
		
		double salesTotalStackHead = salesTotalStack.head();
		salesTotalStack.pop();
		double salesTotalStackUpdate = salesTotalStackHead + Double.valueOf(num.format(saleTotal));
		salesTotalStack.push(salesTotalStackUpdate);
		
		if(moneyAddedStack.isEmpty())
		{
			moneyAddedStack.push(0.0);
		}
		
		if(totalMoneyAdded != moneyAddedStack.head())
		{
			double moneyAddedStackHead = moneyAddedStack.head();
			moneyAddedStack.pop();
			double moneyAddedStackUpdate = moneyAddedStackHead + totalMoneyAdded;
			moneyAddedStack.push(moneyAddedStackUpdate);
		}
		
		itemList.clear();
		infoDisplay.setText("");
		payment.setText("");
		cardNumber.setText("");
		paymentSelect = "";
		runningTotal.setText("BALANCE DUE: $0.00");
		paymentPanel.setVisible(false);
		payment.setEditable(true);
		totalMoneyAdded = 0.0;
		saleTotal = 0.0;
	}
	
}
