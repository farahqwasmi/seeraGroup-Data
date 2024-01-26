package helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static helpers.dbConnection.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class fileTransfer {

    public static void moveCustomerFileToDB(String filePath) {
        String sql = "insert into customerData (id,first_name ,last_name,address,city,state,zip_code , phone ,email,birthday) values(?,?,?,?,?,?,?,?,?,?)";
        //String sql2="delete from 'customerData'";
        try {
            PreparedStatement statement = getMyConnection().prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");
                if (data.length >= 10) {
                    String id = data[0];
                    String first_name = data[1];
                    String last_name = data[2];
                    String address = data[3];
                    String city = data[4];
                    String state = data[5];
                    String zip_code = data[6];
                    String phone = data[7];
                    String email = data[8];
                    // Parsing the date manually
                    String birthdayString = data[9];
                    Date birthday = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date parsedDate = dateFormat.parse(birthdayString);
                        birthday = new Date(parsedDate.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    statement.setInt(1, parseInt(id));
                    statement.setString(2, first_name);
                    statement.setString(3, last_name);
                    statement.setString(4, address);
                    statement.setString(5, city);
                    statement.setString(6, state);
                    if (zip_code != "")
                        statement.setInt(7, parseInt(zip_code));
                    else
                        statement.setNull(7, java.sql.Types.NULL);

                    statement.setString(8, phone);
                    statement.setString(9, email);
                    statement.setDate(10, new java.sql.Date(birthday.getTime()));
                    statement.addBatch();
                    statement.executeBatch();

                } else {
                    // Handle the case where the array does not have enough elements
                    System.err.println("Skipping line due to insufficient data: " + lineText);
                }
            }
            lineReader.close();
            statement.executeBatch();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
//L1 table
    public static void moveBeforeLogicFileToDB(String filePath) {
        String sql = "insert into beforeLogic (order_type,product_type,dim_group_id,order_no,dim_bookingdate_id,dim_store_id,ahs_group_name,product_name,product_code,dim_customer_id,dim_language,dim_totals_currency,dim_status_id,phone,payment_amount,discount_amount,base_amount,inputvat,outputvat,product_vat,selling_price,selling_price_vat,conversion_rate_sar,conversion_rate_usd,ibv,gbv) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //String sql2="delete from 'customerData'";
        try {
            PreparedStatement statement = getMyConnection().prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");
                if (data.length >= 26) {
                String order_type = data[0];
                String product_type = data[1];
                String dim_group_id = data[2];
                String order_no = data[3];
                String dim_bookingdate_id = data[4];
                String dim_store_id = data[5];
                String ahs_group_name = data[6];
                String product_name = data[7];
                String product_code = data[8];
                String dim_customer_id = data[9];

                String dim_language = data[10];
                String dim_totals_currency = data[11];
                String dim_status_id = data[12];
                String phone = data[13];
                String payment_amount = data[14];
                String discount_amount = data[15];
                String base_amount = data[16];
                String inputvat = data[17];
                String outputvat = data[18];
                String product_vat = data[19];

                String selling_price = data[20];
                String selling_price_vat = data[21];
                String conversion_rate_sar = data[22];
                String conversion_rate_usd = data[23];
                String ibv = data[24];
                String gbv = data[25];


                statement.setString(1, order_type);
                statement.setString(2, product_type);
                statement.setInt(3, parseInt(dim_group_id));
                statement.setString(4, order_no);
                statement.setInt(5, parseInt(dim_bookingdate_id));
                statement.setInt(6, parseInt(dim_store_id));
                statement.setString(7, ahs_group_name);
                statement.setString(8, product_name);
                statement.setString(9, product_code);
                statement.setString(10, dim_customer_id);
                statement.setString(11, dim_language);
                statement.setString(12, dim_totals_currency);
                statement.setString(13, dim_status_id);
                statement.setString(14, phone);
                statement.setString(15, payment_amount);
                statement.setFloat(16, Float.parseFloat(discount_amount));
                statement.setFloat(17, Float.parseFloat(base_amount));
                statement.setFloat(18, Float.parseFloat(inputvat));
                statement.setFloat(19, Float.parseFloat(outputvat));
                statement.setFloat(20, Float.parseFloat(product_vat));
                statement.setFloat(21, Float.parseFloat(selling_price));
                statement.setFloat(22, Float.parseFloat(selling_price_vat));
                statement.setFloat(23, Float.parseFloat(conversion_rate_sar));
                statement.setFloat(24, Float.parseFloat(conversion_rate_usd));
                statement.setFloat(25, Float.parseFloat(ibv));
                statement.setFloat(26, Float.parseFloat(gbv));
                }
                statement.addBatch();
                statement.executeBatch();


            }
            lineReader.close();
            statement.executeBatch();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void moveAfterLogicFileToDB(String filePath) {
        String sql = "insert into afterLogic (order_type,product_type,dim_group_id,order_no,dim_bookingdate_id,dim_store_id,service_fee_code,product_code,dim_customer_id,dim_language,dim_totals_currency,dim_status_id,phone,payment_amount,discount_amount,service_fee_amount,base_amount,inputvat,outputvat,product_vat,selling_price,selling_price_vat,ibv ,iov_usd,gbv,gbv_usd) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //String sql2="delete from 'customerData'";
        try {
            PreparedStatement statement = getMyConnection().prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",");
                if (data.length >= 26) {
                    String order_type = data[0];
                    String product_type = data[1];
                    String dim_group_id = data[2];

                    String order_no = data[3];
                    String dim_bookingdate_id = data[4];
                    String dim_store_id = data[5];

                    String service_fee_code = data[6];

                    String product_code = data[7];
                    String dim_customer_id = data[8];
                    String dim_language = data[9];
                    String dim_totals_currency = data[10];

                    String dim_status_id = data[11];
                    String phone = data[12];
                    String payment_amount = data[13];
                    String discount_amount = data[14];

                    String  service_fee_amount  = data[15];

                    String base_amount = data[16];
                    String inputvat = data[17];
                    String outputvat = data[18];
                    String product_vat = data[19];

                    String selling_price = data[20];
                    String selling_price_vat = data[21];

                    String ibv = data[24];
                    String ibv_usd = data[23];
                    String gbv = data[25];
                    String gbv_usd = data[23];


                    statement.setString(1, order_type);
                    statement.setString(2, product_type);
                    statement.setInt(3, parseInt(dim_group_id));
                    statement.setString(4, order_no);
                    statement.setInt(5, parseInt(dim_bookingdate_id));
                    statement.setInt(6, parseInt(dim_store_id));

                    statement.setString(7, service_fee_code);

                    statement.setString(8, product_code);
                    statement.setString(9, dim_customer_id);
                    statement.setString(10, dim_language);
                    statement.setString(11,dim_totals_currency);
                    statement.setString(12, dim_status_id);
                    statement.setString(13, phone);


                    statement.setFloat(14, Float.parseFloat(payment_amount));
                    statement.setFloat(15, Float.parseFloat(discount_amount));
                    statement.setFloat(16, Float.parseFloat(service_fee_amount));

                    statement.setFloat(17, Float.parseFloat(base_amount));
                    statement.setFloat(18, Float.parseFloat(inputvat));
                    statement.setFloat(19, Float.parseFloat(outputvat));
                    statement.setFloat(20, Float.parseFloat(product_vat));

                    statement.setFloat(21, Float.parseFloat(selling_price));
                    statement.setFloat(22, Float.parseFloat(selling_price_vat));

                    statement.setFloat(23, Float.parseFloat(ibv));
                    statement.setFloat(24, Float.parseFloat(ibv_usd));
                    statement.setFloat(25, Float.parseFloat(gbv));
                    statement.setFloat(26, Float.parseFloat(gbv_usd));
                }
                statement.addBatch();
                statement.executeBatch();


            }
            lineReader.close();
            statement.executeBatch();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
