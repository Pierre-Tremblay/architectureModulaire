package eu.unareil.dal.jdbc;

import eu.unareil.bo.Produit;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitJDBCImpl implements DAO<Produit> {
    private static final String SQL_INSERT_INTO = "INSERT INTO produit (libelle, marque, prixUnitaire, qteStock, type) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM produit WHERE refProd = ?";
    private static final String SQL_UPDATE = "UPDATE produit SET libelle = ?, marque = ?, prixUnitaire = ?, qteStock = ?, type = ? WHERE refProd = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM produit WHERE refProd = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM produit";

    @Override
    public void insert(Produit data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setDouble(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            preparedStatement.setString(5, data.getClass().getSimpleName());
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
    public void delete(Produit data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, data.getReProd());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été supprimé");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été supprimé", e);
        }
    }

    @Override
    public void update(Produit data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setDouble(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            preparedStatement.setString(5, data.getClass().getSimpleName());
            preparedStatement.setLong(6, data.getReProd());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté", e);
        }
    }

    @Override
    public Produit selectById(long id) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Produit(resultSet.getLong("refProd"), resultSet.getString("libelle"), resultSet.getString("marque"), resultSet.getFloat("prixUnitaire"), resultSet.getLong("qteStock"));
            } else {
                throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
    }

    @Override
    public List<Produit> selectAll() throws DALException {
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = JdbcTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produits.add(new Produit(resultSet.getLong("refProd"), resultSet.getString("libelle"), resultSet.getString("marque"), resultSet.getFloat("prixUnitaire"), resultSet.getLong("qteStock")));
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
        return produits;
    }
}

