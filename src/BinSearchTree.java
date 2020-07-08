/**
 * 
 * @author Connor Haines
 * 
 * This class contains multiple methods used to search through specified binary  trees.
 *
 */
public class BinSearchTree {

	/**
	 * "root" is the root of each binary tree.
	 */
	private BinSearchTreeNode root;
	
	/**
	 * This getWord class is used to find the node from the binary tree that contains the given word.
	 * @param searchWord is the string passed to the method that is to be searched for throughout the tree.
	 * @return returns the node with the specified word, or null if it doesn't exist.
	 */
	public BinSearchTreeNode getWord(String searchWord) {

		BinSearchTreeNode result = this.root;

		if (result != null) {
			//right
			if (searchWord.compareTo(this.root.getWord()) > 0) {
				BinSearchTreeNode tempNode = root;
				
				this.root = this.root.getRight();
				result = getWord(searchWord);
				this.root = tempNode;
			}	
			//left
			if (searchWord.compareTo(this.root.getWord()) < 0) {
				BinSearchTreeNode tempNode = root;
				
				this.root = this.root.getLeft();
				result = getWord(searchWord);
				this.root = tempNode;
			}
		}
		return result;
	}

	/**
	 * This method looks for theWord and if it is not found, it it inserted into the tree.
	 * 
	 * @param theWord is the given string to be added to the tree.
	 * @param theFileName is the file name string associated with theWord.
	 * @param thePosition is the position integer associated with theWord.
	 */
	public void insertWord(String theWord, String theFileName, int thePosition) {

		if (this.root == null) {
			root = new BinSearchTreeNode(theWord, theFileName, thePosition);
			return;
		}
		BinSearchTreeNode left = this.root.getLeft();
		BinSearchTreeNode right = this.root.getRight();
		
	//Adding new nodes to the left or right.
		if (this.root.getWord().compareTo(theWord) > 0) {
			
			if (left == null) {
				BinSearchTreeNode newNode = new BinSearchTreeNode(theWord, theFileName, thePosition);
				this.root.setLeft(newNode);
			} 
			else {
				BinSearchTreeNode tempNode = this.root;
				this.root = left;
				insertWord(theWord, theFileName, thePosition);
				this.root = tempNode;
			}
		}
		
		else if (this.root.getWord().compareTo(theWord) < 0 ) {

			if (right == null) { 
				BinSearchTreeNode newNode = new BinSearchTreeNode(theWord, theFileName, thePosition);
				this.root.setRight(newNode);
			} 
			else {
				BinSearchTreeNode tempNode = this.root;
				this.root = right;
				insertWord(theWord, theFileName, thePosition);
				this.root = tempNode;
			}
			
		}
		else {
			this.root.getFiles().insertWord(theFileName, thePosition);
		}
	}
}
