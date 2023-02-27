package org.example.Organizmy;

import org.example.Config;
import org.example.Nisza;

import java.util.List;
import java.util.Random;

public class Glon extends Organizm {
    private final int max_najedzenie = 3;
    private final int koszt_potomka = 2;
    private final int max_wiek;
    private final Nisza nisza;
    private int najedzenie = max_najedzenie - new Random().nextInt(0, max_najedzenie);
    private int wiek = 0;

    public Glon(int najedzenie, Nisza nisza, int wiek) {
        this.najedzenie = najedzenie;
        this.nisza = nisza;
        this.wiek = wiek;
        max_wiek = new Random().nextInt(5, Config.gl_max_wiek);
    }

    public Glon(Nisza nisza, int wiek) {
        this.nisza = nisza;
        this.wiek = wiek;
        max_wiek = new Random().nextInt(nisza.getPlansza().getKolumny(), nisza.getPlansza().getKolumny() * Config.gl_max_wiek);
    }

    @Override
    public void kolejnyKrok() {
        if (wiek >= max_wiek) {
            die(nisza);
        } else if (najedzenie < max_najedzenie) {
            najedzenie++;
        } else {
            List<Nisza> wolne = nisza.znajdzWolneSasiadujace();
            if (wolne.size() > 0) {
                int index = wolne.size() > 1 ? new Random().nextInt(0, wolne.size() - 1) : 0;
                Nisza n = wolne.get(index);
                n.setWnetrze(new Glon(0, n, 0));
                najedzenie -= koszt_potomka;
            }
        }
        wiek++;
    }

    @Override
    public String symbol() {
        return "*";
    }
}
