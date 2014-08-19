package Trie;

import java.util.HashMap;

public class TrieNode {
	
	private boolean isWord = false; //denotes the end of a word
	private Character data;
	private HashMap<Integer, TrieNode> children = new HashMap<Integer, TrieNode>();
	
	public TrieNode()
	{
		data = new Character(' ');
	}
	
	public TrieNode(Character c)
	{
		data = c;
	}
	
	public Character getData()
	{
		return data;
	}
	public boolean getIsWord()
	{
		return isWord;
	}
	
	public void setIsWord(boolean isWord)
	{
		this.isWord = isWord;
	}
	
	public boolean hasChildren()
	{
		return(children.size() > 0);
	}
	
	public boolean hasChild(Character c)
	{
		if(children.containsKey(c.hashCode()))
			return true;
		else
			return false;
	}
	
	public TrieNode getChild(Character c)
	{
		return children.get(c.hashCode());
	}
	
	public HashMap<Integer, TrieNode> getChildren()
	{
		return children;
	}
	
	public void removeChild(Character c)
	{
		if(children.containsKey(c.hashCode()))
		{
			children.remove(c.hashCode());
		}
		else
		{
			System.out.println("This child does not exists: Cannot Remove");
		}
	}
	public boolean addChild(TrieNode node)
	{
		if(children.containsKey(node.data.hashCode()))
			return false;
		else
		{
			children.put(node.data.hashCode(), node);
			return true;
		}
	}

}
