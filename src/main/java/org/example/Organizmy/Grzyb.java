package org.example.Organizmy;

import org.example.Config;
import org.example.Nisza;

import java.util.List;
import java.util.Random;

public class Grzyb extends Organizm {
    private final int max_najedzenie = 5;
    private final int koszt_potomka = 2;
    private final int max_wiek;
    private int najedzenie = max_najedzenie - new Random().nextInt(0, max_najedzenie);
    private int wiek = 0;
    private Nisza nisza;


    public Grzyb(int najedzenie, Nisza nisza, int wiek) {
        this.najedzenie = najedzenie;
        this.nisza = nisza;
        this.wiek = wiek;
        max_wiek = new Random().nextInt(nisza.getPlansza().getKolumny() * 2, nisza.getPlansza().getKolumny() * Config.gr_max_wiek);
    }

    public Grzyb(Nisza nisza, int wiek) {
        this.nisza = nisza;
        this.wiek = wiek;
        max_wiek = new Random().nextInt(nisza.getPlansza().getKolumny() * 2, nisza.getPlansza().getKolumny() * Config.gr_max_wiek);
    }

    @Override
    public void kolejnyKrok() {
        if (wiek >= max_wiek) {
            die(nisza);
            nisza = null;
        } else if (najedzenie <= max_najedzenie) {
            List<Nisza> martwe = nisza.znajdzMartweSasiadujace();
            Nisza n;
            if (martwe.size() > 0) {
                int index = martwe.size() > 1 ? new Random().nextInt(0, martwe.size() - 1) : 0;
                n = martwe.get(index);
            } else {
                List<Nisza> wolne = nisza.znajdzWolneSasiadujace();
                int index = wolne.size() > 1 ? new Random().nextInt(0, wolne.size() - 1) : 0;
                n = wolne.get(index);
            }
            n.setWnetrze(this);
            nisza.setWnetrze(null);
            nisza = n;
        } else {
            List<Nisza> wolne = nisza.znajdzWolneSasiadujace();
            if (wolne.size() > 0) {
                int index = wolne.size() > 1 ? new Random().nextInt(0, wolne.size() - 1) : 0;
                Nisza n = wolne.get(index);
                n.setWnetrze(new Grzyb(0, n, 0));
                najedzenie -= koszt_potomka;
            }
        }
        wiek++;
    }

    @Override
    public String symbol() {
        return "#";
    }
}
