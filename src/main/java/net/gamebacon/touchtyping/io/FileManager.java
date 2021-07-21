package net.gamebacon.touchtyping.io;

//import com.google.gson.Gson;
import net.gamebacon.touchtyping.Driver;
import net.gamebacon.touchtyping.util.Data;

import java.awt.*;
import java.io.*;

public class FileManager {

    //Gson gson = new Gson();
    File file;

    public FileManager() {
    }



    @Deprecated
    public Data loadData(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String json = "";
        String line = "";
        while((line = reader.readLine()) != null)
            json += line;

        Data data = null; //gson.fromJson(json, Data.class);
        reader.close();

        this.file = file;
        return data;
    }


    @Deprecated
    public void saveData(Data data) throws IOException {
        String json = ""; //gson.toJson(data);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(json);
        writer.close();
    }


}
