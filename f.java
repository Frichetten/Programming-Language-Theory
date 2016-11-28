public class f {
	public static void main(String[] args) {
		int x = Integer.valueOf(args[0]);
		int y = Integer.valueOf(args[1]);
		System.out.println("f("+args[0]+","+args[1]+")="+f(x,y));
	}
	
	public static int f(int x, int y){
		int a = x+1;
		if (x == 0) return g(y,x,y);
		else return a+g(f(x-1,y),x,y);
	}
	
	public static int g(int n, int x, int y){
		int a = x+y;
		if (n == 0) return 0;
		else return h(n-1, a,x,y);
	}
	
	public static int h(int k, int a, int x, int y){
		if (k == 0) return 0;
		else return a+(k+1)+g(k,x,y);
	}
}
