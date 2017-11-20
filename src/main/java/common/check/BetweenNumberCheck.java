package common.check;

//проверка на вхождение числа в диапазон
public class BetweenNumberCheck extends CheckBase implements Check {

    private int min;
    private int max;

    public BetweenNumberCheck(String value, int min, int max) {
        super(value);
        this.min = min;
        this.max = max;
    }

    public boolean check() {
        int val = Integer.parseInt(value);
        return (val >= min) && (val <= max);
    }
}
