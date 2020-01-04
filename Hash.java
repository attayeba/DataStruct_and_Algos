/*
Comp 352-Assignment 3
*/

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class Hash {

	private static Huffman.Leaf[] leafs;
	private static int orderCtr;
	private static String file;
			
	private static char[] reader(String fileName)
	{
		Scanner sc=null;
		file=fileName;
		String s;
		
		try
		{
			sc = new Scanner(new FileInputStream(file));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found, program will terminate");
			System.exit(0);
		}
		
		s= sc.nextLine();
		char[] cArray=s.toCharArray();
		return cArray;
		
	}
	
	private static int hashFuntion(char txt)
	{
		char chr=txt;
		int hashVal=(int)chr;
		return hashVal;
	}
	
	private static void hashTable(String fileName)
	{
		char[] arr=reader(fileName);
		orderCtr=0;
		
		for(int i=0;i<arr.length;i++)
		{
			int value = hashFuntion(arr[i]);
			
			if(leafs[value].getData()==0)
			{
				leafs[value].setOrder(orderCtr);
				orderCtr++;
			}
			leafs[value].setData(leafs[value].getData()+1);
		}

	}
	
	public static Huffman.Leaf[] buildHash(String fileName)
	{
		int j=0;
		leafs=new Huffman.Leaf[128];
		Huffman obj=new Huffman();
		
		for(int i=0;i<leafs.length;i++)
		{
			leafs[i]=obj.new Leaf();
			leafs[i].setCharacter(i);
		}
		
		hashTable(fileName);
		QuickSort.sort(leafs);
		
		for(int i=0;i<leafs.length;i++)
		{
			j=j+leafs[i].getData();
		}

		return leafs;
	}

}