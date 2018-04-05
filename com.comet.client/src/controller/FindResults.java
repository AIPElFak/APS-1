package controller;

import java.util.ArrayList;
import java.util.List;

public class FindResults implements Container {
	
   private List<Integer> findResults;

   public FindResults(String text, String find){
	   
	   findResults = new ArrayList<Integer>();
	   for (int i = 0; i + find.length() < text.length(); i++) {
	        if(text.substring(i, i + find.length()).equals(find)) {
	        	findResults.add(new Integer(i));
	        }
	   }
		
   }
   
   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
      
         if(index < findResults.size()){
            return true;
         }
         return false;
      }

      @Override
      public Integer next() {
      
         if(this.hasNext()){
            return findResults.remove(index++);
         }
         return new Integer(-1);
      }		
   }
}