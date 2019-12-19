class Logic {
	private boolean field[][];
	private int S;
	private int lastM[][];
	private int cA, cB;
	private int moves[] = { -1, 1 };
	boolean north, east, south, west;
	int counter = 0;

	Logic(int size) {
		field = new boolean[size][size];
		S = size;
		lastM = new int[S * S][2];
	}

	void openSite() {
		int b = (int) (Math.random() * field.length - 1);
		if (b == 0)
			b++;
		lastM[counter][0] = 0;
		lastM[counter][1] = b;
		field[0][b] = true;
		openSite(0, b);
	}

	private void end() {
		int b = (int) (Math.random() * field.length - 1);
		while (!field[S - 2][b]) {
			b = (int) (Math.random() * field.length - 1);
		}
		field[S - 1][b] = true;
	}

	private void openSite(int a, int b) {
		if (north && south && east && west) {
			if (counter < 1) {
				return;
			}
			a = lastM[counter - 1][0];
			b = lastM[counter - 1][1];
			counter--;
			north = east = south = west = false;
			openSite(a, b);
		}
		cA = a;
		cB = b;
		System.out.println("start " + a + " " + b);
		int choice = (int) (Math.random() * 2);
		System.out.println(choice);
		if (choice == 0)
			a += moves[(int) (Math.random() * 2)];
		else
			b += moves[(int) (Math.random() * 2)];
		System.out.println(a + "--" + b);
		if (a > cA)
			south = true;
		else
			north = true;
		if (b > cB)
			east = true;
		else
			west = true;
		if (goodNeighbors(a, b)) {
			System.out.println(a + " good " + b);
			north = east = south = west = false;
			lastM[counter][0] = a;
			lastM[counter][1] = b;
			counter++;
			field[a][b] = true;
			openSite(a, b);
		} else {
			openSite(cA, cB);
		}
	}

	private boolean goodNeighbors(int a, int b) {
		if (!isAllowed(a, b))
			return false;
		System.out.println(a + " " + b);
		if (a + 1 != cA)
			if (field[a + 1][b])
				return false;

		if (b + 1 != cB)
			if (field[a][b + 1])
				return false;

		if (a - 1 != cA)
			if (field[a - 1][b])
				return false;
		if (b - 1 != cB)
			if (field[a][b - 1])
				return false;
		System.out.println(a + " " + b + "sd");
		return true;
	}

	boolean[][] getField() {
		return field;
	}

	private boolean isAllowed(int a, int b) {
		if (a < 1 || b < 1)
			return false;
		if (a >= S - 1 || b >= S - 1)
			return false;
		if (field[a][b])
			return false;
		return true;
	}

	void show() {
		end();
		for (int a = 0; a < S; a++) {
			for (int b = 0; b < S; b++) {
				if (field[a][b])
					System.out.print("*");
				else
					System.out.print("0");
				System.out.print("|");
			}

			System.out.println("");
		}
	}

}