import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class LR1 {

	static ArrayList<String> states;
	static ArrayList<String> symbols;
	static ArrayList<String> hold;
	static StringTokenizer tokener;
	static String token;
	
	public static void main(String[] args) {
		//Getting Command Line Arguments
		String line = args[0];
		System.out.println("Input: " + line);
		
		//Tokenizing
		tokener = new StringTokenizer(line,"(+*)$",true);
		
		//Initializing
		states = new ArrayList<String>();
		symbols = new ArrayList<String>();
		hold = new ArrayList<String>();
		
		//Push State 0 into states, and off we go
		states.add("0");
		token = tokener.nextToken();
		state0();
		
	}
	
	public static String lookup(String item, String letter){
		if (item.equals("0")){
			if (letter.equals("E"))
				return "1";
			else if (letter.equals("T"))
				return "2";
			else if (letter.equals("F"))
				return "3";
			else
				return "";
		}
		else if (item.equals("4")){
			if (letter.equals("E"))
				return "8";
			else if (letter.equals("T"))
				return "2";
			else if (letter.equals("F"))
				return "3";
			else return "";
		}
		else if (item.equals("6")){
			if (letter.equals("T"))
				return "9";
			else if (letter.equals("F"))
				return "3";
			else 
				return "";
		}
		else if (item.equals("7")){
			if (letter.equals("F"))
				return "10";
			else
				return "";
		}
		else
			return "";
	}
	
	public static void goTo(String state){
		if (state.equals("0"))
			state0();
		else if (state.equals("1"))
			state1();
		else if (state.equals("2"))
			state2();
		else if (state.equals("3"))
			state3();
		else if (state.equals("4"))
			state4();
		else if (state.equals("5"))
			state5();
		else if (state.equals("6"))
			state6();
		else if (state.equals("7"))
			state7();
		else if (state.equals("8"))
			state8();
		else if (state.equals("9"))
			state9();
		else if (state.equals("10"))
			state10();
		else if (state.equals("11"))
			state11();
	}
	
	public static void state0(){
		//id -- shift5
		if (isNum(token)){
			hold.add(token);
			//Push token into symbols
			symbols.add(token);
			//Push 5 into states
			states.add("5");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state5();
		}
		//( -- shift 4
		else if (token.equals("(")){
			//Push token into symbols
			symbols.add(token);
			//Push 4 into states
			states.add("4");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state4();
		}
		else {
			System.out.println("FATAL ERROR IN STATE 0 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state1(){
		//+ -- shift 6
		if (token.equalsIgnoreCase("+")){
			//Push token into symbols
			symbols.add(token);
			//Push 6 into states
			states.add("6");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state6();
		}
		//$ -- accept
		else if (token.equals("$")){
			System.out.println("ACCEPT");
			System.out.println("\t Value => " + hold.get(0));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 1- TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state2(){
		//+ -- E -> T
		if (token.equals("+")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("E");
			//Need to grab a new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"E"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//* -- shift 7
		else if (token.equals("*")){
			//Push token into symbols
			symbols.add(token);
			//Push 7 into states
			states.add("7");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state7();
		}
		//) -- E -> T
		else if (token.equals(")")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("E");
			//Need to grab a new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"E"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//$ -- E -> T
		else if (token.equals("$")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("E");
			//Need to grab a new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"E"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 2- TERMINATING");
			System.exit(1);
		}
		
	}
	
	public static void state3(){
		//+ -- T -> F
		if (token.equalsIgnoreCase("+")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push T into symbols
			symbols.add("T");
			//Need to grab a new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//* -- T -> F
		else if (token.equals("*")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push T into symbols
			symbols.add("T");
			//Need to grab a new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//) -- T -> F
		else if (token.equals(")")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push T into symbols
			symbols.add("T");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//$ -- T -> F
		else if (token.equals("$")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push T into symbols
			symbols.add("T");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 3 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state4(){
		//id -- shift 5
		if (isNum(token)){
			//Push token into symbols
			symbols.add(token);
			//Push 5 into states
			states.add("5");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state5();
		}
		//( -- shift 4
		else if (token.equals("(")){
			//Push token into symbols
			symbols.add(token);
			//Push 4 into states
			states.add("4");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state4();
		}
		else {
			System.out.println("FATAL ERROR IN STATE 4 - TERMINATING");
			System.exit(1);
		}
		
	}
	
	public static void state5(){
		//+ -- F -> id
		if (token.equals("+")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push F into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//* -- F -> id
		else if (token.equals("*")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push F into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//) -- F -> id
		else if (token.equals(")")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push F into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//$ -- F -> id
		else if (token.equals("$")){
			//Pop 1 from each stack
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			//Push F into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 5 - TERMINATING " + token);
			System.exit(1);
		}
	}
	
	public static void state6(){
		//id -- shift 5
		if (isNum(token)){
			hold.add(token);
			//Push token into symbols
			symbols.add(token);
			//Push 5 into states
			states.add("5");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state5();
		}
		//( -- shift 4
		else if (token.equals("(")){
			//Push token into symbols
			symbols.add(token);
			//Push 4 into states
			states.add("4");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state4();
		}
		else {
			System.out.println("FATAL ERROR IN STATE 6 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state7(){
		//id -- shift 5
		if (isNum(token)){
			//Push token into symbols
			symbols.add(token);
			//Push 5 into states
			states.add("5");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state5();
		}
		//( -- shift 4
		else if (token.equals("(")){
			//Push token into symbols
			symbols.add(token);
			//Push 4 into states
			states.add("4");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state4();
		}
		else {
			System.out.println("FATAL ERROR IN STATE 7 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state8(){
		//+ -- shift 6
		if (token.equals("+")){
			//Push token into symbols
			symbols.add(token);
			//Push 6 into states
			states.add("6");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state6();
		}
		//) -- shift 11
		else if (token.equals(")")){
			//Push token into symbols
			symbols.add(token);
			//Push 11 into states
			states.add("11");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state11();
		}
		else {
			System.out.println("FATAL ERROR IN STATE 8 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state9(){
		//+ -- E -> E + T
		if (token.equals("+")){
			int a = Integer.valueOf(hold.remove(hold.size()-1));
			int b = Integer.valueOf(hold.remove(hold.size()-1));
			int c = a + b;
			hold.add(String.valueOf(c));
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("E");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"E"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//* -- shift 7
		else if (token.equals("*")){
			//Push token into symbols
			symbols.add(token);
			//Push 7 into states
			states.add("7");
			//Grab new token
			token = tokener.nextToken();
			System.out.println(states.toString() + " " + symbols.toString());
			state7();
		}
		//) -- E -> E + T
		else if (token.equals(")")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("E");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"E"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//$ -- E -> E + T
		else if (token.equals("$")){
			int a = Integer.valueOf(hold.remove(hold.size()-1));
			int b = Integer.valueOf(hold.remove(hold.size()-1));
			int c = a + b;
			hold.add(String.valueOf(c));
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("E");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"E"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 9 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state10(){
		//+ -- T -> T * F
		if (token.equals("+")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("T");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//* -- T -> T * F
		else if (token.equalsIgnoreCase("*")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("T");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//) -- T -> T * F
		else if (token.equals(")")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("T");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//$ -- T -> T * F
		else if (token.equals("$")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("T");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"T"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 10 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static void state11(){
		//+ -- F -> ( E )
		if (token.equals("+")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//* -- F -> ( E )
		else if (token.equals("*")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//) -- F -> ( E )
		else if (token.equals(")")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		//$ -- F -> ( E )
		else if (token.equalsIgnoreCase("$")){
			//Pop 3 From Each Stack
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			symbols.remove(symbols.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			states.remove(states.size()-1);
			//Push E into symbols
			symbols.add("F");
			//Need to grab new state based on the previous one
			states.add(lookup(states.get(states.size()-1),"F"));
			System.out.println(states.toString() + " " + symbols.toString());
			goTo(states.get(states.size()-1));
		}
		else {
			System.out.println("FATAL ERROR IN STATE 11 - TERMINATING");
			System.exit(1);
		}
	}
	
	public static boolean isNum(String item){
		if(item.equals("+") || item.equals("*") || item.equals("(") || item.equals(")") || item.equals("$") || item.equals("E") || item.equals("T") || item.equals("F")){
			return false;
		}
		else
			return true;
	}
		
	public static class Token {
		boolean isState = false;
		boolean isNum;
		String item = "";
		
		public Token(String item, boolean isState){
			this.item = item;
			this.isState = isState;
			if(!item.equals("+") || !item.equals("*") || !item.equals("(") || !item.equals(")") || !item.equals("$")){
				this.isNum = true;
			}
			else
				this.isNum = false;
		}
		
		public boolean isState(){
			return isState;
		}
		
		public String getItem(){
			return this.item;
		}
		
	}
	
	public static String reverse(String line){
		String enil = "";
		//Reversing
		for(int i=0; i<line.length(); i++){
			enil = enil + line.charAt(line.length()-i-1);
		}
		return enil;
	}
	
}
