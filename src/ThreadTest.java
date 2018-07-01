import sallat.TyperStream;

class ThreadTest{

	public static void main(String... args){
		TyperStream typer = new TyperStream(System.out);
		typer
			.setVariation(100)
			.setCPM(200)
			.setAccuracy(90);
		Thread racer = new Thread(() -> typer.println("Hello world!"));
		racer.start();
		typer.println("Putin is gay");
		try{ racer.join(); } catch(InterruptedException ex) {}
	}
}
