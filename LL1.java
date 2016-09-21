/*
 * This code was developed entirely and only by Nicholas Frichette for IT327 
 * Instructed by Dr. Li of Illinois State University. No assistance was 
 * received by any outside parties, nor was any code copied or taken from 
 * any other source. 9/20/2016
 */

import java.util.ArrayList;
import java.util.Stack;

public class LL1 {
	
	public static Stack<String> st = null;
	public static String token = "";
	
	public static void main(String[] args) {
		String line = args[0];
		//Tokenize the String
		ArrayList<String> tokens = tokenize(line);
		
		//Putting tokens into stack
		st = new Stack<String>();
		for (int i=0;i<tokens.size();i++){
			st.push(tokens.get(tokens.size()-1-i));
		}
		
		//Pull first token off and run it
		token = st.pop();
		if (E()){
			System.out.println("Yes");
		}
		else
			System.out.println("No");
	}
	
	private static boolean E(){
		try{
			//If E sees a "(": Rule 1
			if (token.equals("(")){
				System.out.println("Rule 1: E -> TE' " + token);
				T();
				Ep();
			}
			//If E sees a number: Rule 1
			else if (Integer.valueOf(token) < 32767){
				System.out.println("Rule 1: E -> TE' " + token);
				T();
				Ep();
			}
			//If E sees a number that exceeds limit
			else if (Integer.valueOf(token) >= 32767){
				System.out.println("Bad number - greater than limit");
				System.out.println("No");
				System.exit(1);
			}
			else
				return false;
		}
		catch (java.lang.NumberFormatException e){
			System.out.println("Invalid Expression - Bad number " + token);
			System.out.println("No");
			System.exit(1);
			return false;
		}
		return true;
	}
	
	private static boolean Ep(){
		//If Ep sees a "$": Rule 4
		if (token.equals("$")){
			System.out.println("Rule 4: E' -> lambda " + token);
			return true;
		}
		//If Ep sees a "+": Rule 2
		else if (token.equals("+")){
			System.out.println("Rule 2: E' -> +TE' " + token);
			token = st.pop();
			T();
			Ep();
		}
		//If Ep sees a "-"": Rule 3
		else if (token.equals("-")){
			System.out.println("Rule 3: E' -> -TE' " + token);
			token = st.pop();
			T();
			Ep();
		}
		//If Ep sees a ")": Rule 4
		else if (token.equals(")")){
			System.out.println("Rule 4: E' -> lambda " + token);
			token = st.pop();
			return true;
		}
		else
			return false;
		return true;
	}
	
	private static boolean T(){
		try{
			//If T sees a "(": Rule 5
			if (token.equals("(")){
				System.out.println("Rule 5: T -> FT' " + token);
				F();
				Tp();
			}
			//If T sees a number: Rule 5
			else if (Integer.valueOf(token) < 32767){
				System.out.println("Rule 5: T -> FT' " + token);
				F();
				Tp();
			}
			//If E sees a number that exceeds limit
			else if (Integer.valueOf(token) >= 32767){
				System.out.println("Bad number - greater than limit");
				System.out.println("No");
				System.exit(1);
			}
			else 
				return false;
		}
		catch (java.lang.NumberFormatException e){
			System.out.println("Invalid Expression - Bad number " + token);
			System.out.println("No");
			System.exit(1);
			return false;
		}
		return true;
	}
	
	private static boolean Tp(){
		//If Tp sees a "$": Rule 8
		if (token.equals("$")){
			System.out.println("Rule 8: T' -> lambda " + token);
			return true;
		}
		//If Tp sees a "+": Rule 8
		else if (token.equals("+")){
			System.out.println("Rule 8: T' -> lambda " + token);
			return true;
		}
		//If Tp sees a "-": Rule 8
		else if (token.equals("-")){
			System.out.println("Rule 8: T' -> lambda " + token);
			return true;
		}
		//If Tp sees a "*": Rule 6
		else if (token.equals("*")){
			System.out.println("Rule 6: T' -> *FT' " + token);
			token = st.pop();
			F();
			Tp();
		}
		//If Tp sees a "/": Rule 7
		else if (token.equals("/")){
			System.out.println("Rule 7: T' -> /FT' " + token);
			token = st.pop();
			F();
			Tp();
		}
		//If Tp sees a ")": Rule 8
		else if (token.equals(")")){
			System.out.println("Rule 8: T' -> lambda " + token);
			return true;
		}
		else
			return false;
		return true;
	}
	
	private static boolean F(){
		try{	
			//If F sees a "(": Rule 9
			if (token.equals("(")){
				System.out.println("Rule 9: F -> (E) " + token);
				token = st.pop();
				E();
			}
			//If F sees a number: Rule 10
			else if (Integer.valueOf(token) < 32767){
				System.out.println("Rule 10: F -> n " + token);
				token = st.pop();
				return true;
			}
			//If E sees a number that exceeds limit
			else if (Integer.valueOf(token) >= 32767){
				System.out.println("Bad number - greater than limit");
				System.out.println("No");
				System.exit(1);
			}
			else
				return false;
		}
		catch (java.lang.NumberFormatException e){
			System.out.println("Invalid Expression - Bad number " + token);
			System.out.println("No");
			System.exit(1);
			return false;
		}
		return true;
	}
	
	private static ArrayList<String> tokenize(String line){
		ArrayList<String> toReturn = new ArrayList<String>();
		String numberHold = "";
		int par = 0;
		//If line does not have an ending, give it one
		if (!String.valueOf(line.charAt(line.length()-1)).equals("$"))
			line = line + "$";
		while(line.length() > 0){
			if (line.charAt(0) > 47 && line.charAt(0) < 58){
				numberHold += line.charAt(0);
			}
			else{
				if (numberHold.length() > 0)
					toReturn.add(numberHold);
				numberHold = "";
				toReturn.add(String.valueOf(line.charAt(0)));
				if (String.valueOf(line.charAt(0)).equals("("))
					par++;
				if (String.valueOf(line.charAt(0)).equals(")"))
					par--;
			}
			line = line.substring(1);
		}
		if (par != 0){
			System.out.println("Incorrect Parenthesis.");
			System.out.println("No");
			System.exit(1);
		}
		return toReturn;
	}

}
