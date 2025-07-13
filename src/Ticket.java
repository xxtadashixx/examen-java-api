

import java.util.Random;

public class Ticket {
    private final String code;     // Exemple : A025
    private final long timestamp;  // Date de création du ticket
    private final int numeroAppel; // N° de caisse qui l'appelle (0 = pas encore appelé)

    public Ticket() {
        this.code = genererCode();
        this.timestamp = System.currentTimeMillis();
        this.numeroAppel = 0;
    }

    public Ticket(String code, long timestamp, int numeroAppel) {
        this.code = code;
        this.timestamp = timestamp;
        this.numeroAppel = numeroAppel;
    }

    private String genererCode() {  // Génère un code aléatoire de la forme A025
        // Code aléatoire : une lettre (A, B ou C) suivie de 3 chiffres (000 à 999)
        Random rand = new Random();
        char lettre = (char) ('A' + rand.nextInt(3)); // A, B ou C
        int numero = rand.nextInt(1000);              // entre 0 et 999
        return String.format("%c%03d", lettre, numero);
    }

    public String getCode() {
        return code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNumeroAppel() {
        return numeroAppel;
    }

    // Permet de créer une copie du ticket avec n° de caisse
    public Ticket appelerPar(int caisse) {
        return new Ticket(this.code, this.timestamp, caisse);
    }
}
