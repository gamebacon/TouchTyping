package net.gamebacon.touchtyping.io;

//import com.google.gson.Gson;
import net.gamebacon.touchtyping.Driver;
import net.gamebacon.touchtyping.util.Data;

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IOManager {

    DataBase dataBase = new DataBase();

    public IOManager() {
        dataBase.connect("localhost", "3306", "test123", "baconwilliam", "qazwsxedc");
    }



    @Deprecated
    public Data loadData() throws IOException, SQLException {
        Data data = new Data();
        if(dataBase.isConnected()) {
            Statement statement = dataBase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from touchtyping");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                char c = resultSet.getString("char").charAt(0);
                int count = resultSet.getInt("count");

                data.getWrongTyped().put(c, count);
            }
        }
        return data;
    }


    @Deprecated
    public void saveData(Data data) throws IOException, SQLException {
        if(dataBase.isConnected()) {
            Statement statement = dataBase.getConnection().createStatement();
            //statement.executeUpdate(String.format("UPDATE videopoker SET money = %d WHERE id = 1", money));
        }
    }


}
