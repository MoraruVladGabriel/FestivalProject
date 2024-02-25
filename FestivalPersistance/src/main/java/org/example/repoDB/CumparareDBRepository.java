package org.example.repoDB;

import com.example.festivalapp.domain.Casier;
import com.example.festivalapp.domain.Cumparare;
import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.repository.CasierRepository;
import com.example.festivalapp.repository.CumparareRepository;
import com.example.festivalapp.repository.SpectacolRepository;
import com.example.festivalapp.utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class CumparareDBRepository implements CumparareRepository {
    private JdbcUtils dbUtils;
    private CasierRepository casierRepo;
    private SpectacolRepository spectacolRepo;
    private static final Logger logger = LogManager.getLogger();

    public CumparareDBRepository(Properties props, CasierRepository casierRepo, SpectacolRepository spectacolRepo){
        logger.info("Initializing ArtistRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
        this.casierRepo = casierRepo;
        this.spectacolRepo = spectacolRepo;
    }
    @Override
    public Cumparare findById(Integer integer) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        Cumparare cumparare = new Cumparare();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Cumparari where id=?")){
            preparedStatement.setInt(1,integer);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String cumparator = resultSet.getString("cumparator");
                    int locuri = resultSet.getInt("locuri");
                    int idSpectacol = resultSet.getInt("spectacol");
                    int idCasier = resultSet.getInt("casier");
                    cumparare.setId(id);
                    cumparare.setCumparator(cumparator);
                    cumparare.setLocuri(locuri);
                    cumparare.setSpectacol(spectacolRepo.findById(idSpectacol));
                    cumparare.setCasier(casierRepo.findById(idCasier));
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit(cumparare);
        return cumparare;
    }

    @Override
    public Iterable<Cumparare> findAll() throws SQLException {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Cumparare> cumparari = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Cumparari")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String cumparator = resultSet.getString("cumparator");
                    int locuri = resultSet.getInt("locuri");
                    Spectacol spectacol = spectacolRepo.findById(resultSet.getInt("spectacol"));
                    Casier casier = casierRepo.findById(resultSet.getInt("casier"));
                    Cumparare cumparare = new Cumparare(cumparator,locuri,spectacol,casier);
                    cumparare.setId(id);
                    cumparari.add(cumparare);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(cumparari);
        return cumparari;
    }

    @Override
    public void save(Cumparare entity) throws SQLException {
        int ok = 0;
        for(Cumparare cumparare:findAll()){
            if (cumparare.equals(entity)){
                ok = 1;
                break;
            }
        }
        if (ok == 1){
            logger.error("The {} already exist",entity);
            System.err.println("The transaction already exist");
        }else {
            logger.traceEntry("Saving task {} ", entity);
            Connection con = dbUtils.getConnection();
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into Cumparari (cumparator,locuri,spectacol,casier) values (?,?,?,?)")) {
                preparedStatement.setString(1, entity.getCumparator());
                preparedStatement.setInt(2, entity.getLocuri());
                preparedStatement.setInt(3, entity.getSpectacol().getId());
                preparedStatement.setInt(4, entity.getCasier().getId());
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
    public void delete(Cumparare entity) {
        logger.traceEntry("Deleting task {} ",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("delete from Cumparari where id=?")) {
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
    public void update(Integer integer, Cumparare entity) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("update Cumparari set cumparator=?, locuri=?, spectacol=?, casier=? where id=?")){
            preparedStatement.setString(1,entity.getCumparator());
            preparedStatement.setInt(2,entity.getLocuri());
            preparedStatement.setInt(3,entity.getSpectacol().getId());
            preparedStatement.setInt(4,entity.getCasier().getId());
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
    public Collection<Cumparare> getAll() {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Cumparare> cumparari = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Cumparari")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String cumparator = resultSet.getString("cumparator");
                    int locuri = resultSet.getInt("locuri");
                    Spectacol spectacol = spectacolRepo.findById(resultSet.getInt("spectacol"));
                    Casier casier = casierRepo.findById(resultSet.getInt("casier"));
                    Cumparare cumparare = new Cumparare(cumparator,locuri,spectacol,casier);
                    cumparare.setId(id);
                    cumparari.add(cumparare);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(cumparari);
        return cumparari;
    }

    @Override
    public List<Cumparare> filterByCasier(Casier casierCautat) {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Cumparare> cumparari = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Cumparari where casier=?")){
            preparedStatement.setInt(1,casierCautat.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String cumparator = resultSet.getString("cumparator");
                    int locuri = resultSet.getInt("locuri");
                    Spectacol spectacol = spectacolRepo.findById(resultSet.getInt("spectacol"));
                    Casier casier = casierRepo.findById(resultSet.getInt("casier"));
                    Cumparare cumparare = new Cumparare(cumparator,locuri,spectacol,casier);
                    cumparare.setId(id);
                    cumparari.add(cumparare);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(cumparari);
        return cumparari;
    }

    @Override
    public List<Cumparare> filterBySpectacol(Spectacol spectacolCautat) {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Cumparare> cumparari = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Cumparari where spectacol=?")){
            preparedStatement.setInt(1,spectacolCautat.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String cumparator = resultSet.getString("cumparator");
                    int locuri = resultSet.getInt("locuri");
                    Spectacol spectacol = spectacolRepo.findById(resultSet.getInt("spectacol"));
                    Casier casier = casierRepo.findById(resultSet.getInt("casier"));
                    Cumparare cumparare = new Cumparare(cumparator,locuri,spectacol,casier);
                    cumparare.setId(id);
                    cumparari.add(cumparare);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(cumparari);
        return cumparari;
    }
}