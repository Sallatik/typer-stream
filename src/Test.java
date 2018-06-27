import sallat.TyperStream;

class Test {

	public static void main(String [] args){	
		TyperStream typer = new TyperStream(System.out)
				.setVariation(100)
				.setCPM(300)
				.setAccuracy(90);
		System.setOut(typer);
		System.out.println(typer.getCPM());
		System.out.append("Hello World!");
		System.out.println();
		System.out.println(false);
		System.out.println(324.543);
		System.out.println(334234L);
		System.out.println(0b0011);
		char[] chars ={ 'a', 'l', 'l', 'o', '!'}; 
		System.out.println(chars);

		System.out.println("GitHub provides desktop clients that include a graphical user\ninterface for the most common repository actions and an automati-\ncally updating command line edition of Git for advanced scenarios.");

	}

}
