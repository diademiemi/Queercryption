package me.diademeimi.queercryption;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.StringReader;

import javax.json.*;

@Path("queercryption")
public class Queercryption {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String main(String string) {
        try (StringReader reader = new StringReader(string)) {
            Integer rounds;
            String body;
            JsonObject json = Json.createReader(reader).readObject();
            
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

            try {
                body = json.getString("body");
            } catch (Exception e) {
                body = "ERROR: No body parameter provided";
            }

            try { 
                rounds = json.getInt("rounds");
            } catch (NullPointerException e) {
                rounds = 10;
            }

            jsonBuilder.add(
                "body", 
                Queercrypt.queercrypt(body, rounds)
            );
            jsonBuilder.add("status", "ok");

            return jsonBuilder.build().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder().add("status", "error").build().toString();

        }
    }
}