/*
Comp 352-Assignment 3
*/

public interface BaseNode
{
	public boolean isLeaf();
	
	public int getData();
	public BaseNode getPrevious();
	public BaseNode getNext();
	public BaseNode getParent();
	public int getCharacter();
	public int getOrder();
	
	public void setData(int data);
	public void setPrevious(BaseNode previous);
	public void setNext(BaseNode next);
	public void setParent(BaseNode parent);
}