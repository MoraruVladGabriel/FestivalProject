package rest.client;

import chat.services.rest.ServiceException;
import model.Artist;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class ArtistsClient {

    public static final String URL = "http://localhost:8080/chat/artists";
    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    public Artist[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Artist[].class));
    }

    public Artist getById(String id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Artist.class));
    }

    public Artist create(Artist user) {
        return execute(() -> restTemplate.postForObject(URL, user, Artist.class));
    }

    public void update(Artist user) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, user.getId()), user);
            return null;
        });
    }

    public void delete(String id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }
}
