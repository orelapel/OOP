/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public class Counter {
    private int myNum;

    /**
     * Constractor.
     * <p>
     * @param num - the number we count.
     */
    public Counter(int num) {
        this.myNum = num;
    }

    /**
     * Add number to current count.
     * <p>
     * @param number - the number we want to add.
     */
    void increase(int number) {
        this.myNum += number;
    }

    /**
     * Subtract number from current count.
     * <p>
     * @param number - the number we want to subtract.
     */
    void decrease(int number) {
        this.myNum -= number;
    }

    /**
     * Get current count.
     * <p>
     * @return - the current count.
     */
    int getValue() {
        return this.myNum;
    }
}
