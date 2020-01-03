class bst
{
    
    Node root;

    private class Node
    {
    	
    	// These attributes of the Node class will not be sufficient for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        private Node(String k)
        {
        	//Instantialize a new Node with keyword k.
        	keyword=k;
        }

        private void update(Record r)
        {
        	//Adds the Record r to the linked list of records. You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list.
        	if(record==null)
        	{
        		record = r;
        	}
        	else
        	{
        		r.next=record;
        		record=r;
        	}
        }

       
    }

    //declares root as null
    public bst()
    {
        this.root = null;
    }
      
    public void insert(String keyword, FileData fd)
    {
    	//Write a recursive insertion that adds recordToAdd to the list of records for the node associated
        //with keyword. If there is no node, this code should add the node.
        Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
        
        String newkey=keyword.trim();   
        insert(newkey, recordToAdd, root);
    }
    
    //private function for insert, traverses through binary tree inserting a node where fit appropriate 
    private void insert(String keyword, Record recordToAdd, Node root)
    {
    	//if there is no root, add a new node at the root 
    	if(root==null)
        {
        	Node newNode = new Node(keyword);
        	newNode.update(recordToAdd);
        	root=newNode;
        }
    	//if the argument is greater than the node's keyword, check if there's a node at the right child. If not, add a new node.
    	//if there is a node, add the node elsewhere. 
        else if(keyword.compareTo(root.keyword) > 0)
        {
        	if(root.r==null)
        	{
        		Node newNode = new Node(keyword);
        		newNode.update(recordToAdd);
        		root.r=newNode;
        	}
        	else
        	{
        		insert(keyword, recordToAdd, root.r);
        	}
        }
    	//performes the same ideology as the right node except with the left node
        else if(keyword.compareTo(root.keyword) < 0)
        {
        	if(root.l==null)
        	{
        		Node newNode = new Node(keyword);
        		newNode.update(recordToAdd);
        		root.l=newNode;
        	}
        	else
        	{
        		insert(keyword, recordToAdd, root.l);
        	}
        }
    	//all else, add a new record object to the root
        else
        {
        	root.update(recordToAdd);
        }
    }
    
    public boolean contains(String keyword)
    {
    	//Write a recursive function which returns true if a particular keyword exists in the bst
    	return contains(keyword, root);
    }
    
    //private function for contains that checks if the root contains a certain keyword
    private boolean contains(String keyword, Node root)
    {
    	//checks root, return false. checks right node and returns if it's in the right node. 
    	//checks left node and returns if it's in the left node. else, returns true. 
    	if(root==null)
    		return false;
    	if(keyword.compareTo(root.keyword) > 0)
    	{
    		return contains(keyword, root.r);
    	}
    	else if(keyword.compareTo(root.keyword) < 0)
    	{
    		return contains(keyword, root.l);
    	}
    	else
    	{
    		return true;
    	}
    }

    public Record get_records(String keyword)
    {
        //Returns the first record for a particular keyword. This record will link to other records
    	//If the keyword is not in the bst, it should return null.
    	
    	Node temp = root;
    	while(temp!=null)
    	{
    		if(keyword.compareTo(temp.keyword) > 0)
    		{
    			temp = temp.r;
    		}
    		else if(keyword.compareTo(temp.keyword) < 0)
    		{
    			temp = temp.l;
    		}
    		else
    			return temp.record;
    	}
    	return null;
    }

    public void delete(String keyword)
    {
    	//Write a recursive function which removes the Node with keyword from the binary search tree.
    	//You may not use lazy deletion and if the keyword is not in the bst, the function should do nothing.
    	delete(keyword, root);
    	
    }
    
    //traverses through binary tree. Deletes right or left node if necessary.
    //if the node is nonexistent, returns nothing. 
    private Node delete(String keyword, Node temp)
    {
    	if(keyword.compareTo(temp.keyword) > 0)
    		temp.r=delete(keyword,temp.r);
    	else if(keyword.compareTo(temp.keyword) < 0)
    		temp.l=delete(keyword,temp.l);
    	else if(temp==null)
    	{
    		
    	}
    	else
    	{
    		if(temp.r!=null)
    		{
    			Node smallr=findSmallest(temp.r);
    			  			
    			temp.keyword=smallr.keyword;
    			temp.record=smallr.record;
    			temp.size=smallr.size;
    			
    			temp.r=delete(smallr.keyword, temp.r);
    			
    		}
    		else
    			temp=temp.l;
    	}
    	return temp;
    }
   
    //finds the smallest node
    private Node findSmallest(Node root)
    {
    	if(root==null)
    		return null;
    	else if(root.l==null)
    		return root;
    	
    	return findSmallest(root.l);
    }

    public void print()
    {
        print(root);
    }

    private void print(Node t)
    {
        if (t!=null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
                System.out.printf("\t%s\n",r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
    

}
