import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * 
 * @author Connor Haines
 * 
 * This class contains various methods to search through text files and count specified words.
 * It extends the HashTable class and often uses methods from BinSearchTree.
 *
 */
public class Searcher extends HashTable {

	private HashTable table;
	private String inputFile;
	
	/**
	 * This is the constructor of the Searcher class.
	 * @param table is a HashTable of size 1030
	 * @param inputFile is the name of the file given by the user.
	 */
	public Searcher(HashTable table, String inputFile) {
		this.inputFile = inputFile;
		this.table = table;
	}
	
	/**
	 * the findAllWords method reads through the file given by the user and for each word it
	 * runs the below findWord method.
	 */
	public void findAllWords(){
		
		//Try-catch block used to handle exceptions.
		try {
			String singleWord;
			String line;
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			
			while((line = input.readLine()) != null) {
				String[] lineWords = line.split(" ");
				
				for (int i = 0; i < lineWords.length; i++) {
					singleWord = lineWords[i];
					if(!singleWord.equals("")) {
						this.findWord(singleWord);
					}
				}
			}
			input.close();
		}
		catch(FileNotFoundException e){
			System.out.println("The file '"+ this.inputFile +"' was not found.");
		}
		catch(IOException e) {
			System.out.println(e.getMessage()+" was not found.");
		}
		
	}
	
	/**
	 * the findWord method is used by the above findAllWords method to find 'searchWord' in the lexicon and uses
	 * the 'customPrinter' function to add the word to a text file.
	 * @param searchWord is a single word string passed to the method to be found in the lexicon.
	 */
	public void findWord(String searchWord) {
		int j;
		j = computeIndex(searchWord);
		
		BinSearchTree tempTree = table.getTable()[j];
		if (tempTree.getWord(searchWord) == null) {
			CustomPrinter.wordNotFound(searchWord, inputFile);
		}
		else {
			CustomPrinter.wordFound(searchWord, inputFile);
			LinkedList tempList = tempTree.getWord(searchWord).getFiles();
			
			//Traversing Linked List
			FileNode current = tempList.getHead();
			ArrayList<Integer> tempArray = current.getPositions();
			
			while(current != null) {
				tempArray = current.getPositions();
				CustomPrinter.printPositionsPerFileFound(current.getFilename(), tempArray, inputFile);
				
				current = current.getNext();
			}
		}
	
	}
	
}
