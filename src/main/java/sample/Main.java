package sample;

import java.util.HashMap;

public class Main {
    private Integer value;

    public Main(Integer value) {
        this.value = value;
    }

    public static void main(String[] args) {
        HashMap<Main, Integer> map = new HashMap<>();
        for (int i = 0; i < 100_000; i++) {
            Main c = new Main(2);
            System.out.println(c.hashCode());
            map.put(c, i);

        }

        System.out.println(map.size());
    }
}

