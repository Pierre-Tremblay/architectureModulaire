package eu.unareil.dal.jdbc;

import eu.unareil.bo.Stylo;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StyloJDBCImpl implements DAO<Stylo> {
    private final static String SQL_INSERT_INTO = "INSERT INTO produit (libelle, marque, prixUnitaire, qteStock,couleur,typeMine,type)   VALUES (?,?,?,?,?,?,?)";
    private final static String SQL_DELETE = "DELETE FROM produit WHERE refProd=?";
    private final static String SQL_UPDATE = "UPDATE produit SET libelle=?,marque=?,prixUnitaire=?,qteStock=?,couleur=?,typeMine=? WHERE refProd=?";
    private final static String SQL_SELECT_ALL = "SELECT * FROM produit WHERE type='Stylo'";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM produit WHERE refProd=?";

    @Override
    public void insert(Stylo data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO, PreparedStatement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setFloat(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            preparedStatement.setString(5, data.getCouleur());
            preparedStatement.setString(6, data.getTypeMine());
            preparedStatement.setString(7, data.getClass().getSimpleName());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté");
            } else {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    data.setReProd(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté", e);
        }


    }

    @Override
    public void delete(Stylo data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {
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
    public void update(Stylo data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setFloat(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            preparedStatement.setString(5, data.getCouleur());
            preparedStatement.setString(6, data.getTypeMine());
            preparedStatement.setLong(7, data.getReProd());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été mis à jour");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été mis à jour", e);
        }

    }

    @Override
    public Stylo selectById(long id) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);

        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Stylo(resultSet.getLong("refProd"), resultSet.getString("libelle"), resultSet.getString("marque"), resultSet.getFloat("prixUnitaire"), resultSet.getLong("qteStock"), resultSet.getString("couleur"), resultSet.getString("typeMine"));

            } else {
                throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }

    }

    @Override
    public List<Stylo> selectAll() throws DALException {
        List<Stylo> stylos = new ArrayList<>();
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stylos.add(new Stylo(resultSet.getLong("refProd"), resultSet.getString("libelle"), resultSet.getString("marque"), resultSet.getFloat("prixUnitaire"), resultSet.getLong("qteStock"), resultSet.getString("couleur"), resultSet.getString("typeMine")));
            }

        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
        return stylos;
    }
}
