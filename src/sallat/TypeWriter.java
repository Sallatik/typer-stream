package sallat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class TypeWriter extends FilterWriter implements Typer{

	private int minDelay = Typer.DEFAULT_DELAY;
	private int maxDelay = Typer.DEFAULT_DELAY;
	private int accuracy = Typer.DEFAULT_ACCURACY;

	private Object lock = new Object();

	private MisstypeSupplier supplier = new LatinMisstypeSupplier();

	public TypeWriter setDelayRange(int minDelay, int maxDelay) throws IllegalArgumentException{
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

	public Typer setDelay(int delay, int variation) throws IllegalArgumentException{
		requirePercentValue(variation);
		int diff = (int) (delay * ((double) variation / 100));
		return setDelayRange(delay - diff, delay + diff);
	}

	public Typer setCPM(double cpm, int variation) throws IllegalArgumentException{
		int delay = (int)((60 / cpm) * 1000);
		return setDelay(delay, variation);
	}

	public Typer setAccuracy(int accuracy) throws IllegalArgumentException{
		requirePercentValue(accuracy);
		this.accuracy = accuracy;
		return this;
	}

	public Typer setMisstypeSupplier(MisstypeSupplier supplier){
		this.supplier = Objects.requireNonNull(supplier);
		return this;
	}
		
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException{
		type(cbuf, off, len);
	}
	
	@Override
	public void write(int c) throws IOException{
		char [] cbuf = { (char) c };
		type(cbuf, 0, 1);
	}
	
	@Override
	public void write(String str, int off, int len) throws IOException{
		type(str.toCharArray(), off, len);
	}

	public synchronized void type(char [] cbuf, int off, int len) throws IOException{
		Random rand = new Random();
		try{
			for(int i = off; i < len; i++){
				char ch = cbuf[i];
				if(rand.nextInt(100) > accuracy){
					writeFlush(supplier.supply(rand, ch), rand);
					erase(rand);
				}
				writeFlush(ch, rand);
			}
		} catch(InterruptedException e){ //when interrupted, stop typing
			return;
		}
	}

	private void writeFlush(char ch, Random rand) throws InterruptedException, IOException{
		delay(rand);
		out.write(ch);
		flush();
	}
	
	private void erase(Random rand) throws InterruptedException, IOException{
		delay(rand);
		super.write("\b \b", 0, 3);
		flush();
	}

	private void delay(Random rand) throws InterruptedException{
		int delay;
		synchronized(lock){
			delay = minDelay + rand.nextInt(maxDelay - minDelay + 1);
		}
		Thread.sleep(delay);
	}	
	
	public TypeWriter(Writer out){
		super(out);
	}

	public double getCPM(){
		double avgDelay = (minDelay + maxDelay) / 2.0;
		return 60 / (avgDelay / 1000);
	}
	
	public int getMinDelay(){ return minDelay; }
	public int getMaxDelay(){ return maxDelay; }
	public int getAccuracy(){ return accuracy; }
	
	private void requirePercentValue(int num) throws IllegalArgumentException{
		if(num < 0 || num > 100)
			throw new IllegalArgumentException(num + " is not a percent value");
	}
}
