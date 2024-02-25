package chat.services.rest;

import model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.ArtistRepository;
import repo.RepositoryException;
import repo.repoDB.ArtistDBRepository;
import repo.repoInMemory.ArtistInMemRepository;

import java.sql.SQLException;
import java.util.Properties;

@RestController
@RequestMapping("/chat/artists")
public class ChatArtistController {
    private static final String template = "Hello, %s!";

    @Autowired
    private ArtistInMemRepository artistRepository ;

    @RequestMapping("/greeting")
    public  String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @RequestMapping( method= RequestMethod.GET)
    public Artist[] getAll(){
        System.out.println("Get all users ...");
        return artistRepository.getAll().toArray(new Artist[0]);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String id) throws SQLException {
        System.out.println("Get by id "+id);
        Artist user=artistRepository.findById(Integer.valueOf(id));
        if (user==null)
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Artist>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Artist create(@RequestBody Artist user) throws SQLException {
        artistRepository.save(user);
        return user;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Artist update(@RequestBody Artist user) throws SQLException {
        System.out.println("Updating user ...");
        artistRepository.update(user.getId(),user);
        return user;

    }
    // @CrossOrigin(origins = "http://localhost:3000")
//    @RequestMapping(value="/{username}", method= RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@PathVariable String username){
//        System.out.println("Deleting user ... "+username);
//        try {
//            artistRepository.delete(username);
//            return new ResponseEntity<Artist>(HttpStatus.OK);
//        }catch (RepositoryException ex){
//            System.out.println("Ctrl Delete user exception");
//            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }


//    @RequestMapping("/{user}/name")
//    public String name(@PathVariable String user){
//        Artist result=artistRepository.findBy(user);
//        System.out.println("Result ..."+result);
//
//        return result.getNume();
//    }



    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(RepositoryException e) {
        return e.getMessage();
    }

}
