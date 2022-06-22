package eu.unareil.dal.jdbc;

import eu.unareil.bo.Auteur;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuteurJDBCImpl implements DAO<Auteur> {
    private final static String SQL_INSERT_INTO = "INSERT INTO auteur (nom, prenom) VALUES (?, ?)";
    private final static String SQL_DELETE = "DELETE FROM auteur WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE auteur SET nom = ?, prenom = ? WHERE id = ?";

    @Override
    public void insert(Auteur data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO, PreparedStatement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, data.getPrenom());
            preparedStatement.setString(2, data.getNom());

            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté");
            } else {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    data.setIdAuteur(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été ajouté", e);
        }
    }

    @Override
    public void delete(Auteur data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);) {
            preparedStatement.setLong(1, data.getIdAuteur());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été supprimé");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été supprimé", e);
        }
    }

    @Override
    public void update(Auteur data) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);) {
            preparedStatement.setString(1, data.getPrenom());
            preparedStatement.setString(2, data.getNom());
            preparedStatement.setFloat(3, data.getIdAuteur());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new DALException("Une erreur est survenue aucune ligne n'a été mis à jour");
            }
        } catch (SQLException e) {
            throw new DALException("Une erreur est survenue aucune ligne n'a été mis à jour", e);
        }
    }

    @Override
    public Auteur selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<Auteur> selectAll() throws DALException {
        return null;
    }
}
