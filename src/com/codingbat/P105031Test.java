package com.codingbat;

import static org.junit.Assert.*;

import org.junit.Test;

public class P105031Test {

	@Test
	public void testOneShiftLeft() {
		assertArrayEquals(new int[]{2,5,3,6}, P105031.shiftLeft(new int[]{6, 2, 5, 3}));
	}
	@Test
	public void testTwoShiftLeft() {
		assertArrayEquals(new int[]{1, 2}, P105031.shiftLeft(new int[]{2,1}));
	}
	@Test
	public void testThreeShiftLeft() {
		assertArrayEquals(new int[]{1}, P105031.shiftLeft(new int[]{1}));
	}

}
