package com.codingbat;

public class P104090 {
/*
 * Given n>=0, create an array with the pattern 
 * {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n} 
 * (spaces added to show the grouping). Note that the length of the array 
 * will be 1 + 2 + 3 ... + n, which is known to sum to exactly n*(n + 1)/2. 

* seriesUp(3) → {1, 1, 2, 1, 2, 3}
* seriesUp(4) → {1, 1, 2, 1, 2, 3, 1, 2, 3, 4}
* seriesUp(2) → {1, 1, 2}
 */
	public static void main(String[] args) throws Exception {
		P104090.seriesUp(3);
		P104090.seriesUp(4);
		P104090.seriesUp(2);

	}
	
	public static int[] seriesUp(int n) throws Exception{
		int[] series = new int[(n * (n+1)/2)];
		int idx = 0;
		
		if (n < 1) throw new Exception("'n' must be greater than zero.");
		
		for (int i = 1; i < n+1; i++) {
			for ( int j = 1; j < i+1; j++){
				series[idx]= j;
				idx++;
			}

		}
		
		return series;
		
	}

}
