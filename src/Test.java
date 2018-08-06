import sallat.*;

class Test{
	public static void main(String... args){
		TypeStream typer = new TypeStream(System.out);
		typer.setDelay(200, 50)
			.setAccuracy(50)
			.setMisstypeSupplier((r,c) -> 'A');
		typer.println(typer.getMinDelay() + " " + typer.getMaxDelay());
		typer.println(typer.getCPM());
		typer.println("sgfadg dfg dsfgdfsg fdggeragsfdgfdsgfdhsadf");
		Thread typerThread = new Thread(() -> typer.println("AFdsfa sfasdf sfa asfsad fsd afads fsdf; lkadsfj sdf ksafsdaflkadsfsadflkdsfasd fdsa vs daflkds fjsdflsdka fdsafsdajfasfjsda;fldsjf;ldskfjds;aflkjds;aflkdsjafsdfasdf ads f sa dsf afs sdf asdf "));
			typerThread.start();
		try{
			Thread.sleep(1000);
		} catch(Exception ex) {}
		typerThread.interrupt();
	}
}
