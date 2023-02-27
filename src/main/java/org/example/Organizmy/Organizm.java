package org.example.Organizmy;

import org.example.Nisza;

public abstract class Organizm {

    public static void die(Nisza nisza) {
        nisza.setWnetrze(new Martwy());
    }

    public abstract void kolejnyKrok();

    public abstract String symbol();
}
