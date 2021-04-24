/**
 *
 */

/**
 * @author JDarjengar
 *
 */
public class Bigs {

	/**
	 * @param args
	 */
	public static void main (String[ ] s) {
		int[] a = One();

		for (int i=0; i<33222; ++i) {
			a = times(a, 2);
		}
		
		print(a);

		System.out.println("2^33222 hat " + a.length + " Stellen");
		print(a); 
		System.out.println(); 

		int[] b = fromInt(13);
		int[] c = copy(b);

		for (int i=1; i<8978; ++i) {
			c=times(c, b);
		}

		System.out.println("13^8978 hat " + c.length + " Stellen");
		print(c); 
		System.out.println(); 

		System.out.println(less(a, c)); // beantwortet die Frage aus der Aufgabenueberschrift
		maxDigit(a);
		maxDigit(c);
	}
	
	// addiert die Ziffernfelder a und b
	public static int[ ] add (int[ ] a, int[ ] b) {
		int sum[];
		if (a.length < b.length) {
			int[] b_cpy = copy(b);
			for (int iii = 0; iii < a.length; iii++) {
				b_cpy[iii] += a[iii];
			}
			for (int iii = 0; iii < b_cpy.length-1; iii++) {
				if (b_cpy[iii] >= 10) {
					b_cpy[iii] %= 10;
					b_cpy[iii+1] += 1;
				}
			}
			if (b_cpy[b_cpy.length-1] >= 10) {
				sum = new int[b_cpy.length+1];
				for (int iii = 0; iii < sum.length-2; iii++) {
					sum[iii] = b_cpy[iii];
				}
				sum[sum.length-2] = b_cpy[b_cpy.length-1] % 10;
				sum[sum.length-1] += 1;
			}
			else {
				sum = copy(b_cpy);
			}
		}
		else {
			int[] a_cpy = copy(a);
			for (int iii = 0; iii < b.length; iii++) {
				a_cpy[iii] += b[iii];
			}
			for (int iii = 0; iii < a_cpy.length-1; iii++) {
				if (a_cpy[iii] >= 10) {
					a_cpy[iii] %= 10;
					a_cpy[iii+1] += 1;
				}
			}
			if (a_cpy[a_cpy.length-1] >= 10) {
				sum = new int[a_cpy.length+1];
				for (int iii = 0; iii < sum.length-2; iii++) {
					sum[iii] = a_cpy[iii];
				}
				sum[sum.length-2] = a_cpy[a_cpy.length-1] % 10;
				sum[sum.length-1] += 1;
			}
			else {
				sum = copy(a_cpy);
			}
		}
		return sum;
	}
				
	// gibt das Ziffernfeld n in lesbarer dezimaler Form aus
	static void print (int[ ] n) {
		for (int iii = 0; iii < n.length; iii++) {
			System.out.print(n[n.length-1-iii]);
			if ((iii+1) % 68 == 0) {
				System.out.print("\\");
				System.out.println();
			}
		}
		System.out.println();
	}
		
	// konstruiert ein einstelliges Ziffernfeld aus d
	static int[ ] digit(int d) {
		int[] D = { d };
		return D;
	}
	 
	// konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
	static int[ ] Null() { return digit(0); }
		
	// konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
	static int[ ] One() { return digit(1); }

	// Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
	static int mod10(int[ ] n) {
		return n[0];
	}

	// ganzzahliger Quotient bei Division durch 10
	static int[ ] div10(int[ ] n) { 
		int quotient[] = new int[n.length-1];
		for (int iii = 1; iii < n.length; iii++) {
			quotient[iii-1] = n[iii];
		}
		return quotient;
	}

	// Umwandlung einer int-Zahl in ein Ziffernfeld	
	static int[ ] fromInt(int n) {
		int[] N = new int[Integer.toString(n).length()];
		for (int iii = 1; iii <= N.length; iii++) {
			N[iii-1] = n % (int) Math.pow(10, iii) / (int) Math.pow(10, iii-1);
		}
		return N;
	}
	
	static int toInt(int[] N) {
		int n = 0;
		for (int iii = 0; iii < N.length; iii++) {
			n += N[iii] * (int) Math.pow(10, iii);
		}
		return n;
	}

