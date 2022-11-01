package utils;

import exceptii.MyException;

public class Utils {
    /**
     * metoda care executa o actiune si prinde propriile exceptii
     * @param runnable - actiunea care va fi executata
     */
    public static void tryExecute(Runnable runnable) {
        try {
            runnable.run();
            System.out.println("Success!");
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
    }
}
