import sallat.Typer;

class Test {

	public static void main(String [] args){	
		System.setOut(new Typer(System.out)
				.setDelay(200));

		System.out.print("Hello World!");
	}

}
