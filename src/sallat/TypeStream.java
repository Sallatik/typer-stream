package sallat;


import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class TypeStream extends PrintStream implements Typer{
	
	private TypeWriter textOut;

	public Typer setDelayRange(int minDelay, int maxDelay) throws IllegalArgumentException{
		textOut.setDelayRange(minDelay, maxDelay);
		return this;		
	}

	public Typer setDelay(int delay, int variation) throws IllegalArgumentException{
		textOut.setDelay(delay, variation);
		return this;
	}

	public Typer setCPM(double cpm, int variation) throws IllegalArgumentException{
		textOut.setCPM(cpm, variation);
		return this;
	}

	public Typer setAccuracy(int accuracy) throws IllegalArgumentException{
		textOut.setAccuracy(accuracy);
		return this;
	}

	public Typer setMisstypeSupplier(MisstypeSupplier supplier){
		textOut.setMisstypeSupplier(supplier);
		return this;
	}
		
	public synchronized void type(char [] cbuf, int off, int len) throws IOException{
		textOut.type(cbuf, off, len);
	}

	@Override
	public void print(boolean b){
		print(String.valueOf(b));
	}

	@Override
	public void print(char c){
		print(String.valueOf(c));
	}

	@Override
	public void print(char [] s){
		print(new String(s));
	}

	@Override
	public void print(double d){
		print(String.valueOf(d));
	}

	@Override
	public void print(float f){
		print(String.valueOf(f));
	}

	@Override
	public void print(int i){
		print(String.valueOf(i));
	}

	@Override
	public void print(long l){
		print(String.valueOf(l));
	}

	@Override
	public void print(Object obj){
		print(String.valueOf(obj));
	}

	@Override
	public void print(String s){
		try{
			textOut.write(s);
		} catch(InterruptedIOException e){
			Thread.currentThread().interrupt();
		} catch(IOException e){
			setError();	
		}
	}

	@Override
	public PrintStream format(String format, Object... args){
		print(String.format(format, args));
		return this;
	}

	@Override
	public PrintStream format(Locale l, String format, Object... args){
		print(String.format(l, format, args));
		return this;
	}

	@Override
	public void println(boolean x){
		println(String.valueOf(x));
	}

	@Override
	public void println(char x){
		println(String.valueOf(x));
	}

	@Override
	public void println(char [] x){
		println(String.valueOf(x));
	}

	@Override
	public void println(double x){
		println(String.valueOf(x));
	}

	@Override
	public void println(float x){
		println(String.valueOf(x));
	}

	@Override
	public void println(int x){
		println(String.valueOf(x));
	}

	@Override
	public void println(long x){
		println(String.valueOf(x));
	}

	@Override
	public void println(Object x){
		println(String.valueOf(x));
	}

	@Override
	public void println(String x){
		print(x + '\n');
	}

	@Override
	public void println(){
		print("\n");
	}
	
	public TypeStream(OutputStream out){
		super(out);
		textOut = new TypeWriter(new OutputStreamWriter(out));
	}
	
	public TypeStream(OutputStream out, String encoding) throws UnsupportedEncodingException{
		super(out, false, encoding);
		textOut = new TypeWriter(new OutputStreamWriter(out, encoding));
	}

	public TypeStream(OutputStream out, Charset charset){
		super(out, false, charset);
		textOut = new TypeWriter(new OutputStreamWriter(out, charset));
	}

	public double getCPM(){ return textOut.getCPM(); }
	public int getMinDelay(){ return textOut.getMinDelay(); }
	public int getMaxDelay(){ return textOut.getMaxDelay(); }
	public int getAccuracy(){ return textOut.getAccuracy(); }
}
