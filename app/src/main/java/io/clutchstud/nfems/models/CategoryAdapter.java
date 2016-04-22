package io.clutchstud.nfems.models;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by a653h496 on 4/20/16.
 */
public class CategoryAdapter {


    private final Moshi moshi;

    public CategoryAdapter(Moshi moshi) {
        this.moshi = moshi;
    }

    @ToJson
    String toJson(ArrayList<Category> categories) {
        return "";
    }


    @FromJson
    ArrayList<Category> fromJson(JsonReader reader) throws IOException {
        ArrayList<Category> categories = new ArrayList<>();
        reader.beginArray();
        while (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
            categories.add(moshi.adapter(Category.class).fromJson(reader));
        }
        reader.endArray();
        return categories;
    }
}
