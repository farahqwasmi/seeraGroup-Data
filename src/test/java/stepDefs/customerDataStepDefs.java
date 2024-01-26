package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.ResultSet;
import java.sql.SQLException;

import static helpers.dbConnection.*;
import static helpers.fileTransfer.*;
import static helpers.query.selectData;
import static org.testng.AssertJUnit.assertEquals;

public class customerDataStepDefs {
    @When("DB connected")
    public void dbConnect() {
        connectToDB();
    }

    @And("enter all of the data  from customer file into a DB")
    public void addTable() {
        moveCustomerFileToDB(System.getProperty("user.dir") + "/src/main/resources/files/customer.csv");
    }

    @And("make sure that data is valid")
    public void validateData() throws SQLException {
        ResultSet resultSet;
        //Check for NULL in Required Fields
        resultSet = selectData("select * from customerData where id is null or first_name is null or last_name is null or address is null or city is null");
        assertEquals(resultSet.getRow(), 0); // we need to make sure that there is no records

        //Check for Valid Email Format

        resultSet = selectData("SELECT * FROM `customerData` WHERE email IS NOT NULL AND email NOT LIKE '%@%' AND email NOT LIKE '';");
        assertEquals(resultSet.getRow(), 0);

        //Check for Valid Birthdate Format
        resultSet = selectData("SELECT * FROM `customerData` WHERE birthday IS NOT null AND birthday NOT REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}$';");
        assertEquals(resultSet.getRow(), 0);

        //Check for Invalid Phone Numbers
        resultSet = selectData("SELECT * FROM `customerData` WHERE phone IS NOT NULL AND phone NOT REGEXP('^[0-9]{3}-[0-9]{3}-[0-9]{4}$') AND phone NOT LIKE '';");
        assertEquals(resultSet.getRow(), 0);

        //Check for Duplicate Entries:
        resultSet = selectData("SELECT * FROM `customerData` WHERE (first_name, last_Name, address, city, state, zip_code, phone, email, birthday) IN (SELECT first_name, last_Name, address, city, state, zip_code, phone, email, birthday FROM `customerData` GROUP BY first_name, last_Name, address, city, state, zip_code, phone, email, birthday HAVING COUNT(*) > 1);");
        assertEquals(resultSet.getRow(), 0);
    }

    @Then("drop connection")
    public void dropConnection() {

        dropDBConnection();
    }
}
