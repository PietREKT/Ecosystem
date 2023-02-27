package org.example;

import java.util.Scanner;

public class Main {
    //    public static int kolumny = 8, wiersze = 8;
    public static void main(String[] args) throws InterruptedException {
        Plansza plansza = new Plansza(8, 8);
        Scanner scanner = new Scanner(System.in);
//        System.out.print ("Podaj ilość wierszy: ");
//        wiersze = scanner.nextInt();
//        System.out.print("Podaj ilość kolumn: ");
//        kolumny = scanner.nextInt();
        System.out.println("""
                Wpisz:
                1. Jeśli chcesz aby symulacja działała automatycznie
                2. Jeśli chcesz robić to manualnie""");
        int wybor = scanner.nextInt();
        if (wybor == 1) {
            System.out.print("Podaj co ile sekund ma się uruchamiać kolejny krok symulacji: ");
            int s = scanner.nextInt();
            System.out.print("Podaj limit kroków symulacji: ");
            int lk = scanner.nextInt();
            System.out.println(plansza);
            Thread.sleep(s * 100L);
            for (int k = 0; k < lk - 1; k++) {
                System.out.println(plansza.kolejnyKrok());
                Thread.sleep(s * 100L);
            }
        } else {
            System.out.println(plansza);
            System.out.println("Wpisz \"kolejny\" aby przejść do następnego kroku symulacji!");
            while (scanner.nextLine().equals("kolejny")) {
                System.out.println(plansza.kolejnyKrok());
                System.out.println("Wpisz \"kolejny\" aby przejść do następnego kroku symulacji!");
            }
        }
    }
}