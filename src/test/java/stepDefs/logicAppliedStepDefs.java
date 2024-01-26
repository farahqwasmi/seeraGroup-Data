package stepDefs;

import io.cucumber.java.en.And;

import java.sql.ResultSet;
import java.sql.SQLException;

import static helpers.dbConnection.*;
import static helpers.fileTransfer.*;
import static helpers.query.*;
import static org.testng.AssertJUnit.assertEquals;


public class logicAppliedStepDefs {

    @And("enter all of the data  from l1 file into a DB")
    public void addTable() {
        moveBeforeLogicFileToDB(System.getProperty("user.dir") + "/src/main/resources/files/beforeLogic.csv");
    }

    @And("apply logic")
    public void applyLogic() {
      selectData("CREATE TABLE after AS SELECT order_type , dim_group_id,order_no,dim_bookingdate_id," +
                "dim_store_id," +
                "NULL AS service_fee_code," +
                "dim_customer_id," +
                "dim_language," +
                "dim_totals_currency," +
                "dim_status_id," +
                "phone," +
                "payment_amount," +
                "discount_amount," +
                "NULL AS service_fee_amount," +
                "base_amount," +
                "inputvat," +
                "outputvat," +
                "product_vat," +
                "selling_price," +
                "selling_price_vat," +
                "ibv," +
                "ibv * conversion_rate_usd AS iov_usd," +
                "gbv," +
                "gbv * conversion_rate_usd AS gbv_usd ," +
                "product_name,product_type " +
                " FROM beforeLogic" +
                " WHERE" +
                "  product_type = order_type ;");



                try {
            dropDBConnection();
            connectToDB();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        selectData("UPDATE after set service_fee_code = product_name WHERE product_type = 'rule'");

        try {
            dropDBConnection();
            connectToDB();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        selectData("CREATE TABLE afterLogic AS\n" +
                "SELECT \n" +
                "order_type,\n" +
                "                    dim_group_id, \n" +
                "                    order_no,\n" +
                "                    dim_bookingdate_id,\n" +
                "                    dim_store_id,\n" +
                "                   service_fee_code,\n" +
                "                    dim_customer_id,\n" +
                "                 dim_language,\n" +
                "                    dim_totals_currency,\n" +
                "                   dim_status_id,\n" +
                "                   phone,\n" +
                "                   payment_amount,\n" +
                "                   discount_amount,\n" +
                "                  NULL AS service_fee_amount,\n" +
                "                   base_amount,\n" +
                "                   inputvat,\n" +
                "                   outputvat,\n" +
                "                   product_vat,\n" +
                "                   selling_price,\n" +
                "                  selling_price_vat,\n" +
                "                   ibv,\n" +
                "                    iov_usd\n" +
                "                   gbv,\n" +
                "                gbv_usd \n" +
                "FROM after" +
                "INTO OUTFILE '/src/main/resources/files/result.csv'\n" +

                "FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\n" +
                "LINES TERMINATED BY '\\n';");


    }

    @And("make sure that result and afterLogic are same")
    public void comparetables() throws SQLException {

        moveAfterLogicFileToDB(System.getProperty("user.dir") + "/src/main/resources/files/afterLogic.csv");

       ResultSet resultSet= selectData("SELECT * FROM afterLogic\n" +
               "WHERE NOT EXISTS (\n" +
               "    SELECT * FROM result\n" +
               "    WHERE result.order_type = afterLogic.order_type\n" +
               ");"); // to check if the two tables are the same

        assertEquals(resultSet.getRow(), 0);


    }


}
