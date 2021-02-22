package nl.kwasikattah;

public class App
{
    public static void main(String[] args) {

        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.Database();

        Home home = new Home();
        home.HomepageChoices();


    }

}
