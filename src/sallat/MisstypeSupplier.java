package sallat;

import java.util.Random;

@FunctionalInterface
public interface MisstypeSupplier{
	char supply(Random rand, char target);
}
