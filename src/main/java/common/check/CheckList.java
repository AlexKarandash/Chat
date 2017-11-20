package common.check;

import java.util.ArrayList;
import java.util.List;

//компоновка проверок и проверка как единое
public class CheckList implements Check {

    private List<Check> listCheck = new ArrayList();

    public boolean check() {
        boolean res = true;
        for (Check check : listCheck) {
            res = check.check();
            if (!res) break;
        }
        return res;
    }

    public void add(Check check) {
        listCheck.add(check);
    }

    public void remove(int index) {
        listCheck.remove(index);
    }

    public int size() {
        return listCheck.size();
    }
}
