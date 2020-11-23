package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private static IntJoukko A, B, C;

    private static String luku() {
        Scanner lukija = new Scanner(System.in);
        String syote = lukija.nextLine();
        return syote;
    }

    private static IntJoukko mikaJoukko() {
        String syote;
        syote = luku();
        while (true) {
            if (syote.equals("A") || syote.equals("a")) {
                return A;
            } else if (syote.equals("B") || syote.equals("b")) {
                return B;
            } else if (syote.equals("C") || syote.equals("c")) {
                return C;
            } else {
                System.out.println("Virheellinen joukko! " + syote);
                System.out.print("Yritä uudelleen!");
                syote = luku();
            }
        }
    }

    private static void lisaa() {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        int syote = lukija.nextInt();
        joukko.lisaa(syote);
    }

    private static void yhdiste() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko c = IntJoukko.yhdiste(aJoukko, bJoukko);
        System.out.println("A yhdiste B = " + c.toString());
    }

    private static void leikkaus() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko c = IntJoukko.leikkaus(aJoukko, bJoukko);
        System.out.println("A leikkaus B = " + c.toString());
    }

    private static void erotus() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko c = IntJoukko.erotus(aJoukko, bJoukko);
        System.out.println("A erotus B = " + c.toString());
    }

    private static void poista() {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mistä joukosta? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku poistetaan? ");
        int syote = lukija.nextInt();
        joukko.poista(syote);
    }

    private static void kuuluu() {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku? ");
        int syote = lukija.nextInt();
        boolean kuuluuko = joukko.kuuluu(syote);
        if (kuuluuko) {
            System.out.println(syote + " kuuluu joukkoon ");
        } else {
            System.out.println(syote + " ei kuulu joukkoon ");
        }
        return;
    }

    public static void main(String[] args) {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();
        String syote;

        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

        Scanner lukija = new Scanner(System.in);
        while (true) {
            syote = lukija.nextLine();
            if (syote.equals("lisää") || syote.equals("li")) {
                lisaa();
            } else if (syote.equalsIgnoreCase("poista") || syote.equalsIgnoreCase("p")) {
                poista();
            } else if (syote.equalsIgnoreCase("kuuluu") || syote.equalsIgnoreCase("k")) {
                kuuluu();
            } else if (syote.equalsIgnoreCase("yhdiste") || syote.equalsIgnoreCase("y")) {
                yhdiste();
            } else if (syote.equalsIgnoreCase("leikkaus") || syote.equalsIgnoreCase("le")) {
                leikkaus();
            } else if (syote.equalsIgnoreCase("erotus") || syote.equalsIgnoreCase("e")) {
                erotus();
            } else if (syote.equalsIgnoreCase("A")) {
                System.out.println(A);
            } else if (syote.equalsIgnoreCase("B")) {
                System.out.println(B);
            } else if (syote.equalsIgnoreCase("C")) {
                System.out.println(C);
            } else if (syote.equalsIgnoreCase("lopeta") || syote.equalsIgnoreCase("quit") || syote.equalsIgnoreCase("q")) {
                System.out.println("Lopetetaan, moikka!");
                break;
            } else {
                System.out.println("Virheellinen komento! " + syote);
                System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
            }
            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
        }
    }
}
