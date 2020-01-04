/*
Comp 352-Assignment 3
*/

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class ATree extends BasicTree{

	private int size;
	private int find;
	private int add;
	private int remove;
	private int comparison;
	private int headChanges;
	
	public ATree()
	{
		super();
		size=0;
		find=0;
		add=0;
		remove=0;
		comparison=0;
		size=0;
		headChanges=0;
	}
	
	public int getSize()
	{
		return size;
	}
		
	public int getFind()
	{
		return find;
	}
	
	public int getAdd()
	{
		return add;
	}
	
	public int getRemove()
	{
		return remove;
	}
	
	public int getComparison()
	{
		return comparison;
	}
	
	public int getHeadChanges()
	{
		return headChanges;
	}
	
	private BaseNode singleLeftRotation(BaseNode child)
	{
		headChanges=headChanges+5;
		
		BaseNode parent=child.getParent();
		
		BaseNode temp1=child.getPrevious();
		BaseNode temp2=child.getNext();
		BaseNode temp3=parent.getNext();
		
		
		child.setPrevious(temp1);
		child.setNext(parent);
		parent.setPrevious(temp2);
		parent.setNext(temp3);

		return child;
	}
	
	private BaseNode singleRightRotation(BaseNode child)
	{
		headChanges=headChanges+5;
		
		BaseNode parent=child.getParent();
		
		BaseNode temp1=child.getNext();
		BaseNode temp2=parent.getPrevious();
		BaseNode temp3=child.getPrevious();
			
		child.setPrevious(parent);
		child.setNext(temp1);
		parent.setPrevious(temp2);
		parent.setNext(temp3);
		
		return child;
	}
	
	private void splay(BaseNode child)
	{
		
		BaseNode temp=null;
		while(child.getParent() != null)
		{
			if(child==getRoot())
			{
				return;
			}
			else if(child.getParent()==getRoot())
			{
				if(child == child.getParent().getNext())
				{
					temp=singleRightRotation(child);
					
				}
				else if(child == child.getParent().getPrevious())
				{
					temp=singleLeftRotation(child);
				}
				
				setRoot(temp);
				child=getRoot();
				getRoot().setParent(null);
			}
			else
			{
				BaseNode currentChild=child;
				BaseNode ret=null;
				BaseNode gParent=child.getParent().getParent();
				BaseNode ggParent=gParent.getParent();
				
					
				if(child == child.getParent().getPrevious() && child.getParent()==child.getParent().getParent().getPrevious())
				{
					singleLeftRotation(child.getParent());
					child=singleLeftRotation(currentChild);
				}
				else if(child==child.getParent().getNext() && child.getParent()==child.getParent().getParent().getNext())
				{
					singleRightRotation(child.getParent());
					child=singleRightRotation(currentChild);
				}
				else if(child==child.getParent().getNext() && child.getParent()==child.getParent().getParent().getPrevious())
				{
					ret=singleRightRotation(child);
					gParent.setPrevious(ret);
					child=singleLeftRotation(child);
	
				}
				else
				{
					ret=singleLeftRotation(child);
					gParent.setNext(ret);
					child=singleRightRotation(child);
				}
				
				if(ggParent==null)
				{
					child.setParent(null);
				}
				else if(gParent==ggParent.getNext())
				{
					ggParent.setNext(child);
				}
				else
				{
					ggParent.setPrevious(child);
				}
				if(child.getParent()==null)
				{
					setRoot(child);
					return;
				}
			}
		}

	}
	
	public boolean search(int x)
	{
		find++;
		
		BaseNode current=getRoot();
		
		
		if(getRoot()==null)
		{
			return false;
		}
		
		while(current!=null)
		{
			if(current.getData()==x)
			{
				splay(current);
				return true;
			}
			
			comparison++;
			if(current.getData()>x)
			{
				if(current.getPrevious()==null)
				{
					break;
				}
				else
				{
					current=current.getPrevious();
				}
				
			}
			else
			{
				if(current.getNext() == null)
				{
					break;
				}
				current=current.getNext();
			}
		}
		splay(current);
		return false;
	}

	public void insert(int x)
	{
		add++;
		headChanges++;
		
		if(getRoot()==null)
		{
			setRoot(new Node(x));
			size++;
			return;
		}
		else
		{
			BaseNode temp=getRoot();
			
			while(true)
			{
				comparison++;
				if(temp.getData() > x)
				{
					if(temp.getPrevious() == null)
					{
						temp.setPrevious(new Node(x));
						splay(temp.getPrevious());
						break;
					}
					temp=temp.getPrevious();
				}
				else
				{
					if(temp.getNext() == null)
					{
						temp.setNext(new Node(x));
						splay(temp.getNext());
						break;
					}
					temp=temp.getNext();
				}
			}
		}
		
	}

	public void remove(int x)
	{
		remove++;
		
		
		if(getRoot()==null)
		{
			return;
		}

		
		boolean found=search(x);
		
		find--;
		
		if(found != true)
		{
			return;
		}
		else
		{
			if(getRoot().getPrevious()==null && getRoot().getNext()==null)
			{
				setRoot(null);
				return;
			}
			else if(getRoot().getPrevious() == null && getRoot().getNext() != null)
			{
				headChanges++;
				setRoot(getRoot().getNext());
				getRoot().setParent(null);
			}
			else if(getRoot().getPrevious() != null && getRoot().getNext() == null)
			{
				headChanges++;
				setRoot(getRoot().getPrevious());
				getRoot().setParent(null);
			}
			else if(getRoot().getPrevious()!=null && getRoot().getNext()!=null)
			{
				headChanges++;
				headChanges++;
				BaseNode temp1=getRoot().getNext();
				setRoot(getRoot().getPrevious());
				BaseNode temp2=getRoot();
				temp1.setParent(null);
				temp2.setParent(null);
				
				if(temp2.getPrevious()==null && temp2.getNext()==null)
				{
					temp2.setNext(temp1);
					setRoot(temp2);
					return;
				}
				else if(temp2.getNext()==null)
				{
					temp2.setNext(temp1);
					setRoot(temp2);
					return;
				}
				else
				{
					while(temp2.getNext() != null)
					{
						temp2=temp2.getNext();
					}
					splay(temp2);
					getRoot().setNext(temp1);
				}
			}
		}
						
	}
		
	
	public static void main(String[] args)
	{
		ATree t=new ATree();
		 
		
		Scanner sc=null;
		String s,fl=args[0];
		char action;
		int value;
		
		try
		{
			sc = new Scanner(new FileInputStream(fl));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, program will terminate");
			System.exit(0);
		}
		
		while(sc.hasNextLine())
		{
			s=sc.nextLine();
			
			action=s.charAt(0);
			
			value=Integer.parseInt(s.substring(1, 4));
			
			if(action=='a')
			{
				t.insert(value);
			}
			else if(action=='f')
			{
				t.search(value);
			}
			else if(action=='r')
			{
				t.remove(value);
			}
		}

		System.out.println("Comparison: "+t.getComparison());
		System.out.println("Head changes "+t.getHeadChanges());
		System.out.println("Add: "+t.getAdd());
		System.out.println("Find: "+t.getFind());
		System.out.println("Remove: "+t.getRemove());
	;
	}

}
