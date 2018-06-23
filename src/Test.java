import sallat.Typer;

class Test {

	public static void main(String [] args){	
		System.setOut(new Typer(System.out)
				.setDelay(200));

		System.out.append("Hello World!");
		System.out.println();
		System.out.println(false);
		System.out.println(324.543);
		System.out.println(334234L);
		System.out.println(0b0011);
		char[] chars ={ 'a', 'l', 'l', 'o', '!'}; 
		System.out.println(chars);
	}

}
