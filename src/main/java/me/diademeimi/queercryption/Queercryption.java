package me.diademeimi.queercryption;

import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.StringReader;

import javax.json.*;

@Path("queercryption")
public class Queercryption {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String queercryption(String string) {
        try (StringReader reader = new StringReader(string)) {
            Integer rounds;
            String body;
            JsonObject json = Json.createReader(reader).readObject();
            
            JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

            try {
                body = json.getString("body");
            } catch (Exception e) {
                jsonBuilder.add("body", Queercrypt.queercrypt("ERROR: No body parameter provided. body is required. rounds is an optional parameter", 1));
                jsonBuilder.add("status", "error");
                return jsonBuilder.build().toString();
            }

            try { 
                rounds = json.getInt("rounds");
                if (rounds > 50) {
                    jsonBuilder.add("notice", "Too many rounds. Max is 50");
                    rounds = 50;
                } else if (rounds < 1) {
                    jsonBuilder.add("notice", "Too few rounds. Min is 1");
                    rounds = 1;
                }
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