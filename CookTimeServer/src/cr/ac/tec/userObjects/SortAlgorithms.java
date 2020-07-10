package cr.ac.tec.userObjects;

import java.util.ArrayList;
import java.util.Collections;

public class SortAlgorithms {
	public ArrayList<String> bubbleSort(ArrayList<Recipe> listToSort){
		ArrayList<Recipe> returnList = listToSort;
		int length = returnList.size();
		for(int primIndex = 0; primIndex < length-1; primIndex++) {
			for(int secIndex = 0; secIndex < length - 1 - primIndex; secIndex++) {
				if(listToSort.get(secIndex).getDay() > listToSort.get(secIndex+1).getDay()) {
					Collections.swap(returnList, secIndex, secIndex+1);
				}
			}
		}
		return recipeToString(returnList);
	}
	
	public ArrayList<String> quickSort(ArrayList<Recipe> list) {
		return recipeToString(this.quickSort(list, 0, list.size()-1));
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
	
	
	
	private int getNumber(SortingType type, Recipe recipe) {
		if(type == SortingType.date) {
			return recipe.getDay();
		}else if(type == SortingType.difficulty) {
			return recipe.getDifficulty();
		}else{
			return recipe.getStars();
		}
	}
	
	public ArrayList<String> insertionSort(ArrayList<Recipe> list,  SortingType type) {
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
        return recipeToString(returnList);
	}
	
	
	private Integer findMax(ArrayList<Recipe> ar){
        int max = ar.get(0).getDifficulty();
        for(int temp = 1; temp < ar.size(); temp++){
            if(max < ar.get(temp).getDifficulty()){
                max = ar.get(temp).getDifficulty();
            }
        }
        return max;
    }

    private ArrayList<Recipe> countingSort(ArrayList<Recipe> ar, int power){
        ArrayList<Recipe> output = new ArrayList<>();

        ArrayList<Integer> count = new ArrayList<>();

        for (int i = 0; i <= 9; ++i) {
            count.add(0);
        }

        for (int i = 0; i < ar.size(); ++i) {
            output.add(new Recipe());
        }

        //
        for (int i = 0; i < ar.size(); ++i) {
            count.set((ar.get(i).getDifficulty()/power)%10, count.get((ar.get(i).getDifficulty()/power)%10)+1);
        }

        for (int i = 1; i < count.size(); ++i) {
            count.set(i, count.get(i) + count.get(i-1));
        }

        for (int i = ar.size()-1; i >= 0; i--) {
            output.set(count.get(((ar.get(i).getDifficulty()/power)%10))-1, ar.get(i));
            count.set((ar.get(i).getDifficulty()/power)%10, count.get((ar.get(i).getDifficulty()/power)%10)-1);
        }

        return output;
    }

    
    
    public ArrayList<String> radixSort(ArrayList<Recipe> ar){
        int max = findMax(ar);

        for(int cont = 0; max/(int)Math.pow(10, cont) >0; cont ++){
            if(cont < 1){
                ar = countingSort(ar, 1);
            }else

            ar = countingSort(ar, (int)Math.pow(10, cont));
        }

        return recipeToString(ar);
    }
    
    private ArrayList<String> recipeToString(ArrayList<Recipe> list){
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Recipe recipe: list) {
    		toReturn.add(recipe.getDishName());
    	}
    	return toReturn;
    }
	
	
}
