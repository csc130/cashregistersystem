public class Stack <T> 
{
	private LinkedList <T> list;
	private T head;
	
	public Stack()
	{
		list = new LinkedList <T> ();
	}
	
	public void push(T num)
	{
		list.insert(num);
	}
	
	public LLNode <T> pop()
	{
		return list.delete();
	}
	
	public boolean isEmpty()
	{
		return(head == null);
	}
	
	public T head()
	{
		head = list.getHead();
		return head;
	}
}
