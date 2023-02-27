package org.example.Organizmy;

import org.example.Config;
import org.example.Nisza;

import java.util.List;
import java.util.Random;

public class Bakteria extends Organizm {
    private final int max_najedzenie = 1;
    private final int koszt_potomka = 1;
    private final int max_wiek;
    private Nisza nisza;
    private int najedzenie = max_najedzenie - new Random().nextInt(0, max_najedzenie);
    private int wiek = 0;

    public Bakteria(int najedzenie, Nisza nisza, int wiek) {
        this.najedzenie = najedzenie;
        this.nisza = nisza;
        this.wiek = wiek;
        max_wiek = new Random().nextInt(nisza.getPlansza().getKolumny() * 2, nisza.getPlansza().getKolumny() * Config.b_max_wiek);
    }

    public Bakteria(Nisza nisza, int wiek) {
        this.nisza = nisza;
        this.wiek = wiek;
        max_wiek = new Random().nextInt(nisza.getPlansza().getKolumny() * 2, nisza.getPlansza().getKolumny() * Config.b_max_wiek);
    }

    @Override
    public void kolejnyKrok() {
        if (wiek >= max_wiek) {
            die(nisza);
        } else if (najedzenie < max_najedzenie) {
            List<Nisza> glony = nisza.znajdzGlonySasiadujace();
            Nisza n;
            if (glony.size() == 0) {
                List<Nisza> bakterie = nisza.znajdzBakterieSasiadujace();
                if (bakterie.size() == 0) {
                    List<Nisza> wolne = nisza.znajdzWolneSasiadujace();
                    int index = wolne.size() > 1 ? new Random().nextInt(0, wolne.size() - 1) : 0;
                    n = wolne.get(index);
                } else {
                    int index = bakterie.size() > 1 ? new Random().nextInt(0, bakterie.size() - 1) : 0;
                    n = bakterie.get(index);
                }
            } else {
                int index = glony.size() > 1 ? new Random().nextInt(0, glony.size() - 1) : 0;
                n = glony.get(index);
            }
            n.setWnetrze(this);
            nisza.setWnetrze(null);
            nisza = n;
        } else {
            List<Nisza> wolne = nisza.znajdzWolneSasiadujace();
            if (wolne.size() == 0) return;
            int index = wolne.size() > 1 ? new Random().nextInt(0, wolne.size() - 1) : 0;
            Nisza n = wolne.get(index);
            n.setWnetrze(new Bakteria(0, n, 0));
            najedzenie -= koszt_potomka;
        }
        wiek++;
    }

    @Override
    public String symbol() {
        return "@";
    }
}
