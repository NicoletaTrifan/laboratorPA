import static java.lang.Integer.*;

import java.util.concurrent.TimeUnit;

public class Optional {
    static boolean verificareNumar(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    static boolean verificareSir(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.length() > 1 || !Character.isLetter(str.charAt(i))) return false;
        }
        return true;
    }

    static boolean verificareCuvinte(String words1, String words2) {
        for (int i = 0; i < words1.length(); i++) {
            for (int j = 0; j < words2.length(); j++) {
                if (words1.charAt(i) == words2.charAt(j)) return true;
            }
        }
        return false;
    }

    //bonus
    static void parcurgereDFS(boolean[][] adjacent, boolean[] vizitat, int n, int start) {
        vizitat[start] = true;
        System.out.print(start + " ");
        for (int i = 0; i < n; ++i) {
            if (adjacent[i][start] && !vizitat[i]) {
                vizitat[i] = true;
                parcurgereDFS(adjacent, vizitat, n, i);
            }
        }
    }

    static boolean verificaConex(boolean[][] adjacent, int n) {
        boolean[] vizitat = new boolean[n];
        for (int i = 0; i < n; i++) {
            vizitat[i] = false;
        }
        parcurgereDFS(adjacent, vizitat, n, 0);
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (!vizitat[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        int n = 0, k = 0, m = 0;
        if (args.length < 3) {
            System.out.println("Argumente insuficiente. Verificati linia de comanda!");
            System.exit(1);
        }
        if (verificareNumar(args[0])) {
            n = parseInt(args[0]);
        } else {
            System.out.println("Nu ati indicat corect parametrul pentru numarul de cuvinte");
            System.exit(1);
        }
        if (verificareNumar(args[1])) {
            k = parseInt(args[1]);
        } else {
            System.out.println("Nu ati indicat corect parametrul pentru lungimea cuvintelor ");
            System.exit(1);
        }
        char[] a = new char[256];
        for (int i = 2; i < args.length; i++) {
            if (verificareSir(args[i])) {
                a[m++] = args[i].charAt(0);
            } else {
                System.out.println("Nu ati indicat corect literele din alfabet");
                System.exit(1);
            }
        }
        String[] words = new String[n];
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                int pos = (int) (Math.random() * m);
                sb.append(a[pos]);
            }
            words[j] = sb.toString();
        }
        if (n < 30000) {
            for (int i = 0; i < words.length; i++) {
                System.out.println(words[i]);
            }
        }

        boolean[][] adjacent = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    adjacent[i][j] = false;
                } else adjacent[i][j] = verificareCuvinte(words[i], words[j]);
                if (n < 30000) {
                    System.out.print(adjacent[i][j] + " ");
                }
            }
            if (n < 30000) {
                System.out.println();
            }
        }
        System.out.println();
        int[] contorAdiacenta = new int[n];
        int[] contorAdiacenta1 = new int[n];
        int[] contorAdiacenta2 = new int[n];
        int minimAdiacenta = 0, contorRand = 0, maximAdiacenta = MAX_VALUE;
        int[] rand = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacent[i][j] == false) contorAdiacenta[i]++;
            }
            if (minimAdiacenta < contorAdiacenta[i]) {
                minimAdiacenta = contorAdiacenta[i];
            }
            if (maximAdiacenta > contorAdiacenta[i]) {
                maximAdiacenta = contorAdiacenta[i];
            }
        }
        if (n < 30000) {
            System.out.print("Cuvinte cel mai putin adiacente ");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacent[i][j] == false) contorAdiacenta1[i]++;
            }
            if (n < 30000) {
                if (minimAdiacenta == contorAdiacenta1[i]) {
                    System.out.print(words[i] + " ");
                }
            }
        }
        System.out.println();
        if (n < 30000) {
            System.out.print("Cuvinte cel mai mult adiacente ");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacent[i][j] == false) contorAdiacenta2[i]++;
            }
            if (n < 30000) {
                if (maximAdiacenta == contorAdiacenta2[i]) {
                    System.out.print(words[i] + " ");
                }
            }
        }
        System.out.println();
        if (verificaConex(adjacent, n)) {
            System.out.println();
            System.out.println("Graful este conex");
        } else {
            System.out.println();
            System.out.println("Graful nu este conex");
        }
        System.out.println();
        long endTime = System.nanoTime();
        long runningTime = endTime - startTime;
        System.out.println("Timpul de executie in nanosecunde " + runningTime);

    }
}
