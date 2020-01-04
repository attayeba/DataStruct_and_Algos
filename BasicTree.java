/*
Comp 352-Assignment 3
*/
public class BasicTree {

	public class Node implements BaseNode
	{
			private int data;
			private BaseNode parent;
			private BaseNode next;
			private BaseNode previous;
			
			public Node()
			{
				data=0;
				next=null;
				previous=null;
			}
			
			public Node(int data)
			{
				this.data=data;
				next=null;
				previous=null;
			}
			
			public void setData(int data)
			{
				this.data=data;
			}
			
			public void setParent(BaseNode parent)
			{
				this.parent=parent;
			}
			
			public void setNext(BaseNode child)
			{
				next=child;
				if(child != null)
					child.setParent(this);
			}
			
			public void setPrevious(BaseNode child)
			{
				previous=child;
				if(child != null)
					child.setParent(this);
			}
			
			public int getData()
			{
				return data;
			}

			public BaseNode getPrevious()
			{
				return previous;
			}
						
			public BaseNode getNext()
			{
				return next;
			}
			
			public BaseNode getParent()
			{
				return parent;
			}
			
			public int getCharacter()
			{
				return -1;
			}
			
			public int getOrder()
			{
				return -1;
			}
			
			public boolean isLeaf()
			{
				return (previous==null && next==null);
			}
	}

	private BaseNode root;
	
	public BasicTree()
	{
		super();
	}
	
	public void setRoot(BaseNode root)
	{
		this.root=root;
	}
	
	public BaseNode getRoot()
	{
		return root;
	}
}
