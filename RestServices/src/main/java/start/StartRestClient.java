package start;

import chat.services.rest.ServiceException;
import model.Artist;
import org.springframework.web.client.RestClientException;
import rest.client.ArtistsClient;

public class StartRestClient {
    private final static ArtistsClient artistsClient=new ArtistsClient();
    public static void main(String[] args) {
        //  RestTemplate restTemplate=new RestTemplate();
        Artist userT=new Artist("test","test");
        try{
            //  User result= restTemplate.postForObject("http://localhost:8080/chat/users",userT, User.class);

            //  System.out.println("Result received "+result);
      /*  System.out.println("Updating  user ..."+userT);
        userT.setName("New name 2");
        restTemplate.put("http://localhost:8080/chat/users/test124", userT);

*/
            // System.out.println(restTemplate.postForObject("http://localhost:8080/chat/users",userT, User.class));
            //System.out.println( restTemplate.postForObject("http://localhost:8080/chat/users",userT, User.class));

            show(()-> System.out.println(artistsClient.create(userT)));
            show(()->{
                Artist[] res=artistsClient.getAll();
                for(Artist u:res){
                    System.out.println(u.getId()+": "+u.getNume());
                }
            });
        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }

        show(()-> System.out.println(artistsClient.getById("ana")));
    }



    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            //  LOG.error("Service exception", e);
            System.out.println("Service exception"+ e);
        }
    }
}
