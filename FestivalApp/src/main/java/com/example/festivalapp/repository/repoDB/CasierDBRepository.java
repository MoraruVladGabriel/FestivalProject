package com.example.festivalapp.repository.repoDB;

import com.example.festivalapp.domain.Casier;
import com.example.festivalapp.repository.CasierRepository;
import com.example.festivalapp.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.festivalapp.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class CasierDBRepository implements CasierRepository {
    private JdbcUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public CasierDBRepository(Properties props){
        logger.info("Initializing ArtistRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Casier findById(Integer integer) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        Casier casier = new Casier();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Casieri where id=?")){
            preparedStatement.setInt(1,integer);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String parola = resultSet.getString("parola");
                    String email = resultSet.getString("email");
                    String oficiu = resultSet.getString("oficiu");
                    casier.setId(id);
                    casier.setNume(nume);
                    casier.setParola(parola);
                    casier.setEmail(email);
                    casier.setOficiu(oficiu);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit(casier);
        return casier;
    }

    @Override
    public Iterable<Casier> findAll() throws SQLException {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Casier> casieri = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Casieri")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String parola = resultSet.getString("parola");
                    String email = resultSet.getString("email");
                    String oficiu = resultSet.getString("oficiu");
                    Casier casier = new Casier(nume,parola,email,oficiu);
                    casier.setId(id);
                    casieri.add(casier);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(casieri);
        return casieri;
    }

    @Override
    public void save(Casier entity) throws SQLException {
        int ok = 0;
        for(Casier casier:findAll()){
            if (casier.equals(entity)){
                ok = 1;
                break;
            }
        }
        if (ok == 1){
            logger.error("The {} already exist",entity);
            System.err.println("The locker already exist");
        }else {
            logger.traceEntry("Saving task {} ", entity);
            Connection con = dbUtils.getConnection();
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into Casieri (nume,parola,email,oficiu) values (?,?,?,?)")) {
                preparedStatement.setString(1, entity.getNume());
                preparedStatement.setString(2, entity.getParola());
                preparedStatement.setString(3, entity.getEmail());
                preparedStatement.setString(4, entity.getOficiu());
                int result = preparedStatement.executeUpdate();
                logger.trace("Saved {} instance ", result);
            } catch (SQLException e) {
                logger.error(e);
                System.err.println("ERROR DB " + e);
            }
            logger.traceExit();
        }

    }

    @Override
    public void delete(Casier entity) {
        logger.traceEntry("Deleting task {} ",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("delete from Casieri where id=?")) {
            preparedStatement.setInt(1,entity.getId());
            int result = preparedStatement.executeUpdate();
            logger.trace("Deleted {} instance ", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Casier entity) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("update Casieri set nume=?, parola=?, email=?, oficiu=? where id=?")){
            preparedStatement.setString(1,entity.getNume());
            preparedStatement.setString(2,entity.getParola());
            preparedStatement.setString(3,entity.getEmail());
            preparedStatement.setString(4,entity.getOficiu());
            preparedStatement.setInt(5,integer);
            int result = preparedStatement.executeUpdate();
            logger.trace("Updated {} instance ",result);
        }catch(SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public Collection<Casier> getAll() {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Casier> casieri = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Casieri")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String parola = resultSet.getString("parola");
                    String email = resultSet.getString("email");
                    String oficiu = resultSet.getString("oficiu");
                    Casier casier = new Casier(nume,parola,email,oficiu);
                    casier.setId(id);
                    casieri.add(casier);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(casieri);
        return casieri;
    }

    @Override
    public List<Casier> filterByOficiu(String oficiuCautat) {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Casier> casieri = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Casieri where oficiu=?")){
            preparedStatement.setString(1,oficiuCautat);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String parola = resultSet.getString("parola");
                    String email = resultSet.getString("email");
                    String oficiu = resultSet.getString("oficiu");
                    Casier casier = new Casier(nume,parola,email,oficiu);
                    casier.setId(id);
                    casieri.add(casier);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(casieri);
        return casieri;
    }

    @Override
    public Casier getCasierByEmail(String emailCautat) {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        Casier casier = new Casier();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Casieri where email=?")){
            preparedStatement.setString(1,emailCautat);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String parola = resultSet.getString("parola");
                    String email = resultSet.getString("email");
                    String oficiu = resultSet.getString("oficiu");
                    casier.setId(id);
                    casier.setNume(nume);
                    casier.setParola(parola);
                    casier.setEmail(email);
                    casier.setOficiu(oficiu);
                    return casier;
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(casier);
        return null;
    }
}
