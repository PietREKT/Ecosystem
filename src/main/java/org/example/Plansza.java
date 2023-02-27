package org.example;

import org.example.Organizmy.Bakteria;
import org.example.Organizmy.Glon;
import org.example.Organizmy.Grzyb;
import org.example.Organizmy.Organizm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private final int wiersze;
    private final int kolumny;
    private final HashMap<Integer, List<Nisza>> nisze = new HashMap<>();
    int krok = 1;

    public Plansza(int wiersze, int kolumny) {
        this.wiersze = wiersze;
        this.kolumny = kolumny;
        for (int w = 0; w < wiersze; w++) {
            List<Nisza> n = new LinkedList<>();
            for (int k = 0; k < kolumny; k++) {
                n.add(new Nisza(w, k, this));
            }
            nisze.put(w, n);
        }
        for (int i = 0; i < Config.osobniki; i++) {
            //Generuj glona
            {
                int w = new Random().nextInt(0, wiersze), k = new Random().nextInt(0, kolumny);
                do {
                    w = new Random().nextInt(0, wiersze);
                    k = new Random().nextInt(0, kolumny);

                } while (!populate(w, k, new Glon(nisze.get(w).get(k), 1)));
            }
            //Generuj grzyba
            {
                int w = new Random().nextInt(0, wiersze), k = new Random().nextInt(0, kolumny);
                do {
                    w = new Random().nextInt(0, wiersze);
                    k = new Random().nextInt(0, kolumny);

                } while (!populate(w, k, new Grzyb(nisze.get(w).get(k), 1)));
            }
            //Generuj bakterie
            {
                int w = new Random().nextInt(0, wiersze), k = new Random().nextInt(0, kolumny);
                do {
                    w = new Random().nextInt(0, wiersze);
                    k = new Random().nextInt(0, kolumny);

                } while (!populate(w, k, new Bakteria(nisze.get(w).get(k), 1)));
            }
        }
    }

    public HashMap<Integer, List<Nisza>> getNisze() {
        return nisze;
    }

    public int getWiersze() {
        return wiersze;
    }

    public int getKolumny() {
        return kolumny;
    }

    public String kolejnyKrok() {
        krok++;
        nisze.forEach((k, v) -> {
            v.forEach(n -> {
                if (n.getWnetrze() != null)
                    n.getWnetrze().kolejnyKrok();
            });
        });
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Krok: ").append(krok).append("\n");
        for (int w = 0; w < wiersze; w++) {
            stringBuilder.append("| ");
            for (int k = 0; k < kolumny; k++) {
                Nisza n = nisze.get(w).get(k);
                stringBuilder.append(n.symbol()).append(" | ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("Glony (*): ").append(stringBuilder.toString().chars().filter(c -> (c == '*')).count() - 1).append("\n");
        stringBuilder.append("Bakterie (@): ").append(stringBuilder.toString().chars().filter(c -> (c == '@')).count() - 1).append("\n");
        stringBuilder.append("Grzyby (#): ").append(stringBuilder.toString().chars().filter(c -> (c == '#')).count() - 1).append("\n");
        stringBuilder.append("Martwe (+): ").append(stringBuilder.toString().chars().filter(c -> (c == '+')).count() - 1).append("\n");
        return stringBuilder.toString();
    }

    private boolean populate(int wiersz, int kolumna, Organizm o) {
        Nisza n = nisze.get(wiersz).get(kolumna);
        if (n.getWnetrze() != null) return false;
        else n.setWnetrze(o);
        return true;
    }
}
