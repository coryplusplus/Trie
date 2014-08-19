package Trie;

import java.util.HashMap;
import java.util.Vector;

public class Trie2 {

	TrieNode root;
	
	public  Trie2()
	{
		root = new TrieNode(' ');
	}
	
	
	public void insert(String string)
	{
		TrieNode current = root;
		for(int i = 0; i < string.length(); i++)
		{
			if(current.hasChild(string.charAt(i)))
			{
				current = current.getChild(string.charAt(i));
			}
			else
			{
				TrieNode node = new TrieNode(string.charAt(i));
				node.setWord(true);
				current.addChild(node);
			}
		}
	}
	
	public void autocomplete(Vector<String> words, TrieNode current, String currentString)
	{
		if(current == root)
			current = getLongestPrefix(currentString);
		for(TrieNode node: current.getChildren().values())
		{
			currentString = currentString + node.data;
			if(node.hasChildren())
			{
				if(node.isWord)
				{
					words.add(currentString);
				}
				autocomplete(words, node, currentString);
			}
			
			words.add(currentString);
		}
	}
	
	public TrieNode getLongestPrefix(String string)
	{
		TrieNode current = root;
		for(int i = 0; i < string.length(); i++)
		{
			if(current.hasChild(string.charAt(i)))
			{
				current = current.getChild(string.charAt(i));
			}
		}
		
		return current;
	}
	
	class TrieNode
	{
		private Character data;
		private HashMap<Integer, TrieNode> children = 
				new HashMap<Integer, TrieNode>();
		private boolean isWord = false;
		public TrieNode(Character character) {
			data = character;
		}
		public Character getData() {
			return data;
		}
		public void setData(Character data) {
			this.data = data;
		}
		public HashMap<Integer, TrieNode> getChildren() {
			return children;
		}
		public void setChildren(HashMap<Integer, TrieNode> children) {
			this.children = children;
		}
		public boolean isWord() {
			return isWord;
		}
		public void setWord(boolean isWord) {
			this.isWord = isWord;
		}
		
		public boolean hasChild(Character data)
		{
			if(children.containsKey(data.hashCode()))
				return true;
			else
				return false;
		}
		
		public boolean hasChildren()
		{
			if(children.size() > 0)
				return true;
			else 
				return false;
		}
		
		public void addChild(TrieNode node)
		{
			if(children.containsKey(node.data.hashCode()))
				return;
			else
				children.put(node.data.hashCode(), node);
		}
		
		public TrieNode getChild(Character character)
		{
			if(children.containsKey(character.hashCode()))
				return children.get(character.hashCode());
			else
				return null;
		}
	}
	
	public static void main(String [] args)
	{
		Trie trie = new Trie();
		trie.insert("testing");
		trie.insert("testlawl");
		trie.insert("test");
		trie.insert("teksdaksdjaskdjaskdj");
		Vector<String> test = new Vector<String>();
		trie.autocomplete(trie.root, test, "");
		System.out.println(test);
	}
}


