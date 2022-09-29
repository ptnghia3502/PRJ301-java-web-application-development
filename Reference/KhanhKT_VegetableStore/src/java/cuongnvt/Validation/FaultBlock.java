package cuongnvt.Validation;

import java.util.ArrayList;

public class FaultBlock {

    private ArrayList<String> list;

    public FaultBlock() {
        list = new ArrayList<>();
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public boolean formatCheck(String x, int lower, int upper, String out, boolean checkLength) {
        String faultString = "";
        if (x == null) {
            faultString = "Please do not not use non - Alphanumeric Characters on " + out + " field.";
        }
        if (x != null) {
            if (checkLength && (x.length() < lower || x.length() > upper)) {
                faultString = "The length of " + out + " doesn't match condition (" + lower + " - " + upper + ").";
            } else {
                return false;
            }
        }
        list.add(faultString);
        return true;

    }

    public boolean SimilarityCheck(String x, String y, String xOut, String yOut) {
        String faultString;
        if (x == null || y == null) {
            return false;
        }
        if (!x.equals(y)) {
            faultString = yOut + " must be similar to " + xOut + ".";
            list.add(faultString);
            return true;
        }
        return false;

    }

}
