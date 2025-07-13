
import java.util.LinkedList;
import java.util.List;

/**
 * Gère la file d’attente des tickets.
 */
public class TicketService {
    private final LinkedList<Ticket> fileAttente = new LinkedList<>();
    private final List<Ticket> appelsEnCours = new LinkedList<>();

    // Créer un nouveau ticket et l’ajouter à la file
    public Ticket nouveauTicket() {
        Ticket t = new Ticket();
        fileAttente.addLast(t);
        return t;
    }

    // Appeler le prochain ticket dans la file, depuis une caisse spécifique
    public Ticket appelerSuivant(int caisseNumero) {
        if (fileAttente.isEmpty()) return null;

        Ticket prochain = fileAttente.removeFirst();
        Ticket appele = prochain.appelerPar(caisseNumero);
        appelsEnCours.add(appele);
        return appele;
    }

    public int getNombreEnAttente() {
        return fileAttente.size();
    }

    public List<Ticket> getFileAttente() {
        return new LinkedList<>(fileAttente);
    }

    public List<Ticket> getAppelsEnCours() {
        return appelsEnCours;
    }
}
