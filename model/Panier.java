package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Panier - représente le panier d'un utilisateur
 * Conforme au diagramme UML du projet ChriOnline
 */
public class Panier {

    // Attributs privés (- dans le UML)
    private int id;
    private double total;

    // Liste des lignes du panier (relation 1 -> * avec LignePanier)
    private List<LignePanier> lignes;

    // Constructeur
    public Panier(int id) {
        this.id = id;
        this.total = 0.0;
        this.lignes = new ArrayList<>();
    }

    // =====================
    //   Méthodes publiques (+)
    // =====================

    /**
     * Ajoute un produit dans le panier.
     * Si le produit existe déjà, augmente la quantité.
     * @param produit   le produit à ajouter
     * @param quantite  la quantité souhaitée
     */
    public void ajouterProduit(Produit produit, int quantite) {
        // Vérifier si le produit est déjà dans le panier
        for (LignePanier ligne : lignes) {
            if (ligne.getProduit().getId() == produit.getId()) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                calculerTotal();
                return;
            }
        }
        // Sinon, créer une nouvelle ligne
        LignePanier nouvelleLigne = new LignePanier(produit, quantite, produit.getPrix());
        lignes.add(nouvelleLigne);
        calculerTotal();
    }

    /**
     * Supprime un produit du panier selon son id.
     * @param produitId  l'id du produit à supprimer
     */
    public void supprimerProduit(int produitId) {
        lignes.removeIf(ligne -> ligne.getProduit().getId() == produitId);
        calculerTotal();
    }

    /**
     * Calcule et met à jour le total du panier.
     * @return le total calculé
     */
    public double calculerTotal() {
        total = 0.0;
        for (LignePanier ligne : lignes) {
            total += ligne.calculerSousTotal();
        }
        return total;
    }

    /**
     * Vide complètement le panier.
     */
    public void viderPanier() {
        lignes.clear();
        total = 0.0;
    }

    // =====================
    //   Getters & Setters
    // =====================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getTotal() { return total; }

    public List<LignePanier> getLignes() { return lignes; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Panier #").append(id).append(" ===\n");
        if (lignes.isEmpty()) {
            sb.append("  (panier vide)\n");
        } else {
            for (LignePanier ligne : lignes) {
                sb.append("  ").append(ligne).append("\n");
            }
        }
        sb.append("  TOTAL : ").append(String.format("%.2f", total)).append(" DH\n");
        return sb.toString();
    }
}
