import sallat.*;
import java.io.*;

class WriterTest{
	public static void main(String [] args) throws Exception{
		Writer myWriter = (Writer) new TypeWriter(new OutputStreamWriter(System.out))
			.setCPM(200, 50)
			.setAccuracy(70);
		myWriter.write("Hello World!");
	}
}
