package util;

import java.util.ArrayList;

public class ArrayListUtil {
	public static <T> void clearArray(ArrayList<T>array){
		int length = array.size();
		for (int i = 0; i < length; i++) {
			array.remove(0);
		}
	}
}
