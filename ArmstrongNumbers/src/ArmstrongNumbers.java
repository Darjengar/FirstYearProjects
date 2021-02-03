public class ArmstrongNumbers {
	public static void main(String[] args) {
		int[] test = giveArmstrongNumbers(32);
		// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634
		printArray(test);
	}

	public static boolean isArmstrongNumber(int number) {
		int num_digits = Integer.toString(number).length();
		int armstrongSum = 0;
		boolean isArmstrong = false;
		
		for (int iii = 0, digit = 0, tmp = number; iii < num_digits; iii++) {
			digit = tmp % 10;
			armstrongSum += Math.pow(digit, num_digits);
			tmp /= 10;
		}

		if (armstrongSum == number) {
			isArmstrong = true;
		}
		return isArmstrong;
	}

	public static int[] giveArmstrongNumbers(int n) {
		int[] armstrongNumbers = new int[n];
		
		int armstrongCnt = 0;
		int number = 0;

		while (armstrongCnt < n) {
			if (isArmstrongNumber(number)) {
				armstrongNumbers[armstrongCnt++] = number;
			}
			number++;
		}
		
		return armstrongNumbers;
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(i < a.length - 1 ? ", " : "\n");
		}
	}
}
