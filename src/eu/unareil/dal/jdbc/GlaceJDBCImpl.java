package eu.unareil.dal.jdbc;

import eu.unareil.bo.Glace;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GlaceJDBCImpl implements DAO<Glace> {
    private final static String SQL_INSERT_INTO = "INSERT INTO produit (dateLimiteConso,  marque,  libelle,  temperatureConservation,  parfum,  qteStock,  prixUnitaire, type) VALUES (?,?,?,?,?,?,?,?)";
    private final static String SQL_DELETE = "DELETE FROM produit WHERE refProd=?";
    private final static String SQL_UPDATE = "UPDATE produit SET libelle=?,marque=?,prixUnitaire=?,qteStock=?,dateLimiteConso=?,parfum=?,temperatureConservation=? WHERE refProd=?";
    private final static String SQL_SELECT_ALL = "SELECT * FROM produit WHERE type='Glace'";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM produit WHERE refProd=?";

    @Override
    public void insert(Glace data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            Date date = java.sql.Date.valueOf(data.getDateLimiteConso());
            preparedStatement.setDate(1, date);
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setString(3, data.getLibelle());
            preparedStatement.setInt(4, data.getTemperatureConservation());
            preparedStatement.setString(5, data.getParfum());
            preparedStatement.setLong(6, data.getQteStock());
            preparedStatement.setFloat(7, data.getPrixUnitaire());
            preparedStatement.setString(8, data.getClass().getSimpleName());
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
    public void delete(Glace data) throws DALException {
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
    public void update(Glace data) throws DALException {

        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {
            Date date = java.sql.Date.valueOf(data.getDateLimiteConso());
            preparedStatement.setDate(1, date);
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setString(3, data.getLibelle());
            preparedStatement.setInt(4, data.getTemperatureConservation());
            preparedStatement.setString(5, data.getParfum());
            preparedStatement.setLong(6, data.getQteStock());
            preparedStatement.setFloat(7, data.getPrixUnitaire());
            preparedStatement.setFloat(8, data.getReProd());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté", e);
        }
    }

    @Override
    public Glace selectById(long id) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Glace(resultSet.getLong("refProd"), resultSet.getDate("dateLimiteConso").toLocalDate(), resultSet.getString("marque"), resultSet.getString("libelle"), resultSet.getInt("temperatureConservation"), resultSet.getString("parfum"), resultSet.getLong("qteStock"), resultSet.getInt("prixUnitaire"));
            } else {
                throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
    }

    @Override
    public List<Glace> selectAll() throws DALException {
        List<Glace> glaces = new ArrayList<>();
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                glaces.add(new Glace(resultSet.getLong("refProd"), resultSet.getDate("dateLimiteConso").toLocalDate(), resultSet.getString("marque"), resultSet.getString("libelle"), resultSet.getInt("temperatureConservation"), resultSet.getString("parfum"), resultSet.getLong("qteStock"), resultSet.getInt("prixUnitaire")));
            }


        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été récupéré", e);
        }
        return glaces;
    }
}
