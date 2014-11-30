package com.codingbat;

public class P137874 {

   public static void main(String[] args) {
   	// TODO Auto-generated method stub
   
   }
	
   public static boolean tripleUp(int[] nums) {
      boolean hasThree = false;
   	
      if (nums.length < 3 ) 
         return hasThree;
   	
      int first = nums[0];
      int second = first + 1;
      int third = second + 1;
      boolean reset = false;
   	
      for (int i = 1; i < nums.length; i++) {
         if (second == nums[i]){
            if (i < nums.length -1 ) {
               if (third == nums[i + 1]) {
                  hasThree = true;
               } 
               else {
					reset = true;
               }
            }
         } 
         else {
        	 reset = true;
         }
         
         if (reset) {
             first = nums[i];
             second = first + 1;
             third = second + 1;	
         }
      
      }
   	
      return hasThree;
   }

}
