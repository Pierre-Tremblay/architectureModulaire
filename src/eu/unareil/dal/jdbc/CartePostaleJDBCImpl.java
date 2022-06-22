package eu.unareil.dal.jdbc;

import eu.unareil.bo.Auteur;
import eu.unareil.bo.CartePostale;
import eu.unareil.bo.TypeCartePostale;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartePostaleJDBCImpl implements DAO<CartePostale> {
    private static final String SQL_INSERT = "INSERT INTO produit (libelle, marque, prixUnitaire, qteStock, typeCartePostale,type) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM produit WHERE refProd = ?";
    private static final String SQL_UPDATE = "UPDATE produit SET libelle = ?, marque = ?, prixUnitaire = ?, qteStock = ?, type = ? WHERE refProd = ?";
    private final static String SQL_SELECT_BY_ID = "SELECT p.refProd, p.libelle, p.marque, p.prixUnitaire, p.qteStock, p.typeCartePostale, a.nom, a.prenom FROM produit p, auteur a WHERE refProd IN (SELECT refCartePostale FROM auteur_cartePostale) AND p.refProd = ?";
    private final static String SQL_SELECT_ALL = "";

    @Override
    public void insert(CartePostale data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setDouble(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            preparedStatement.setString(5, data.getClass().getSimpleName());
            preparedStatement.setString(6, data.getType().toString());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté");
            } else {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    data.setReProd(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté", e);
        }
    }

    @Override
    public void delete(CartePostale data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, data.getReProd());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été suppprimé");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été supprimé", e);
        }
    }

    @Override
    public void update(CartePostale data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setDouble(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            preparedStatement.setLong(5, data.getReProd());
            preparedStatement.setString(6, data.getType().toString());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été mis à jour");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été mis à jour", e);
        }
    }

    @Override
    public CartePostale selectById(long id) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);

        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CartePostale cartePostale = new CartePostale();
                TypeCartePostale enumVal = TypeCartePostale.valueOf(resultSet.getString("typeCartePostale"));
                List<Auteur> auteurs = new ArrayList<>();
                cartePostale.setReProd(resultSet.getLong("refProd"));
                cartePostale.setLibelle(resultSet.getString("libelle"));
                cartePostale.setMarque(resultSet.getString("marque"));
                cartePostale.setPrixUnitaire(resultSet.getFloat("prixUnitaire"));
                cartePostale.setQteStock(resultSet.getLong("qteStock"));
                cartePostale.setType(enumVal);

                Auteur auteur = new Auteur(resultSet.getString("nom"), resultSet.getString("prenom"));
                auteurs.add(auteur);

                while (resultSet.next()) {
                    auteurs.add(new Auteur(resultSet.getString("nom"), resultSet.getString("prenom")));
                }
                cartePostale.setLesAuteurs(auteurs);
                return cartePostale;
            } else {
                throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
    }

    @Override
    public List<CartePostale> selectAll() throws DALException {
        // Je liste les cartes postales
        List<CartePostale> cartesPostales = new ArrayList<>();
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
// J'instancie une carte postale
                CartePostale cartePostale = new CartePostale();
//J'instancie une liste des auteurs
                List<Auteur> auteurs = new ArrayList<>();
                TypeCartePostale enumVal = TypeCartePostale.valueOf(resultSet.getString("typeCartePostale"));
                // Je set les attribut de Carte Postales
                cartePostale.setReProd(resultSet.getLong("refProd"));
                cartePostale.setLibelle(resultSet.getString("libelle"));
                cartePostale.setMarque(resultSet.getString("marque"));
                cartePostale.setPrixUnitaire(resultSet.getFloat("prixUnitaire"));
                cartePostale.setQteStock(resultSet.getLong("qteStock"));
                cartePostale.setType(enumVal);
                // Je set la list des auteurs dans carte postale
                cartePostale.setLesAuteurs(auteurs);

                cartesPostales.add(cartePostale);
            }


        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
        return cartesPostales;
    }
}
