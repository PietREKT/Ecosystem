package org.example;

import org.example.Organizmy.Organizm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Nisza {
    private final int wiersz;
    private final int kolumna;
    private final Plansza plansza;
    private Organizm wnetrze;

    protected Nisza(int wiersz, int kolumna, Plansza plansza) {
        this.wiersz = wiersz;
        this.kolumna = kolumna;
        this.plansza = plansza;
    }

    public Organizm getWnetrze() {
        return wnetrze;
    }

    public void setWnetrze(Organizm wnetrze) {
        this.wnetrze = wnetrze;
    }

    public Plansza getPlansza() {
        return plansza;
    }

    public String symbol() {
        if (wnetrze != null) {
            return wnetrze.symbol();
        }
        return " ";
    }

    public List<Nisza> znajdzWolneSasiadujace() {
        return znajdzSasiadujaceSymbol(" ");
    }

    public List<Nisza> znajdzMartweSasiadujace() {
        return znajdzSasiadujaceSymbol("+");
    }

    public List<Nisza> znajdzGlonySasiadujace() {
        return znajdzSasiadujaceSymbol("*");
    }

    public List<Nisza> znajdzBakterieSasiadujace() {
        return znajdzSasiadujaceSymbol("@");
    }

    private List<Nisza> znajdzSasiadujaceSymbol(String symbol) {
        HashMap<Integer, List<Nisza>> nisze = plansza.getNisze();
        List<Nisza> sasiadujaceSymbolem = new LinkedList<>();
        for (int w = -1; w < 2; w++) {
            for (int k = -1; k < 2; k++) {
                int wi = wiersz + w, ko = kolumna + k;
                if (wi < 0 || ko < 0 || ko >= plansza.getKolumny() || wi >= plansza.getWiersze()) continue;
                if (wi == wiersz && ko == kolumna) continue;
                if (nisze.get(wi) == null || nisze.get(wi).get(ko) == null) continue;
                if (nisze.get(wi).get(ko).symbol().equals(symbol))
                    sasiadujaceSymbolem.add(nisze.get(wi).get(ko));
            }
        }
        return sasiadujaceSymbolem;
    }
}
