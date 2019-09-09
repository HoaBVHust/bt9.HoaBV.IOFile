package vn.topica.itlab.iofile;

import java.util.ArrayList;

public class ProcessString {
	//change a string that have a lot of words to a word array 
	public static String[] stringToWords(String string) {
		//replace a lot of connected space to a space
		while(true)
		{
			if (string.indexOf("  ") != -1) {
				string=string.replace("  "," ");
			}
			else {
				break;
			}
		}
		string = string.toLowerCase().trim();
		//change to word array using split method
		String[] words = string.split("\\s");
		return words;
	}
	// upper the first char of the word
	public static String toUpperFirstChar(String string) {
		if(string.length()>1){
			String firstChar=string.substring(0,1).toUpperCase();
			string=firstChar+string.substring(1);
		}
		else {
			string=string.toUpperCase();
		}
		return string;
	}
	//find the words that appear most in the list word
	public static ArrayList<String> findWordAppearMost(ArrayList<String> words) {
		//listWord save a set list of the word that isn't same
		ArrayList<String> listWord = new ArrayList<String>();
		//frequencyOfWord save the frequency appear of the word
		ArrayList<Integer> frequencyOfWord = new ArrayList<Integer>();
		for (String word : words) {
			if(!listWord.contains(word)) {
				listWord.add(word);
				frequencyOfWord.add(1);
			}
			else {
				int index = listWord.indexOf(word);
				frequencyOfWord.set(index, frequencyOfWord.get(index)+1);
			}	
		}
		//find the max in the frequencyOfWord list
		int max = 0;
		//listIndexOfMax will save all index of the max frequency
		ArrayList<Integer> listIndexOfMax = new ArrayList<Integer>();
		for (int i=0; i< frequencyOfWord.size(); i++) {
			if(frequencyOfWord.get(i)>max) {
				listIndexOfMax.clear();
				max=frequencyOfWord.get(i);
				listIndexOfMax.add(i);
			}	
			else if(frequencyOfWord.get(i)==max) {
				listIndexOfMax.add(i);
			}
		}
		words.clear();
		for (Integer integer : listIndexOfMax) {
			words.add(listWord.get(integer));
		}
		return words;
		
	}
}
