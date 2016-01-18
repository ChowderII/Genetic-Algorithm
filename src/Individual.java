import java.util.Random;
import java.util.Arrays;

/**
 * An <code>Individual</code> (a chromosome) is a candidate solution for a given
 * problem. Its representation depends on the specific problem to be solved. Two
 * individuals can be combined (see method crossover) to produce a new
 * offspring. As with natural chromosomes, these artificial ones suffer
 * mutations. Each chromosome has a fitness value that indicates the quality of
 * this solution.
 * <p/>
 * 
 * A <code>Population</code> is a collection of chromosomes. At each iteration
 * (generation), the genetic algorithm selects chromosomes for reproduction. The
 * offsprings are inserted into the population, and the least fitted individuals
 * are eliminated. Thus, the size of the population is fixed.
 * <p/>
 * 
 * For this assignment, an <code>Individual</code> represents a solution to the
 * <code>n</code>-Queens problem. As introduced in the assignment description, a
 * candidate solution is represented by a permutation of size <code>n</code>,
 * such that attribute <code>i</code> represents the row for the queen found at
 * column <code>i</code>.
 * <p/>
 * 
 * Not all permutations are valid solutions to <code>n</code>-Queens problem. A
 * permutation is a valid solution if no two queens can attack each other. Two
 * queens are attacking each other if they are on the same row or column, which
 * is impossible given this representation, but also if they are found on the
 * same minor or major diagonal.
 * <p/>
 *
 * Herein, we define the fitness value of an individual as the number of pairs
 * of queens attacking each other.
 * <p/>
 * You must complete the implementation of the class <code>Individual</code>
 * following all the directives.
 *
 * @author Marcel Turcotte (turcotte@eecs.uottawa.ca)
 */


public class Individual implements Comparable<Individual> {

	// INSERT YOUR CLASS AND INSTANCE VARIABLES HERE

	int[] n;

	/**
	 * Creates an <code>Individual</code> having <code>size</code> attributes.
	 * This constructor is used by the class <code>Population</code>.
	 * 
	 * @param size the number of attributes of this <code>Individual</code>
	 */

	public Individual(int size) {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

		@SuppressWarnings("unused")
		int[] n = new int[size]; //Assuming "number of attributes" is the number of queens
		//Then this constructor creates and individual with as an object

		this.n = Util.getPermutation(size);
	}

	/**
	 * Creates an <code>Individual</code> using the provided permutation. The method
	 * must copy the values of the permutation into a new array. This constructor
	 * is primarily used for testing.
	 * 
	 * @param permutation used to initialize the attributes of this <code>Individual</code>
	 */

	public Individual(int[] permutation) { // 10

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

		n = permutation;
	}

	/**
	 * Returns the offspring resulting from the crossover of <code>this</code>
	 * <code>Individual</code> and <code>other</code>. The result must be a valid
	 * permutation!
	 * <p/>
	 * 
	 * In particular, the naive solution consisting of taking the first
	 * <code>position-1</code> attributes of this <code>Individual</code> and
	 * the last <code>size-position</code> attributes of <code>other</code> would
	 * not generate a valid permutation in most cases.
	 * <p/>
	 *  
	 * Instead, we are proposing that the first <code>position-1</code> attributes 
	 * of this <code>Individual</code> are copied to the offspring, then the
	 * missing values will be selected from <code>other</code>, whilst preserving
	 * their order of appearance in <code>other</code>.
	 * <p/>
	 * 
	 * This method is primarily used for testing.
	 * 
	 * @param other a reference to an <code>Individual</code>
	 * @param position the location of the crossover
	 * @return the offspring resulting from the crossover of <code>this</code> and <code>other</code>
	 */

	public Individual crossover(Individual other, int position) {

		int[] n = new int[this.n.length]; // creates the array of the offspring
		int offspringI = 0; // creates an integer representing the index
		int otherI = 0;

		n[position] = this.n[position]; //take the value at [position] and implements it on the child at the same position.

		for (int i = 0; offspringI < n.length && otherI < n.length; i++)
		{
			if (offspringI != position && other.n[otherI] != n[position])
			{
				n[offspringI] = other.n[otherI];
				otherI++;
				offspringI++;
			}
			else
			{
				if (other.n[otherI] == this.n[position])
				{
					otherI++;
				}
				if (n[offspringI] == this.n[position])
				{
					offspringI++;
				}
			}
		}
		return (new Individual(n));
	}

