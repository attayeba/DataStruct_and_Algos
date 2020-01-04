/*
Comp 352-Assignment 3
*/

public class QuickSort
{
	private static void swap(BaseNode[] array, int a, int b)
	{
		BaseNode temp;
		temp=array[a];
		array[a]=array[b];
		array[b]=temp;
	}
	
	private static int mOfThree(BaseNode[] array,int start, int end)
	{
		int middle=(start+end)/2;
		
		if(array[end].getData() > array[start].getData())
			swap(array,end,start);
		
		if(array[middle].getData() > array[start].getData())
			swap(array,middle,start);
		
		if(array[end].getData() > array[middle].getData())
			swap(array,middle,end);
		
		return middle;
	}
	
	private static void partition(BaseNode[]  array, int start, int end)
	{
		int p=mOfThree(array,start,end);
	
		int pivot=array[p].getData();
		int i=start;
		int j=end;
		
		while(start <= end)
		{
			while(array[start].getData() > pivot)
				start++;
			
			while(array[end].getData() < pivot)
				end--;
			
			if(start <= end)
			{
				if(array[start].getData()==array[end].getData())
				{
					if(array[start].getOrder()<array[end].getOrder())
					{
						swap(array,start,end);
						
						start++;
						end--;
					}
					else
					{
						swap(array,start,end);
						swap(array,start,end);
						
						start++;
						end--;
					}
				}
				else
				{
					swap(array,start,end);
					
					start++;
					end--;
				}
				
		
			}
		}

		if(i<end)
			partition(array,i,end);
		
		if(j>start)
			partition(array,start,j);
	}
	
	public static void sort(BaseNode[] array)
	{
		
		if(array.length <= 1)
			return;
		
		partition(array,0,array.length-1);
		
	}
	
}