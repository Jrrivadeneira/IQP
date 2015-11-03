package model;

public class EMS {
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		a = a + b - (b = a);
		// System.out.print(a + " " + b);

		int[][][] map = new int[5][4][8];
		int i = 0;
		while (i < map.length * map[0].length * map[0][0].length) {
			map[i / (map[0].length * map[0][0].length)][(i / map[0][0].length)
					% map[0].length][i % map[0][0].length] = 1;
			i++;
		}
		for (int[][] k : map) {
			for (int[] c : k) {
				for (int m : c) {
					System.out.print(m);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
