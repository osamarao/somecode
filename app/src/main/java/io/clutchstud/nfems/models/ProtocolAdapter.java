package io.clutchstud.nfems.models;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by a653h496 on 4/21/16.
 */
public class ProtocolAdapter {

    private Moshi moshi;

    public ProtocolAdapter(Moshi moshi){

        this.moshi = moshi;
    }

    @ToJson
    String toJson( ArrayList<Protocol>  protocol){
        return "";
    }

    @FromJson
    ArrayList<Protocol> fromJson(JsonReader reader) throws IOException{

        ArrayList<Protocol> protocols = new ArrayList<>();
        reader.beginArray();
        while (reader.peek() == JsonReader.Token.BEGIN_OBJECT){
           protocols.add(moshi.adapter(Protocol.class).fromJson(reader));
        }
        reader.endArray();

        return protocols;
    }
}
