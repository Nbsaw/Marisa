package com.nbsaw.marisa.server;

import com.nbsaw.marisa.exception.NotFoundException;
import com.nbsaw.marisa.http.Request;
import com.nbsaw.marisa.http.Response;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
class RouterHandler {

    public static boolean handlerRouter(Request request, Response response){
        String router =  request.getRouter();
        HashMap<Method,Object> routers = Server.getMap.get(router);
        if (routers != null){
            for(Method method : routers.keySet()){
                try {
                    method.invoke(method,request,response);
                } catch (Exception e){
                    return false;
                }
            }
        }
        else if (router.equals("/")){
            response.setHeader();
            response.setContent("Marisa moe !!");
        }
        return false;
    }

    public static boolean handlerFile(Request request, Response response){
        try {
            response.setStatic(request.getRouter());
            return false;
        } catch (NotFoundException e) {
            response.setError();
            log.error("Can not {} {}",request.getHeaders().get("method"),request.getHeaders().get("router"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
