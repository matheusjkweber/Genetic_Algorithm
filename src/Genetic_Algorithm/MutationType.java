package Genetic_Algorithm;

/**
* The MutationType enum describe the possible options for the mutation of genes.
*
* @author  Matheus Jose Krumenauer Weber
* @version 1.0
* @since   2017-01-07
*/

// SBS - Vary step by step.
// SBSD100 - Vary step by step divided by 100.
// SBSD10 - Vary step by step divided by 10.
// SBSD3 - Vary step by step divided by 3.

public enum MutationType {
	SBS, SBSD100, SBSD10, SBSD3, NONE;
}
