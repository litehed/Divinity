package divine.util;


public class SimpleUtil {
	
	public static int buttonWrap(int input, int min, int max) {
		if(input > max) {
			input = min;
		}else if(input < min) {
			input = max;
		}
		return input;
	}
}
