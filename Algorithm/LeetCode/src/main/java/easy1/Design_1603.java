package easy1;

public class Design_1603 {

    public static final int BIG = 1;
    public static final int MEDIUM = 2;

    int big;
    int medium;
    int small;

    public Design_1603(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    // big = 1, medium = 2, small = 3
    public boolean addCar(int carType) {
        boolean result;
        if (carType == BIG) {
            result = big > 0;
            big -= 1;
        } else if (carType == MEDIUM) {
            result = medium > 0;
            medium -= 1;
        } else {
            result = small > 0;
            small -= 1;
        }

        return result;
    }
}
