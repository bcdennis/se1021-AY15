package com.codingbat;

public class P105031 {

	public static void main(String[] args) {
		
	}
	
	public static int[] shiftLeft(int[] nums) {
		  int[] holder = new int[nums.length];
		  if (nums.length == 0) return nums;
		  
		  int head = nums[0];
		  for (int i = 1; i < nums.length; i++) {
		     holder[i-1] = nums[i];
		  }
		  
		  holder[nums.length-1] = head;
		  
		  return holder;
		  
		}


}
