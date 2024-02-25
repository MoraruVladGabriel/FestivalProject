package org.example.repoDB;

import com.example.festivalapp.domain.Artist;
import com.example.festivalapp.repository.ArtistRepository;
import com.example.JdbcUtils;
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

public class ArtistDBRepository implements ArtistRepository {

    private JdbcUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public ArtistDBRepository(Properties props){
        logger.info("Initializing ArtistRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Artist findById(Integer integer) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        Artist artist = new Artist();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Artisti where id=?")){
            preparedStatement.setInt(1,integer);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String tip = resultSet.getString("tip");
                    artist.setId(id);
                    artist.setNume(nume);
                    artist.setTip(tip);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit(artist);
        return artist;
    }

    @Override
    public Iterable<Artist> findAll() throws SQLException {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Artist> artisti = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Artisti")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String tip = resultSet.getString("tip");
                    Artist artist = new Artist(nume,tip);
                    artist.setId(id);
                    artisti.add(artist);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(artisti);
        return artisti;
    }

    @Override
    public void save(Artist entity) throws SQLException {
        int ok = 0;
        for(Artist artist:findAll()){
            if (artist.equals(entity)){
                ok = 1;
                break;
            }
        }
        if (ok == 1){
            logger.error("The {} already exist",entity);
            System.err.println("The artist already exist");
        }else {
            logger.traceEntry("Saving task {} ", entity);
            Connection con = dbUtils.getConnection();
            try (PreparedStatement preparedStatement = con.prepareStatement("insert into Artisti (nume,tip) values (?,?)")) {
                preparedStatement.setString(1, entity.getNume());
                preparedStatement.setString(2, entity.getTip());
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
    public void delete(Artist entity) {
        logger.traceEntry("Deleting task {} ",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("delete from Artisti where id=?")) {
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
    public void update(Integer id, Artist entity) throws SQLException {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("update Artisti set nume=?, tip=? where id=?")){
            preparedStatement.setString(1,entity.getNume());
            preparedStatement.setString(2,entity.getTip());
            preparedStatement.setInt(3,id);
            int result = preparedStatement.executeUpdate();
            logger.trace("Updated {} instance ",result);
        }catch(SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public Collection<Artist> getAll() {
        logger.traceEntry();
        Connection con =dbUtils.getConnection();
        List<Artist> artisti = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Artisti")){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String tip = resultSet.getString("tip");
                    Artist artist = new Artist(nume,tip);
                    artist.setId(id);
                    artisti.add(artist);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB "+ e);
        }
        logger.traceExit(artisti);
        return artisti;
    }

    @Override
    public List<Artist> filterByTip(String tipCautat) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Artist> artisti = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Artisti where tip=?")){
            preparedStatement.setString(1,tipCautat);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String nume = resultSet.getString("nume");
                    String tip = resultSet.getString("tip");
                    Artist artist = new Artist(nume,tip);
                    artist.setId(id);
                    artisti.add(artist);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("ERROR DB " + e);
        }
        logger.traceExit(artisti);
        return artisti;
    }
}