	/**
	 * Returns the offspring resulting from the crossover of <code>this</code>
	 * <code>Individual</code> and <code>other</code>. The method randomly selects the
	 * position of the crossover. The result must be a valid permutation!
	 * <p/>
	 * 
	 * In particular, the naive solution consisting of taking the first
	 * <code>position-1</code> attributes of this <code>Individual</code> and the last
	 * <code>size-position</code> attributes of <code>other</code> would not generate a
	 * valid permutation in most cases.
	 * <p/>
	 * 
	 * Instead, we are proposing that the first <code>position-1</code> attributes
	 * of this <code>Individual</code> are copied to the offspring, then the missing
	 * values will be selected from <code>other</code>, whilst preserving their
	 * order of appearance in <code>other</code>.
	 * <p/>
	 * 
	 * This method is used by <code>Population</code>.
	 * 
	 * @param other a reference to an <code>Individual</code>
	 * @return the offspring resulting from the crossover of <code>this</code> and <code>other</code>
	 */

	public Individual recombine(Individual other) {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		int[] n = new int[this.n.length]; // creates the array of the offspring
		int offspringI = 0; // creates an integer representing the index
		int otherI = 0;
		int position = Util.randomInt(n.length);

		n[position] = this.n[position]; //take the value at [position] and implements it on the child at the same position.

		for (int i = 0; offspringI < n.length && otherI < n.length; i++)
		{
			if (offspringI != position && other.n[otherI] != n[position])
			{
				n[offspringI] = other.n[otherI];
				otherI++;
				offspringI++;
			}
			else
			{
				if (other.n[otherI] == this.n[position])
				{
					otherI++;
				}
				if (n[offspringI] == this.n[position])
				{
					offspringI++;
				}
			}
		}
		return (new Individual(n));
	}

	/**
	 * Returns the offspring resulting from applying a mutation
	 * to this <code>Individual</code>. In order to make sure that 
	 * the result is valid permutation, the method exchanges
	 * the value of two attributes, those found at positions
	 * <code>i</code> and <code>j</code>.
	 * 
	 * This method is primarily used for testing.
	 * 
	 * @param i the first attribute 
	 * @param j the second attribute
	 * @return the offspring resulting from exchanging attributes <code>i</code> and <code>j</code>
	 */

	public Individual mutate(int i, int j) { // 10

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

		int temporary = 0;

		temporary = this.n[i];
		this.n[i] = this.n[j];
		this.n[j] = temporary;

		return (new Individual(n));
	}

	/**
	 * Returns the offspring resulting from applying a mutation
	 * to this <code>Individual</code>. In order to make sure that 
	 * the result is valid permutation, the method exchanges
	 * the value of two randomly selected attributes.
	 * <p/>
	 * 
	 * This method is called by <code>Population</code>.
	 * 
	 * @return the offspring resulting from exchanging two randomly selected attributes
	 */

	public Individual mutate() {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		int i = Util.randomInt(this.n.length);
		int j = Util.randomInt(this.n.length);
		int temporary = 0;

		temporary = this.n[i];
		this.n[i] = this.n[j];
		this.n[j] = temporary;

		return (new Individual(n));
	}

	/**
	 * Returns the fitness value of <code>this Individual</code>, which
	 * is defined as the number of pairs of queens attacking each
	 * other.
	 * 
	 * @return the fitness value of <code>this Individual</code>. 
	 */

	public int getFitness() {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		int[] sum = new int[this.n.length];
		int[] sub = new int[this.n.length];
		
		int fitness = 0;
		
		for(int i = 0; i < this.n.length; i++){	
			sum[i] = i + this.n[i];
			sub[i] =  this.n[i] - i;
		}
		
		for(int i = 0; i<this.n.length; i++){
			for(int j = 0; j<this.n.length; ++j){
				if(i == j){j++;}
				if(j == this.n.length){break;}
				
				if(sum[i] == sum[j]){fitness++;}
				if(sub[i] == sub[j]){fitness++;}
			}
		}
		
		return fitness/2;
	}


	/**
	 * Returns a negative integer, zero, or a positive integer as the fitness of this <code>Individual</code> is
	 * less than, equal to, or greater than the fitness of the specified <code>Individual</code>. 
	 * 
	 * @param other <code>Individual</code> to be compared
	 * @return a negative integer, zero, or a positive integer as the fitness of this <code>Individual</code> 
	 *         is less than, equal to, or greater than the fitness of the specified <code>Individual</code>.
	 */

	public int compareTo(Individual other) {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		if (this.getFitness() < other.getFitness())
			return -1;
		else if (this.getFitness() == other.getFitness())
			return 0;
		else
			return 1;
	}

	/**
	 * Returns a string representation of this <code>Individual</code>.
	 * 
	 * @return a string representation of this <code>Individual</code>
	 */

	public String toString() {

		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		String text = new String();

		text = "[";

		for(int i = 0; i < this.n.length ;i++)
		{
			text += " " + this.n[i];
		}

		text += "]";

		return text;
	}

	/**
	 * Runs a series of tests.
	 * 
	 * @param args command line parameters of the program
	 */

	public static void main(String[] args) {
		// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		int[] n = {3, 2, 1, 0};
		Individual a2 = new Individual(n);

		System.out.print(a2.getFitness() + " nombre de reines individuels");

	}
}