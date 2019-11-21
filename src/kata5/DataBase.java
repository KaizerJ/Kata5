package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private final String connectString;
    private Connection connection = null;

    public DataBase(String connectString) {
        this.connectString = connectString;
    }

    void open() {
        try {
            this.connection = DriverManager.getConnection(connectString);
            System.out.println("Base de Datos abierta ...");
        } catch (SQLException exception) {
            System.out.println("ERROR DataBase::open (SQLExcepton) " + exception.getMessage());
        }
    }

    void close() {
        if(this.connection != null)
            try {
                this.connection.close();
                System.out.println("Base de Datos cerrada.");
        } catch (SQLException exception) {
            System.out.println("ERROR DataBase::close (SQLExcepton) " + exception.getMessage());
        }
    }

    void select_PERSONAS() {
        String SQL = "SELECT * FROM PERSONAS";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultset = statement.executeQuery(SQL);
            
            System.out.println("ID \t NOMBRE \t APELLIDOS \t DEPARTAMENTO");
            System.out.println("-- \t ------ \t --------- \t ------------");
            while(resultset.next()){
                System.out.println(resultset.getInt("ID") + " \t " +
                                   resultset.getString("Name") + " \t " +
                                   resultset.getString("APELLIDOS") + " \t " +
                                   resultset.getString("DEPARTAMENTO"));
            }
                
            
        } catch (SQLException exception) {
            System.out.println("ERROR DataBase::select_PERSONAS (SQLExcepton) " + exception.getMessage());
        }
    }

    void insert_PERSONAS(Person person) {
        String SQL = "INSERT INTO PERSONAS(NAME, APELLIDOS, DEPARTAMENTO) VALUES(?,?,?)";
        
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getDepartment());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("ERROR DataBase::insert_PERSONAS (SQLExcepton) " + exception.getMessage());
        }
    }
    
    
}
