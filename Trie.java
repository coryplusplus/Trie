package Trie;

import java.util.Vector;

public class Trie {
	
	TrieNode root;
	
	public Trie()
	{
		root = new TrieNode();
	}
	
	public void insert(String string)
	{
		TrieNode current = root;
		
		for(int i = 0; i < string.length(); i++)
		{
			if(current.hasChild(string.charAt(i)))
				current = current.getChild(string.charAt(i));
			else
			{
				TrieNode node = new TrieNode(string.charAt(i));
				current.addChild(node);
				current = current.getChild(string.charAt(i));
			}
		}
		current.setIsWord(true);
		
	}
	
	public TrieNode search(String string)
	{
		TrieNode current = root;
		for(int i = 0; i < string.length(); i++)
		{
			if(current.hasChild(string.charAt(i)))
				current = current.getChild(string.charAt(i));
			
		}
		if(!current.getIsWord())
			return null;
		return current;
	}
	
	public TrieNode getLongestPrefix(String string)
	{
		TrieNode current = root;
		for(int i = 0; i < string.length(); i++)
		{
			if(current.hasChild(string.charAt(i)))
				current = current.getChild(string.charAt(i));
			else
				return null;
			
		}
		
		return current;
	}
	
	
	public void autocomplete(TrieNode current, Vector<String> words, String completeWord)
	{
		if(current == root)
		{
			current = getLongestPrefix(completeWord);
		}
		if(current != null && current.getChildren() != null)
		{
		    for(TrieNode node: current.getChildren().values())
		    {
			    String tempWord = completeWord + node.getData();
			    if(!node.hasChildren())
			    {
			 	    words.add(tempWord);
			    }
			    else
			    {
				    if(node.getIsWord())
					    words.add(tempWord);
				    autocomplete(node, words, tempWord);
			    }
		    }
		}
		
	}
	
	private TrieNode matchFirstNode(String string)
	{
		if(root.hasChild(string.charAt(0)))
		{
			return root.getChild(string.charAt(0));
		}
		else
			return null;
	}

	private void remove(String string)
	{
		TrieNode current = matchFirstNode(string);
		for(int i =1; i < string.length();i++)
		{
			if(!current.hasChild(string.charAt(i)))
			{
				return;
			}
			else
			{
				TrieNode temp = current.getChild(string.charAt(i));
				if(!(temp.getChildren().size() > 1))
				    current.removeChild(string.charAt(i));
				current = temp;
				
			}
		}
	}
	
	
	public static void main(String [] args)
	{
		Trie trie = new Trie();
		trie.insert("TESTING");
		trie.insert("TEST");
		trie.insert("TEAM");
		Vector<String> results = new Vector<String>();
		trie.autocomplete(trie.root,results,"TE");
		System.out.println(results);
		trie.remove("TEAM");
		results.clear();
		trie.autocomplete(trie.root,results,"TE");
		System.out.println(results);
	}

}
