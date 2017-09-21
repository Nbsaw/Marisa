package morisa.test;

import com.nbsaw.marisa.annotation.Get;
import com.nbsaw.marisa.annotation.Path;
import com.nbsaw.marisa.exception.NotFoundException;
import com.nbsaw.marisa.http.Request;
import com.nbsaw.marisa.http.Response;

import java.io.IOException;

@Path("/marisa")
public class Qsa {
    @Get(value  = {"/test","/hello"})
    public void hello(Request request, Response response){
        response.setHeader();
        response.setContent(request.getRouter());
    }

    @Get(value  = {"/ttt"})
    public void lalala(Request request, Response response){
        response.setHeader();
        response.setContent("like express ???");
    }

    @Get(value  = {"/morisa"})
    public void morisa(Request request, Response response){
        response.setHeader();
        response.setContent("emmmm show marisa");
    }

    @Get(value = {"/pic"})
    public void  st(Request request, Response response) throws IOException, NotFoundException {
        response.setStatic("/test.jpg");
    }
}
