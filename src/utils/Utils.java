package utils;

import exceptii.MyException;

public class Utils {
    public static void tryExecute(Runnable runnable) {
        try {
            runnable.run();
            System.out.println("Success!");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
    }
}
