public class LinkedList <T>
{
	LLNode <T> head = new LLNode<T>();
	LLNode <T> end = new LLNode<T>();
		
	public LinkedList()
	{
		head = null;
	}
	
	public void insert(T data)
	{
		LLNode <T> newNode = new LLNode<T>(data);
		end.setLink(null);
		if(isEmpty())
		{
			head = newNode;
		}
		
		else
		{
			end.setLink(newNode);
		}
		
		end = newNode;
	}
	
	public LLNode <T> delete()
	{
		LLNode <T> temp = null;
		try
		{
			temp = head;
			head = head.link;
		}
		catch(Exception e)
		{
			System.err.println("Pop From Empty Stack!");
		}
		return temp;
	}
	
	public boolean isEmpty()
	{
		return(head == null);
	}
	
	public void clear()
	{
		head = null;
	}
	
	public T getHead() 
	{
		return head.getData();
	}

	public void setHead(LLNode <T> head) 
	{
		this.head = head;
	}
	
	public String toString()
	{
		LLNode <T> tempNode = head;
		String itemList = "";
		while(tempNode!=null)
		{
			itemList+=(tempNode.getData()+"\n");
			tempNode = tempNode.getLink();
		}
		
		return itemList;
	}
}
