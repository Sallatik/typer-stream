package sallat;

import java.io.IOException;

public interface Typer{
	
	int DEFAULT_DELAY = 100;
	int DEFAULT_ACCURACY = 100;
	int DEFAULT_VARIATION = 0;

	Typer setDelayRange(int min, int max) throws IllegalArgumentException;

	Typer setDelay(int delay, int variation) throws IllegalArgumentException;

	Typer setCPM(double cpm, int variation) throws IllegalArgumentException;

	Typer setAccuracy(int accuracy) throws IllegalArgumentException;

	Typer setMisstypeSupplier(MisstypeSupplier supplier);

	void type(char [] cbuf, int off, int len) throws IOException;

	double getCPM();

	int getMinDelay();

	int getMaxDelay();

	int getAccuracy();
}
