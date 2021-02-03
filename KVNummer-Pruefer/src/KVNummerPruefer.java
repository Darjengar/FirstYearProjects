/**
 * 
 */

/**
 * @author Joel Nauschütz
 *
 */
public class KVNummerPruefer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String versicherungsnummer = args[0];
		int charValCnt = 0;
		int n_weights = 3;
		int charWeights[] = { 7, 3, 1 };	
		char pruefziffer = versicherungsnummer.
				charAt(versicherungsnummer.length()-1);	
		char testPruefziffer;
		
		for (int i = 0; i < versicherungsnummer.length()-1; i++) {
			switch (versicherungsnummer.charAt(i)) {
				case '0':
					charValCnt += 0;
					break;
				case '1':
					charValCnt += 1 * charWeights[i % 3];
					break;
				case '2':
					charValCnt += 2 * charWeights[i % 3];
					break;
				case '3':
					charValCnt += 3 * charWeights[i % 3];
					break;
				case '4':
					charValCnt += 4 * charWeights[i % 3];
					break;
				case '5':
					charValCnt += 5 * charWeights[i % 3];
					break;
				case '6':
					charValCnt += 6 * charWeights[i % 3];
					break;
				case '7':
					charValCnt += 7 * charWeights[i % 3];
					break;
				case '8':
					charValCnt += 8 * charWeights[i % 3];
					break;
				case '9':
					charValCnt += 9 * charWeights[i % 3];
					break;
				case 'A':
					charValCnt += 10 * charWeights[i % 3];
					break;
				case 'B':
					charValCnt += 11 * charWeights[i % 3];
					break;
				case 'C':
					charValCnt += 12 * charWeights[i % 3];
					break;
				case 'D':
					charValCnt += 13 * charWeights[i % 3];
					break;
				case 'E':
					charValCnt += 14 * charWeights[i % 3];
					break;
				case 'F':
					charValCnt += 15 * charWeights[i % 3];
					break;
				case 'G':
					charValCnt += 16 * charWeights[i % 3];
					break;
				case 'H':
					charValCnt += 17 * charWeights[i % 3];
					break;
				case 'I':
					charValCnt += 18 * charWeights[i % 3];
					break;
				case 'J':
					charValCnt += 19 * charWeights[i % 3];
					break;
				case 'K':
					charValCnt += 20 * charWeights[i % 3];
					break;
				case 'L':
					charValCnt += 21 * charWeights[i % 3];
					break;
				case 'M':
					charValCnt += 22 * charWeights[i % 3];
					break;
				case 'N':
					charValCnt += 23 * charWeights[i % 3];
					break;
				case 'O':
					charValCnt += 24 * charWeights[i % 3];
					break;
				case 'P':
					charValCnt += 25 * charWeights[i % 3];
					break;
				case 'Q':
					charValCnt += 26 * charWeights[i % 3];
					break;
				case 'R':
					charValCnt += 27 * charWeights[i % 3];
					break;
				case 'S':
					charValCnt += 28 * charWeights[i % 3];
					break;
				case 'T':
					charValCnt += 29 * charWeights[i % 3];
					break;
				case 'U':
					charValCnt += 30 * charWeights[i % 3];
					break;
				case 'V':
					charValCnt += 31 * charWeights[i % 3];
					break;
				case 'W':
					charValCnt += 32 * charWeights[i % 3];
					break;
				case 'X':
					charValCnt += 33 * charWeights[i % 3];
					break;
				case 'Y':
					charValCnt += 34 * charWeights[i % 3];
					break;
				case 'Z':
					charValCnt += 35 * charWeights[i % 3];
					break;
			}
		}
		
		if (charValCnt % 10 == 0) {
			testPruefziffer = '0';
		}
		else {
			testPruefziffer = (char)('0' + (10 - charValCnt % 10));
		}
		
		if (testPruefziffer == pruefziffer) {
			System.out.println("Korrekt");
		}
		else {
			System.out.println("Inkorrekt");
			System.out.println("Korrekte Ziffer: " + testPruefziffer);
		}
		
	}
}
