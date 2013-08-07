import java.text.DecimalFormat;
public class Item
{
	private String description;
	private double price;
	DecimalFormat num = new DecimalFormat("###.00");
	public Item(String description, double price)
	{
		this.description = description;
		this.price = price;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String toString()
	{
		String format = "%1$1s %2$25s";
		return String.format(format,description,num.format(price));
		//return description+"                       $"+num.format(price);
	}
}
