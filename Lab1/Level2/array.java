public class Spiral {
	 
    private static final int first_cell = 1; 
    private static final int size = 10; 

    public static void main(String... args) {
            Spiral calculator = new Spiral(size);
            calculator.fastConsoleDemo();
    }
   
    private int[][] mCoub;
    private int mSize;

    public Spiral(int size) {
            mSize = size;
    }

    public void fastConsoleDemo() {
            init();
            for (int i = 0; i < mSize; ++i) {
                    for (int j = 0; j < mSize; ++j) {
                            if (i > j) {
                                    if (i + j < mSize) {
                                            mCoub[i][j] = (mSize - (j + 1)) * (j + 1) * 4 - (i - (j + 1));
                                    } else {
                                            mCoub[i][j] = i * (mSize - i) * 4 - (i - (mSize - i)) - (j - mSize + i + 1);
                                    }
                            } else {
                                    if (i + j < mSize) {
                                            mCoub[i][j] = i * (mSize - i) * 4 - (i - (mSize - i)) + i + j - mSize + 1;
                                    } else {
                                            mCoub[i][j] = (mSize - (mSize - j - 1)) * (mSize - j - 1) * 4 + (j - (mSize - j)) + i + (j - mSize) + 3;
                                    }
                            }
                    }
            }
            System.out.print("Сторона квадрата: " + size + "\n");
            System.out.print(toString());
    }

    private void init() {
            mCoub = new int[mSize][mSize];
            mCoub[0][0] = first_cell;
    } 

    @Override
    public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("\n");
            for (int i = 0; i < mCoub.length; i++) {
                    for (int j = 0; j < mCoub[i].length; j++) {
                            builder.append(String.format("%4s", Integer.toString(mCoub[i][j])));
                    }
                    builder.append("\n");
            }
            return builder.toString();
    }
}