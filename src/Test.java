import sallat.TyperStream;

class Test{
	public static void main(String... args){
		TyperStream typer = new TyperStream(System.out);
		typer.setDelay(100, 100)
			.setAccuracy(50);
//		typer.println(typer.getMinDelay() + " " + typer.getMaxDelay());
//		typer.setCPM(600, 30);
		typer.println(typer.getMinDelay() + " " + typer.getMaxDelay());
		typer.println(typer.getCPM());
		typer.println("sgfadg dfg dsfgdfsg fdggeragsfdgfdsgfdhsadf");
	}
}
