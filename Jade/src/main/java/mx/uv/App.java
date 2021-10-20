package mx.uv;

import static spark.Spark.*;
import com.google.gson.*;

import mx.uv.datos.Usuario;
import spark.ModelAndView;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class App 
{
    private static Gson gson = new Gson();
    private static Map<String, Usuario> usuarios = new HashMap<>();

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        staticFiles.location("/");


    }

    post("/usuario", (req, res) -> {
        String payload = req.body();
        String id = UUID.randomUUID().toString();
        Usuario u = gson.fromJson(payload, Usuario.class);
        u.setId(id);
        usuarios.put(id, u);

        JsonObject objetoJson = new JsonObject();
        objetoJson.addProperty("status", "ok");
        objetoJson.addProperty("id", id);
        return objetoJson;
    });
}