	// kopiert den Wert von a
	static int[ ] copy(int[ ] n) {
		int[] n_cpy = new int[n.length];
		for (int iii = 0; iii < n.length; iii++) {
			n_cpy[iii] = n[iii];
		}
		return n_cpy;
	}

	// multipliziert das Ziffernfeld a mit einer int-Zahl
	static int[ ] times(int[ ] n, int d) {
		int[] product = Null();
		for (int iii = 1; iii <= d; iii++) {
			product = add(product, n);
		}
		return product;
	}

	// multipliziert das Ziffernfeld n mit 10
	static int[ ] times10(int[ ] n) {
		int[] product = new int[n.length + 1];
		product[0] = 0;
		for (int iii = 1; iii < product.length; iii++) {
			product[iii] = n[iii-1];
		}
		return product;
	}

	// multipliziert zwei Ziffernfelder	
	static int[ ] times(int[ ]a, int[ ] b) {
		int[] product = Null();
		int[] tmp = Null();
		for (int iii = 0; iii < b.length; iii++) {
			tmp = times(a, b[iii]);
			for (int jjj = 0; jjj < iii; jjj++) {
				tmp = times10(tmp);
			}
			product = add(tmp, product);
		}
		return product;
	}

	// Quadratzahl eines Ziffernfeldes
	static int[ ] square(int[ ]a) { return times(a, a);  }

	// Kubikzahl eines Ziffernfeldes
	static int[ ] cubic(int[ ]a) { return times(a, square(a)); }

	// Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
	static boolean less (int[ ] a, int[ ] b) {
		boolean isLess = false;
		if (a.length < b.length) {
			isLess = true;
		}
		else if (a.length == b.length) {
			for (int iii = 0; iii < a.length; iii++) {
				if (a[a.length-1-iii] < b[b.length-1-iii]) {
					isLess = true;
					break;
				}
				else if (a[a.length-1-iii] > b[b.length-1-iii]) {
					break;
				}
			}
		}
		return isLess;
	}

	// Test auf Gleichheit zweier Ziffernfelder
	static boolean equal (int[ ] a, int[ ] b){
		boolean isEqual = true;
		if (a.length != b.length) {
			isEqual = false;
		}
		else {
			for (int iii = 0; iii < a.length; iii++) {
				if (a[iii] != b[iii]) {
					isEqual = false;
				}
			}
		}
		return isEqual;
	}

	// Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
	// mindenstens eine Ziffer, alle Positionen liegen zwischen 0 und 9
	// keine fuehrenden Nullen (ausser bei Null selbst) 
	static boolean ok (int[ ] n) {
		boolean isOk = true;
		/* leading zeros or empty array */
		if ((n.length > 1 && n[n.length-1] == 0) || n.length == 0) {
				isOk = false;
		}
		else {
			for (int iii = 0; iii < n.length; iii++) {
				if ( !(0 <= n[iii]) || !(n[iii] <= 9) ) {
					isOk = false;
				}
			}
		}
		return isOk;
	}

	// gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus	
	static void maxDigit(int[] n) {
		int[] digit_counter = new int[10];
		for (int iii = 0; iii < n.length; iii++) {
			switch (n[iii]) {
				case 0:
					digit_counter[0] += 1;
					break;
				case 1:
					digit_counter[1] += 1;
					break;
				case 2:
					digit_counter[2] += 1;
					break;
				case 3:
					digit_counter[3] += 1;
					break;
				case 4:
					digit_counter[4] += 1;
					break;
				case 5:
					digit_counter[5] += 1;
					break;
				case 6:
					digit_counter[6] += 1;
					break;
				case 7:
					digit_counter[7] += 1;
					break;
				case 8:
					digit_counter[8] += 1;
					break;
				case 9:
					digit_counter[9] += 1;
					break;
			}
		}
		
		int min_digit = 0;
		int max_count = digit_counter[0];
		for (int iii = 1; iii < digit_counter.length; iii++) {
			if (max_count < digit_counter[iii]) {
				min_digit = iii;
				max_count = digit_counter[iii];
			}
		}
		System.out.println(min_digit + ": " + max_count);
	}
}
