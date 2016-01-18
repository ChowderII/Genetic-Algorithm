import java.util.Arrays;
import java.util.Random;

/** 
 * A <code>Population</code> is a collection of individuals (each one
 * representing a candidate solution for the n-queens problem).  To
 * facilitate the implementation of the various methods, <b>the
 * individuals will always be kept in increasing value of fitness</b>.
 */

public class Population {

	// INSERT YOUR CLASS AND INSTANCE VARIABLES HERE
	Individual[] pop;
	public static final double MUTATION_RATE = 0.8;
	/**
	 * A constructor of arity 2 to initialize the <b>Population</b>.
	 * 
	 * @param size is the number of individuals of this population
	 * @param dimension is the size of the board and also the number of queens
	 */

	public Population(int size, int dimension) {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		pop = new Individual[size];

		for (int i = 0; i < size; i++)
		{
			pop[i] = new Individual(dimension);
		}
	}

	/**
	 * The method <code>evolve</code> selects parent individuals. An offspring
	 * is then created from the two parents, using the method
	 * <code>crossover</code>. With probability <code>MUTATION_RATE</code>, the
	 * offspring is <code>mutated</code>. Use 0.8 as the default
	 * <code>MUTATION_RATE</code> The resulting child is inserted into the
	 * population. As a result, the least fitted individual will be eliminated
	 * from the population. Remember that the <code>population</code> is kept in
	 * increasing order of fitness. For the selection of the parents, you can
	 * experiment with different scenarios. A possible scenario is to randomly
	 * select two parents. Another possible one would be to select the most fit,
	 * and a randomly selected one. Or else, select the two most fitted
	 * individuals.
	 */

	public void evolve() {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		int one = Util.randomInt(this.pop.length);
		int two = Util.randomInt(this.pop.length);

		this.pop[one].recombine(this.pop[two]);

		int chance = (Util.randomInt(10)/10);

		if (chance >= MUTATION_RATE)
			this.pop[one].mutate();
	}

	/**
	 * The instance method <code>public Individual getFittest()</code> returns the
	 * "best" individual of the population, i.e. the one that has the smallest
	 * fitness value.
	 * 
	 * @return returns the currently best solution
	 */

	public Individual getFittest() {

		Individual temp = new Individual(this.pop[0].n.length);

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		for (int j = this.pop.length-1; j > 0; j--)	 
			for(int i = this.pop.length-1; i > 0; i--){
				if (this.pop[i-1].compareTo(this.pop[i]) > 0) {
					temp = this.pop[i];
					this.pop[i] = this.pop[i-1];
					this.pop[i-1] = temp;
				} 				 
			}	
		return (this.pop[0]);
	}

	/**
	 * Returns a <code>String</code> representation of this <code>Population</code>.
	 * 
	 * @return the String representation of this Population
	 */

	public String toString() {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		String text = new String();

		text = "[";

		for(int i = 0; i < this.pop[0].n.length ;i++)
		{
			text += " " + this.pop[0].n[i];
		}

		text += "]";

		return text;
	}

	public static void main(String[] args)
	{
		Population test1 = new Population(6, 4);

		test1.getFittest();

		System.out.print(test1.toString());
	}

}