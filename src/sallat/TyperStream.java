package sallat;


import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class TyperStream extends PrintStream {

	public static final int DEFAULT_DELAY = 100;
	public static final int DEFAULT_ACCURACY = 100;
	public static final int DEFAULT_VARIATION = 0;

	private int minDelay = DEFAULT_DELAY;
	private int maxDelay = DEFAULT_DELAY;
	private int accuracy = DEFAULT_ACCURACY;
	private Object lock = new Object();

	private MisstypeSupplier supplier = new LatinMisstypeSupplier();

	public TyperStream setDelayRange(int minDelay, int maxDelay) throws IllegalArgumentException{
		if(maxDelay < 0 || minDelay < 0)
			throw new IllegalArgumentException("delay values can't be negative");
		if(maxDelay < minDelay)
			throw new IllegalArgumentException("max delay must be greater than min delay");
		synchronized(lock){
			this.minDelay = minDelay;
			this.maxDelay = maxDelay;
		}
		return this;
	}

	private void requirePercentValue(int num) throws IllegalArgumentException{
		if(num < 0 || num > 100)
			throw new IllegalArgumentException(num + " is not a percent value");
	}

	public TyperStream setDelay(int delay, int variation) throws IllegalArgumentException{
		requirePercentValue(variation);
		int diff = (int) (delay * ((double) variation / 100));
		return setDelayRange(delay - diff, delay + diff);
	}

	public TyperStream setCPM(double cpm, int variation) throws IllegalArgumentException{
		int delay = (int)((60 / cpm) * 1000);
		return setDelay(delay, variation);
	}

	public TyperStream setAccuracy(int accuracy) throws IllegalArgumentException{
		requirePercentValue(accuracy);
		this.accuracy = accuracy;
		return this;
	}

	public TyperStream setMisstypeSupplier(MisstypeSupplier supplier){
		this.supplier = Objects.requireNonNull(supplier);
		return this;
	}
		
	private void type(String s){
		Random rand = new Random();
		try{
			for(char ch : s.toCharArray()){
				if(rand.nextInt(100) > accuracy){
					printFlush(supplier.supply(rand, ch), rand);
					erase(rand);
				}
				printFlush(ch, rand);
			}
		} catch(InterruptedException e){ //when interrupted, stop typing
			return;
		}
	}

	private void printFlush(char ch, Random rand) throws InterruptedException{
		delay(rand);
		super.print(ch);
		flush();
	}
	
	private void erase(Random rand) throws InterruptedException{
		delay(rand);
		super.print("\b \b");
		flush();
	}

	private void delay(Random rand) throws InterruptedException{
		int delay;
		synchronized(lock){
			delay = minDelay + rand.nextInt(maxDelay - minDelay + 1);
		}
		Thread.sleep(delay);
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
	public synchronized void print(String s){
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
	public synchronized void println(String x){
		print(x);
		println();
	}

	@Override
	public synchronized void println(){
		type("\n");
	}
	
	public TyperStream(OutputStream out){
		super(out);
	}
	
	public TyperStream(OutputStream out, String encoding) throws UnsupportedEncodingException{
		super(out, false, encoding);
	}

	public TyperStream(OutputStream out, Charset charset){
		super(out, false, charset);
	}

	public double getCPM(){
		double avgDelay = (minDelay + maxDelay) / 2.0;
		return 60 / (avgDelay / 1000);
	}
	
	public int getMinDelay(){ return minDelay; }
	public int getMaxDelay(){ return maxDelay; }
	public int getAccuracy(){ return accuracy; }
}
