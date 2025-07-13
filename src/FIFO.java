public class FIFO<T> {
    /**
     * Noeud pour stocker chaque élément de la file
     */
    private static class Node<T> {
        // Valeur stockée
        private T value;
        // Référence vers le nœud suivant
        private Node<T> next;

        // Constructeur du nœud
        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    // Premier élément de la file
    private Node<T> head;
    // Dernier élément de la file
    private Node<T> tail;
    // Nombre d'éléments dans la file
    private int size;

    /**
     * Constructeur : initialise une file vide
     */
    public FIFO() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Ajoute un élément à la fin de la file
     * @param item l'objet à ajouter
     */
    public void enqueue(T item) {
        // Créer un nouveau nœud
        Node<T> newNode = new Node<>(item);

        // Si la file n'est pas vide, lier l'ancien tail au nouveau
        if (tail != null) {
            tail.next = newNode;
        }

        // Mettre à jour tail
        tail = newNode;

        // Si la file était vide, head pointe aussi vers le nouveau nœud
        if (head == null) {
            head = newNode;
        }

        // Incrémenter la taille
        size++;
    }

    /**
     * Retire et renvoie l'élément en tête de la file
     * @return l'objet retiré ou null si la file est vide
     */
    public T dequeue() {
        // Si la file est vide, rien à retirer
        if (head == null) {
            return null;
        }

        // Récupérer la valeur du head
        T result = head.value;

        // Avancer head vers le nœud suivant
        head = head.next;

        // Si on a retiré le dernier élément, tail doit être null
        if (head == null) {
            tail = null;
        }

        // Décrémenter la taille
        size--;

        // Retourner la valeur retirée
        return result;
    }

    /**
     * Regarde l'élément en tête sans le retirer
     * @return l'objet en tête ou null si vide
     */
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    /**
     * Vérifie si la file est vide
     * @return true si vide, false sinon
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Renvoie le nombre d'éléments dans la file
     * @return taille de la file
     */
    public int size() {
        return size;
    }

    /**
     * Affiche tous les éléments de la file
     */
    public void printAll() {
        Node<T> current = head;
        System.out.print("File: [");
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}
