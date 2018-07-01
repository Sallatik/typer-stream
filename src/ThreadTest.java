import sallat.TyperStream;

class ThreadTest{

	public static void main(String... args){
		TyperStream typer = new TyperStream(System.out);
		typer
			.setVariation(100)
			.setCPM(200)
			.setAccuracy(90);
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		Thread racer = new Thread(() -> typer.println("Hello world! I love you!"));
		racer.start();
		try{ Thread.sleep(10); } catch(InterruptedException ex) {}
		typer.println();
		typer.println("Putin is gay");
		try{ racer.join(); } catch(InterruptedException ex) {}
	}
}
