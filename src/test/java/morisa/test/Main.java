package morisa.test;

import com.nbsaw.marisa.annotation.Get;
import com.nbsaw.marisa.http.Request;
import com.nbsaw.marisa.http.Response;

public class Main {
    @Get(value = "test")
    public void test(Request request, Response response){
        response.setHeader();
        response.setContent("fuck test");
    }

//    @Get(value = "/")
//    public void index(Request request, Response response){
//        response.setHeader();
//        response.setContent("Marisa Moe !!!");
//    }
}
