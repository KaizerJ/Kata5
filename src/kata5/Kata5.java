package kata5;

public class Kata5 {

    public static void main(String[] args) {
        String connectString = "jdbc:sqlite:C:\\Users\\Usuario\\Documents\\IS2\\Kata5\\DB_SQLite\\Prueba.db";
        DataBase database = new DataBase(connectString);
        
        database.open();

        database.select_PERSONAS();
        
        Person person = new Person("Santiago","Perez","TIC");
        database.insert_PERSONAS(person);
        
        System.out.println("* * * * * *");
        
        database.select_PERSONAS();
        
        database.close();
    }
    
}
