package model;

/**
 * Classe LignePanier - représente une ligne dans le panier
 * (un produit + sa quantité + son prix unitaire)
 * Conforme au diagramme UML du projet ChriOnline
 */
public class LignePanier {

    // Attributs privés (- dans le UML)
    private int quantite;
    private double prixUnitaire;

    // Référence vers le produit associé
    private Produit produit;

    // Constructeur
    public LignePanier(Produit produit, int quantite, double prixUnitaire) {
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    // =====================
    //   Méthodes publiques (+)
    // =====================

    /**
     * Calcule le sous-total de cette ligne.
     * Sous-total = quantité × prix unitaire
     * @return le sous-total de la ligne
     */
    public double calculerSousTotal() {
        return quantite * prixUnitaire;
    }

    // =====================
    //   Getters & Setters
    // =====================

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    @Override
    public String toString() {
        return produit.getNom()
                + " x" + quantite
                + " @ " + String.format("%.2f", prixUnitaire) + " DH"
                + " = " + String.format("%.2f", calculerSousTotal()) + " DH";
    }
}
