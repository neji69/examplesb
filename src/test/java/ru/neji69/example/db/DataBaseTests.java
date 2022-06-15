package ru.neji69.example.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseTests {

    public static final String jdbcURL = "jdbc:postgresql://localhost:5432/demo";
    public static final String username = "postgres";
    public static final String password = "1";

    Connection connection;

    @Test
    void dataBaseTest() throws SQLException {


        List<String> actualResultList = new ArrayList<>();

        List<String> expectedDataList = new ArrayList<>();
        expectedDataList.add("ВнуковоМосква");
        expectedDataList.add("ДомодедовоМосква");
        expectedDataList.add("БаратаевкаУльяновск");
        expectedDataList.add("ШереметьевоМосква");
        expectedDataList.add("Ульяновск-ВосточныйУльяновск");

        String sql = "SELECT   a.airport_name,\n" +
                "         a.city\n" +
                "FROM     bookings.airports a\n" +
                "WHERE    a.city IN (\n" +
                "            SELECT   aa.city\n" +
                "            FROM     bookings.airports aa\n" +
                "            GROUP BY aa.city\n" +
                "            HAVING   COUNT(*) > 1\n" +
                "         )\n" +
                "ORDER BY a.city;";

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connect to PostgeSQL Server");
        } catch (SQLException e) {

            System.out.println("dont worry , it easy stupid program");
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String airportName = resultSet.getString("airport_name");
                String city = resultSet.getString("city");
                actualResultList.add(airportName + city);
            }
        } catch (SQLException exception) {
            connection.close();
        }

        Collections.sort(actualResultList);
        Collections.sort(expectedDataList);
        Assertions.assertThat(actualResultList.size()).isEqualTo(expectedDataList.size());
        Assertions.assertThat(actualResultList).isEqualTo(expectedDataList);
    }

    @Test
    void dataBaseTest2() {


        List<String> actualResultList = new ArrayList<>();

        List<String> expectedDataList = new ArrayList<>();
        expectedDataList.add("Airbus A319-100");
        expectedDataList.add("Airbus A320-200");
        expectedDataList.add("Airbus A321-200");
        expectedDataList.add("Boeing 737-300");
        expectedDataList.add("Ульяновск-ВосточныйУльяновск");
        expectedDataList.add("Ульяновск-ВосточныйУльяновск");
        expectedDataList.add("Ульяновск-ВосточныйУльяновск");

        String sql = "SELECT   a.model,\n" +
                "         string_agg (s2.fare_conditions || '(' || s2.num::text || ')',\n" +
                "                     ', ') as fare_conditions\n" +
                "FROM     (\n" +
                "          SELECT   s.aircraft_code, s.fare_conditions, count(*) as num\n" +
                "          FROM     bookings.seats s\n" +
                "          GROUP BY s.aircraft_code, s.fare_conditions\n" +
                "          ORDER BY s.aircraft_code, s.fare_conditions\n" +
                "         ) s2\n" +
                "         join bookings.aircrafts a ON  s2.aircraft_code = a.aircraft_code \n" +
                "GROUP BY a.model\n" +
                "Having count(s2.fare_conditions) > 1";

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connect to PostgeSQL Server");
        } catch (SQLException e) {

            System.out.println("dont worry , it easy stupid program");
            e.printStackTrace();
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String model = resultSet.getString("model");
//                actualResultList.add(airportName + city);
            }
        } catch (SQLException exception) {
//            connection.close();
        }

        Collections.sort(actualResultList);
        Collections.sort(expectedDataList);
        Assertions.assertThat(actualResultList.size()).isEqualTo(expectedDataList.size());
        Assertions.assertThat(actualResultList).isEqualTo(expectedDataList);
    }


}


