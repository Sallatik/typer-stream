package sallat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;

public class Typer extends PrintStream {

	public static final long DEFAULT_DELAY = 100;
	
	private long delay = DEFAULT_DELAY;
	
	public long getDelay(){ return delay; }

	public Typer setDelay(long delay){ 
		this.delay = delay;
		return this;
       	}

	public Typer(OutputStream out){
		super(out);
	}

	public Typer(OutputStream out, boolean autoFlush){
		super(out, autoFlush);
	}

	public Typer(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException{
		super(out, autoFlush, encoding);
	}

	public Typer(OutputStream out, boolean autoFlush, Charset charset){
		super(out, autoFlush, charset);
	}
		
	private void type(String s){
		for(int i = 0; i < s.length(); i++){
			try { Thread.sleep(delay) ;} catch(InterruptedException ex) {} // fix it!
			super.print(s.charAt(i));
		}
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
		type(s);
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
		print(x);
		super.println();
	}

}
