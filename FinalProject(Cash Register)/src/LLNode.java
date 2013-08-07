public class LLNode <T>
{
	T data;
	LLNode <T> link;
	
	public LLNode()
	{
		super();
	}
	
	public LLNode(T data)
	{
		this.data = data;
		link = null;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data) 
	{
		this.data = data;
	}

	public LLNode <T> getLink() 
	{
		return link;
	}

	public void setLink(LLNode <T> link) 
	{
		this.link = link;
	}
}
