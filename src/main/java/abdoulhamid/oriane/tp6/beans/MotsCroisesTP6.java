package abdoulhamid.oriane.tp6.beans;

import abdoulhamid.oriane.tp6.utils.SpecifMotsCroises;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MotsCroisesTP6 implements SpecifMotsCroises {
    public Grille<Character> solution;
    private Grille<StringProperty> proposition;
    private Grille<String> horizontal;
    private Grille<String> vertical;

    public MotsCroisesTP6(int hauteur, int largeur) {
        solution = new Grille<>(hauteur, largeur);
        proposition = new Grille<>(hauteur, largeur);
        horizontal = new Grille<>(hauteur, largeur);
        vertical = new Grille<>(hauteur, largeur);
        for (int lig = 1; lig <= getHauteur(); lig++) {
            for (int col = 1; col <= getLargeur(); col++) {
                setCaseNoire(lig, col, true);
                this.setProposition(lig, col, ' ');
            }
        }
    }

    public int getHauteur() {
        return solution.getHauteur();
    }

    public int getLargeur() {
        return solution.getLargeur();
    }

    public boolean coordCorrectes(int lig, int col) {
        return 1 <= lig && lig <= getHauteur()
                && 1 <= col && col <= getLargeur();
    }

    public boolean estCaseNoire(int lig, int col) {
        assert coordCorrectes(lig, col);
        return (solution.getCellule(lig, col) == null);
    }

    public void setCaseNoire(int lig, int col, boolean noire) {
        assert coordCorrectes(lig, col);
        if (noire) {
            solution.setCellule(lig, col, null);
        } else if (solution.getCellule(lig, col) == null) {
            solution.setCellule(lig, col, ' ');
        }
    }

    public char getSolution(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        return solution.getCellule(lig, col);
    }

    public void setSolution(int lig, int col, char sol) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        solution.setCellule(lig, col, sol);
    }

    public char getProposition(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);

        return proposition.getCellule(lig, col).get().charAt(0);
    }

    public void setProposition(int lig, int col, char prop) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        if (this.propositionProperty(lig, col) != null) {
            this.propositionProperty(lig, col).setValue(String.valueOf(prop));
        } else {
            this.proposition.setCellule(lig, col, new SimpleStringProperty(String.valueOf(prop)));
        }
    }

    public String getDefinition(int lig, int col, boolean isHorizontal) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        if (isHorizontal) {
            return horizontal.getCellule(lig, col);
        }
        return vertical.getCellule(lig, col);
    }

    public void setDefinition(int lig, int col, boolean isHorizontal, String def) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);
        if (isHorizontal) {
            horizontal.setCellule(lig, col, def);
        } else {
            vertical.setCellule(lig, col, def);
        }
    }

    /**
     * retournant la StringProperty située dans la case indiquée, au lieu de la valeur
     * encapsulée dans celle-ci
     *
     * @param lig
     * @param col
     * @return
     */
    public StringProperty propositionProperty(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);

        return proposition.getCellule(lig, col);
    }

    public void reveler(int lig, int col) {
        assert coordCorrectes(lig, col);
        assert !estCaseNoire(lig, col);

        this.setProposition(lig, col,  this.getSolution(lig, col));
    }

    @Override
    public String toString() {
        return "Solution\n" + solution
                + "\nProposition\n" + proposition
                + "\nHorizontal\n" + horizontal
                + "\nVertical\n" + vertical;
    }

}
