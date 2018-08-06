package sallat;

import java.util.Random;

class LatinMisstypeSupplier implements MisstypeSupplier{
	@Override
	public char supply(Random rand, char target){
		return (char) (rand.nextInt(95) + 32);
	}
}
