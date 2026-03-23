package model;

/**
 * Classe Produit - entité produit du catalogue
 * Conforme au diagramme UML du projet ChriOnline
 */
public class Produit {

    // Attributs privés
    private int id;
    private String nom;
    private String description;
    private double prix;
    private int stock;

    // Constructeur
    public Produit(int id, String nom, String description, double prix, int stock) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }

    /**
     * Vérifie si le produit est disponible en quantité suffisante.
     * @param qte quantité demandée
     * @return true si stock >= qte
     */
    public boolean estDisponible(int qte) {
        return stock >= qte;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "[" + id + "] " + nom + " - " + String.format("%.2f", prix) + " DH (stock: " + stock + ")";
    }
}
