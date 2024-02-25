package com.example.festivalapp.repository.repoDB;

import com.example.festivalapp.domain.Artist;
import com.example.festivalapp.domain.Spectacol;
import com.example.festivalapp.repository.ArtistRepository;
import com.example.festivalapp.repository.Repository;
import com.example.festivalapp.repository.SpectacolRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.festivalapp.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class SpectacolDBRepository implements SpectacolRepository {
    private JdbcUtils dbUtils;
    private ArtistRepository artistRepo;
    private static final Logger logger = LogManager.getLogger();

    public SpectacolDBRepository(Properties props, ArtistRepository artistRepo){
        logger.info("Initializing ArtistRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
        this.artistRepo = artistRepo;
    }
    @Override
    public Spectacol findById(Integer integer) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        Spectacol spectacol = new Spectacol();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Spectacole where id=?")){
            preparedStatement.setInt(1,integer);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    Artist artist = artistRepo.findById(resultSet.getInt("artist"));
                    LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("data"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    String locatie = resultSet.getString("locatie");
                    int locuriDisponibile = resultSet.getInt("locuriDisponibile");
                    int locuriVandute = resultSet.getInt("locuriVandute");
                    spectacol.setId(id);
                    spectacol.setNume(nume);
                    spectacol.setArtist(artist);
                    spectacol.setData(dateTime);
                    spectacol.setLocatie(locatie);
                    spectacol.setLocuriDisponibile(locuriDisponibile);
                    spectacol.setLocuriVandute(locuriVandute);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit(spectacol);
        return spectacol;
    }

    @Override
    public Iterable<Spectacol> findAll() throws SQLException {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Spectacol> spectacole = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Spectacole")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    Artist artist = artistRepo.findById(resultSet.getInt("artist"));
                    LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("data"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    String locatie = resultSet.getString("locatie");
                    int locuriDisponibile = resultSet.getInt("locuriDisponibile");
                    int locuriVandute = resultSet.getInt("locuriVandute");
                    Spectacol spectacol = new Spectacol(nume,artist,dateTime,locatie,locuriDisponibile,locuriVandute);
                    spectacol.setId(id);
                    spectacole.add(spectacol);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(spectacole);
        return spectacole;
    }

    @Override
    public void save(Spectacol entity) throws SQLException {
        int ok = 0;
        for(Spectacol spectacol:findAll()){
            if (spectacol.equals(entity)){
                ok = 1;
                break;
            }
        }
        if (ok == 1){
            logger.error("The {} already exist",entity);
            System.err.println("The show already exist");
        }else {
            logger.traceEntry("Saving task {} ", entity);
            Connection con = dbUtils.getConnection();
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into Spectacole (nume,artist,data,locatie,locuriDisponibile,locuriVandute) values (?,?,?,?,?,?)")) {
                preparedStatement.setString(1, entity.getNume());
                preparedStatement.setInt(2, entity.getArtist().getId());
                preparedStatement.setString(3,entity.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                preparedStatement.setString(4,entity.getLocatie());
                preparedStatement.setInt(5,entity.getLocuriDisponibile());
                preparedStatement.setInt(6,entity.getLocuriVandute());
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
    public void delete(Spectacol entity) {
        logger.traceEntry("Deleting task {} ",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("delete from Spectacole where id=?")) {
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
    public void update(Integer integer, Spectacol entity) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("update Spectacole set nume=?, artist=?, data=?, locatie=?, locuriDisponibile=?, locuriVandute=? where id=?")){
            preparedStatement.setString(1,entity.getNume());
            preparedStatement.setInt(2,entity.getArtist().getId());
            preparedStatement.setString(3,entity.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            preparedStatement.setString(4,entity.getLocatie());
            preparedStatement.setInt(5,entity.getLocuriDisponibile());
            preparedStatement.setInt(6,entity.getLocuriVandute());
            preparedStatement.setInt(7,integer);
            int result = preparedStatement.executeUpdate();
            logger.trace("Updated {} instance ",result);
        }catch(SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public Collection<Spectacol> getAll() {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Spectacol> spectacole = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Spectacole")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    Artist artist = artistRepo.findById(resultSet.getInt("artist"));
                    LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("data"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    String locatie = resultSet.getString("locatie");
                    int locuriDisponibile = resultSet.getInt("locuriDisponibile");
                    int locuriVandute = resultSet.getInt("locuriVandute");
                    Spectacol spectacol = new Spectacol(nume,artist,dateTime,locatie,locuriDisponibile,locuriVandute);
                    spectacol.setId(id);
                    spectacole.add(spectacol);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(spectacole);
        return spectacole;
    }

    @Override
    public List<Spectacol> filterByDay(LocalDateTime data) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Spectacol> spectacole = new ArrayList<>();
        String dataString = data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Spectacole where data=?")){
            preparedStatement.setString(1,dataString);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    Artist artist = artistRepo.findById(resultSet.getInt("artist"));
                    LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("data"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    String locatie = resultSet.getString("locatie");
                    int locuriDisponibile = resultSet.getInt("locuriDisponibile");
                    int locuriVandute = resultSet.getInt("locuriVandute");
                    Spectacol spectacol = new Spectacol(nume,artist,dateTime,locatie,locuriDisponibile,locuriVandute);
                    spectacol.setId(id);
                    spectacole.add(spectacol);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(spectacole);
        return spectacole;
    }

//    @Override
//    public void updateLocuri(Integer id, int locuriDisponibileNoi, int locuriVanduteNoi) {
//        logger.traceEntry();
//        Connection con = dbUtils.getConnection();
//        try(PreparedStatement preparedStatement = con.prepareStatement("update Spectacole set locuriDisponibile=?, locuriVandute=? where id=?")){
//            preparedStatement.setString(1,entity.getNume());
//            preparedStatement.setInt(2,entity.getArtist().getId());
//            preparedStatement.setString(3,entity.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
//            preparedStatement.setString(4,entity.getLocatie());
//            preparedStatement.setInt(5,entity.getLocuriDisponibile());
//            preparedStatement.setInt(6,entity.getLocuriVandute());
//            int result = preparedStatement.executeUpdate();
//            logger.trace("Updated {} instance ",result);
//        }catch(SQLException e){
//            logger.error(e);
//            System.err.println("ERROR DB " + e);
//        }
//        logger.traceExit();
//    }
}
