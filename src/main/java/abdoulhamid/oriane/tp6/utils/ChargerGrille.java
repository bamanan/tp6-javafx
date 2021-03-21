package abdoulhamid.oriane.tp6.utils;

import abdoulhamid.oriane.tp6.beans.MotsCroisesTP6;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChargerGrille {
    private final Connection connection;


    public ChargerGrille() {
        connection = connecterBD();
    }

    public static Connection connecterBD() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/TP_PRGA_2020", "istic", "Istic@@2021");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public Map<Integer, String> grillesDisponibles() {
        String query = "SELECT * FROM TP5_GRILLE";
        Map<Integer, String> grilles = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery(query);
            while (rs.next()) {
                int num = rs.getInt("num_grille");
                String name = rs.getString("nom_grille") + "(" + rs.getString("hauteur") + "x" + rs.getString("largeur") + ")";
                grilles.put(num, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grilles;
    }

    public MotsCroisesTP6 extraireGrille(int numGrille) {
        MotsCroisesTP6 motsCroises = null;

        String query = "SELECT hauteur, largeur, ligne, colonne, horizontal, solution, definition FROM `TP5_GRILLE`, `TP5_MOT` " +
                " WHERE TP5_MOT.num_grille = TP5_GRILLE.num_grille " +
                " AND TP5_MOT.num_grille =? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, numGrille);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                motsCroises = new MotsCroisesTP6(rs.getInt("hauteur"), rs.getInt("largeur"));
                extractGrid(motsCroises, rs);
            }
            while (rs.next()) {
                assert motsCroises != null;
                extractGrid(motsCroises, rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return motsCroises;
    }

    private void extractGrid(MotsCroisesTP6 motsCroises, ResultSet rs) throws SQLException {
        int lig = rs.getInt("ligne");
        int col = rs.getInt("colonne");
        String sol = rs.getString("solution");
        boolean isHorizontal = rs.getBoolean("horizontal");
        String definition = rs.getString("definition");

        motsCroises.setSolution(lig, col, sol.charAt(0));
        motsCroises.setDefinition(lig, col, isHorizontal, definition);
    }

    public static void main(String... args) {
        ChargerGrille chargerGrille = new ChargerGrille();
        System.out.println(chargerGrille.grillesDisponibles());
        System.out.println(chargerGrille.extraireGrille(9));
    }

}
