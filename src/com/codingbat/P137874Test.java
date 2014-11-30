package com.codingbat;

import static org.junit.Assert.*;

import org.junit.Test;

public class P137874Test {

	@Test
	public void testOneTripleUp() {
		assertTrue(P137874.tripleUp(new int[]{1, 4, 5, 6, 2}));
	}
	
	@Test
	public void testTwoTripleUp() {
		assertTrue(P137874.tripleUp(new int[]{1, 2, 3}));
	}

	@Test
	public void testThreeTripleUp() {
		assertFalse(P137874.tripleUp(new int[]{1, 2, 4}));
	}
	

}
