package com.codingbat;

import static org.junit.Assert.*;

import org.junit.Test;

public class P104090Test {

	@Test
	public void testOneSeriesUp() {
		try {
			assertArrayEquals(new int[]{1, 1, 2, 1, 2, 3}, P104090.seriesUp(3));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testTwoSeriesUp() {
		try {
			assertArrayEquals(new int[]{1, 1, 2, 1, 2, 3, 1, 2, 3, 4}, P104090.seriesUp(4));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testThreeSeriesUp() {
		try {
			assertArrayEquals(new int[]{1, 1, 2}, P104090.seriesUp(2));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
