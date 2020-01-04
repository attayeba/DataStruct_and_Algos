/*
Comp 352-Assignment 3
*/

import java.util.Scanner;

public class Huffman extends BasicTree
{
	public class Leaf implements BaseNode
	{
		private int character;
		private int data;
		private int order;
		
		public Leaf()
		{
			character=0;
			data=0;
			order=0;
		}
		
		public void setCharacter(int character)
		{
			this.character=character;
		}
		
		public void setData(int data)
		{
			this.data=data;
		}
		
		public void setOrder(int order)
		{
			this.order=order;
		}
		
		public void setPrevious(BaseNode previous)
		{
			previous=null;
		}
		
		public void setNext(BaseNode next)
		{
			next=null;
		}
		
		public void setParent(BaseNode parent)
		{
			parent=null;
		}
		
		public int getCharacter()
		{
			return character;
		}
		
		public int getData()
		{
			return data;
		}
		
		public int getOrder()
		{
			return order;
		}
		
		public BaseNode getPrevious()
		{
			return null;
		}
		
		public BaseNode getNext()
		{
			return null;
		}
		
		public BaseNode getParent()
		{
			return null;
		}
		
		public boolean isLeaf()
		{
			return true;
		}

	}

	
	private static BaseNode buildTrees(Leaf[] LArray)
	{
		BaseNode[] internal=new BaseNode[128];
		BasicTree obj=new BasicTree();
		int elementsCtr,nbLeafs=0;
		int sum;
		BaseNode temp1=null,temp2=null;
		
		for(int i=0;i<internal.length;i++)
		{
			internal[i]=LArray[i];
			if(LArray[i].getData() > 0)
			{
				nbLeafs++;
			}
		}
	
		elementsCtr=nbLeafs;
				
		while(elementsCtr>1)
		{

			temp1=internal[elementsCtr-1];
			temp2=internal[elementsCtr-2];

			sum=temp1.getData()+temp2.getData();
				
			internal[elementsCtr-2]=obj.new Node(sum);
			internal[elementsCtr-2].setPrevious(temp1);
			internal[elementsCtr-2].setNext(temp2);
			
			QuickSort.sort(internal);
			
			
			elementsCtr--;
			
		}
		
		return internal[0];
	}
	
	private static String[] buildHuffmanCode(String[] st, BaseNode traversal, String s)
	{
		if(!traversal.isLeaf())
		{
			buildHuffmanCode(st,traversal.getPrevious(),s+'0');
			buildHuffmanCode(st,traversal.getNext(),s+'1');
			
		}
		else
		{
			st[traversal.getCharacter()]=s;
		}
		
		return st;
	}
	
	private static void displayHuffmanCode(String[] st, BaseNode traversal,Leaf[] LArray)
	{
		if(!traversal.isLeaf())
		{
			displayHuffmanCode(st,traversal.getPrevious(),LArray);
			displayHuffmanCode(st,traversal.getNext(),LArray);
			
		}
		else
		{
			char c=(char)traversal.getCharacter();
			System.out.printf("%-3c           %-3d           %-10s%n",c,traversal.getData(),st[traversal.getCharacter()]);
		}
	}
	
	private static void encode(String s, String[] st)
	{
		String s1,s2="";
		int ch;
		char[] cArray=s.toCharArray();

		for(int i=0;i<cArray.length;i++)
		{
			ch=(int)cArray[i];
			s1=st[ch];
			s2=s2+s1;
		}
		
		System.out.print(s2+"\n");
	}
	
	public static void main(String[] args)
	{
		Scanner kb=new Scanner(System.in);
			
		String[] BitCode=new String[128];
		String fl=args[0];
		Leaf[] lf=Hash.buildHash(fl);
		BaseNode nd=buildTrees(lf);
	
		String sentence;

		BitCode=buildHuffmanCode(BitCode,nd,"");
		System.out.println("Charachter    Frequency    HuffmanCode");
		displayHuffmanCode(BitCode,nd,lf);
		
		
		
		System.out.println("\nPlease enter a sentence you would like to encode. Enter '0' when you would like to stop");
		while(true)
		{
			sentence=kb.nextLine();
			sentence=sentence.toLowerCase();
			
			if(sentence.equals("0"))
			{
				break;
			}
			
			System.out.print("encode: ");
			encode(sentence,BitCode);
		}
		
		kb.close();


		
		
	}

}