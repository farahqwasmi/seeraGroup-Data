package helpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static helpers.dbConnection.getMyConnection;

public class query {

    public static ResultSet selectData(String query) {
        ResultSet resultSet;
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = getMyConnection().prepareStatement(query);
            if (preparedStatement.getFetchSize() != 0)
                resultSet = preparedStatement.executeQuery();
            else

                resultSet = null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public void dropTable() {


    }
}
