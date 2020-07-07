package cr.ac.tec.userObjects;

import java.util.ArrayList;
import java.util.Collections;

public class SortAlgorithms {
	public ArrayList<Recipe> bubbleSort(ArrayList<Recipe> listToSort){
		ArrayList<Recipe> returnList = listToSort;
		int length = returnList.size();
		for(int primIndex = 0; primIndex < length-1; primIndex++) {
			for(int secIndex = 0; secIndex < length - 1 - primIndex; secIndex++) {
				if(listToSort.get(secIndex).getDay() > listToSort.get(secIndex+1).getDay()) {
					Collections.swap(returnList, secIndex, secIndex+1);
				}
			}
		}
		return returnList;
	}
	
	public ArrayList<Recipe> quickSort(ArrayList<Recipe> list) {
		return this.quickSort(list, 0, list.size()-1);
	}
	
	private ArrayList<Recipe> quickSort(ArrayList<Recipe> list, int start, int end){
		ArrayList<Recipe> listReturn = list;
		if(start > end) {
			int partion = partion(listReturn, start, end);
			quickSort(listReturn, start, partion-1);
			quickSort(listReturn, partion+1, end);
		}
		return listReturn;
	}
	
	private int partion(ArrayList<Recipe> list, int start, int end) {
		int pivot = list.get(end).getStars();
		int begin = start-1;
		for(int primIndex = start; primIndex < end; primIndex++) {
			if(list.get(primIndex).getStars() <= pivot) {
				begin++;
				Collections.swap(list, begin, primIndex);
			}
		}
		Collections.swap(list, begin+1, end);
		return begin+1;
	}
	
	public ArrayList<Recipe> radixSort(){
		
	}
	
	private int getNumber(SortingType type, Recipe recipe) {
		if(type == SortingType.date) {
			return recipe.getDay();
		}else if(type == SortingType.difficulty) {
			return recipe.getDifficulty();
		}else (type == SortingType.stars) {
			return recipe.getStars();
		}
	}
	
	public ArrayList<Recipe> insertionSort(ArrayList<Recipe> list,  SortingType type) {
		ArrayList<Recipe> returnList = list;
		int listSize = returnList.size(); 
        for (int primIndex = 1; primIndex < listSize; ++primIndex) { 
            int key = getNumber(type, returnList.get(primIndex)); 
            int secIndex = primIndex - 1; 
            while (secIndex >= 0 && getNumber(type, returnList.get(secIndex)) > key) { 
                Collections.swap(returnList, secIndex+1, secIndex); 
                secIndex = secIndex - 1; 
            }   
        }
        return returnList;
	}
	
	
	
}
