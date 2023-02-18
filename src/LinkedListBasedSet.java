
public class LinkedListBasedSet  implements WordSet {
	
	private WordNode head=null;
	private int size;
	
	private WordNode[] letterPositions= new WordNode['z'+1];
	
public void init()
{	
	char ch;
	for (ch = 'a' ; ch <= 'z' ; ch++ ) {
		this.initAdd(ch+"");
	}
}

public void finalize()
{
	char ch;
	for (ch = 'a' ; ch <= 'z' ; ch++ )
		if(letterPositions[ch].freq==1)
			this.remove(ch+"");
		else
			letterPositions[ch].freq-=1;		}


private void initAdd(String newEntry) { 
	System.out.println(newEntry);
	WordNode nd = new WordNode(newEntry,1);
	letterPositions[newEntry.charAt(0)]=nd;
	
	if (head== null) {
		head=nd;
		
	}		
	else {
		
		WordNode walk=head;		
		while(walk!=null){
			 
				if(walk.next!=null) {
					walk=walk.next;}
				
				else {
					walk.next=nd;
					break;}																
		}									
	}	
}


	@Override
	public int size() { 
		int count=0;
		WordNode walk=head;
		
		while(walk!=null) {
			  count++;
			  walk=walk.next;			
		}
		return count;
	}

	@Override
	public boolean isEmpty() { 

		if(head==null) {
			return true;		
				}			
		return false;
	}

	@Override
	public boolean add(String newEntry) {  
		WordNode nd = new WordNode(newEntry,1);//TODO update freq		
			WordNode walk = letterPositions[newEntry.charAt(0)];		
			WordNode beforeWalk = walk;
			
			while(walk!=null){
				
				int comparison = nd.data.compareTo(walk.data); // <0(negative) nd alfabetik olarak önce //=0 nd eþit(ayný string) //>0 nd alfabetik olarak sonra
				
				if(comparison==0)
				{
					walk.freq++;
					break;
				}
				else if(comparison>0 )
				{
					if(walk.next==null)
						walk.next=nd;
					else
						walk=walk.next;
				}
					
				else 
				{
					beforeWalk.next = nd;
					nd.next=walk;
					break;
				}
				
									
			}									
		

		return false;
	}

	@Override
	public boolean remove(String anEntry) { 
		WordNode walk= head;
		WordNode prev= null;
		
		if( anEntry.equals(head.data))
		{
			head = head.next;
			return false;
			
		}
		while(walk!=null) {
			if(anEntry.equals(walk.data)) {
				break;
			}
		prev=walk;	
		walk=walk.next;	
		}
		if(walk!=null) {
			prev.next=walk.next;
		}
			return false;
	}

	@Override
	public void clear() {  
		this.size=0;		
	}

	
	@Override
	public boolean contains(String anEntry) {
		WordNode walk = head;
	
	while(walk!=null) {
		if(anEntry.equals(walk.data)) {
			return true;
		}
		walk=walk.next;
	} 
	
		return false;
	}
	
	

	
	
	@Override
	public String[] toArray() {
		WordNode walk =head;				
		String []items=new String[this.size()];
		
		for(int i=0;i<this.size();i++){
			items[i]= walk.data;
			walk=walk.next;		
		}
		return items;
	}

	
	
	@Override
	public WordSet union(WordSet arg) {
		WordNode walk=head;
		WordSet set4=new LinkedListBasedSet();
		set4=arg;
		while(walk!=null) {
			if(!set4.contains(walk.data)) {
				set4.add(walk.data);
			}
			walk=walk.next;
		}
		
		return set4;
	}

	@Override
	
		public WordSet intersection(WordSet arg) { 
		WordNode walk=head;
		WordSet set3=new LinkedListBasedSet();
			while(walk!=null) {
				if(arg.contains(walk.data)) {
					set3.add(walk.data);
				}
				walk=walk.next;
			}
			return set3;
		}

	@Override
	public WordSet difference(WordSet arg) {		
		WordNode walk =head;
		WordSet set5=new LinkedListBasedSet();
		set5=arg;
		while(walk!=null) {
			if(arg.contains(walk.data)) {
				set5.remove(walk.data);			
			}
			
			walk=walk.next;
		}
		
		return set5;
	}

	 @Override
	 public String toString() {			
		 StringBuilder items = new StringBuilder("");		
		 int count=0;
			WordNode walk=head;

			while(walk!=null) {
				items.append("\n"+count+". "+walk.data +" freq="+ walk.freq);
					count++;
				  walk=walk.next;	
				  
			}
			return items.toString();
	    }
	
}
