import domain.Entity;
import domain.User;
import domain.validation.NoValidation;
import domain.validation.Validator;

import java.util.Arrays;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] arr = {"edijioed", "edkneod", "edjiedjn", "edjniend"};
        String[] sir = Arrays.copyOfRange(arr, 1, 4);
        for(String s : sir)
            System.out.println(s);
    }
}