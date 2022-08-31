package com.cabit.Cab_It.database.mysql;

public class Query
{
    /*
    * Enum class which owns each and every mysql queries according to query types (DDL, DML..)
    * */
    public enum DDL
    {
        /*
        * DDL queries here
        * */
        CREATE_DATABASE(
                "CREATE DATABASE ".concat(DatabaseAccessCredential.DATABASE.getValue())
        ),
        CREATE_TABLE_ADMIN(
            "CREATE TABLE admin ( " +
                    " id varchar(255), " +
                    " username varchar(50) NOT NULL UNIQUE, " +
                    " password varchar(50) NOT NULL UNIQUE, " +
                    " PRIMARY KEY(id) " +
                ");"),
        CREATE_TABLE_EMPLOYEE(
            "CREATE TABLE employee ( " +
                "     id varchar(255), " +
                "     username varchar(50) NOT NULL UNIQUE, " +
                "     password varchar(50) NOT NULL UNIQUE, " +
                "     nic varchar(20) NOT NULL, " +
                "     first_name varchar(255) NOT NULL, " +
                "     mid_name varchar(255), " +
                "     last_name varchar(255), " +
                "     address_line_1 varchar(255) NOT NULL, " +
                "     address_line_2 varchar(255), " +
                "     address_line_3 varchar(255), " +
                "     city varchar(100) NOT NULL, " +
                "     phone varchar(20) NOT NULL, " +
                "     blood_group varchar(100) NOT NULL, " +
                "     license varchar(100) NOT NULL, " +
                "     registered_date_time datetime NOT NULL, " +
                "     photo longblob, " +
                "     location_id varchar(255), " +
                "     vehicle_id varchar(255), " +
                "     PRIMARY KEY(id), " +
                "     FOREIGN KEY(location_id) REFERENCES location(id), " +
                "     FOREIGN KEY(vehicle_id) REFERENCES vehicle(id) " +
                ");"),
        CREATE_TABLE_VEHICLE( "CREATE TABLE vehicle ( " +
                    " id varchar(255), " +
                    " model varchar(100) NOT NULL, " +
                    " brand varchar(100) NOT NULL, " +
                    " fuel_type varchar(100), " +
                    " engine_type varchar(100), " +
                    " plate_number varchar(100), " +
                    " top_speed int, " +
                    " registered_date_time datetime NOT NULL, " +
                    " photo longblob, " +
                    " PRIMARY KEY(id) " +
                ");"),
        CREATE_TABLE_CUSTOMER(
            "CREATE TABLE customer ( " +
                    " id varchar(255), " +
                    " nic varchar(20) NOT NULL, " +
                    " username varchar(50) NOT NULL UNIQUE, " +
                    " password varchar(50) NOT NULL UNIQUE, " +
                    " first_name varchar(255) NOT NULL, " +
                    " mid_name varchar(255), " +
                    " last_name varchar(255), " +
                    " address_line_1 varchar(255) NOT NULL, " +
                    " address_line_2 varchar(255), " +
                    " address_line_3 varchar(255), " +
                    " city varchar(100) NOT NULL, " +
                    " phone varchar(20) NOT NULL, " +
                    " photo longblob, " +
                    " PRIMARY KEY(id) " +
                ");"),
        CREATE_TABLE_ADVERTISEMENT(
            "CREATE TABLE advertisement ( " +
                    " id varchar(255), " +
                    " introduced_date_time timestamp, " +
                    " content varchar(255) NOT NULL, " +
                    " photo longblob, " +
                    " PRIMARY KEY(id) " +
                ");"),
        CREATE_TABLE_LOCATION( "CREATE TABLE location ( " +
                    " id varchar(255), " +
                    " district varchar(100) NOT NULL, " +
                    " zone varchar(100), " +
                    " PRIMARY KEY(id) " +
                ");"),
        CREATE_TABLE_ORDER(
            "CREATE TABLE `order` ( " +
                    " customer_id varchar(255), " +
                    " vehicle_id varchar(255), " +
                    " from_location_id varchar(255), " +
                    " to_location_id varchar(255), " +
                    " response varchar(255), " +
                    " review varchar(255), " +
                    " requested_date_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                    " responded_date_time timestamp, " +
                    " PRIMARY KEY(customer_id, vehicle_id, from_location_id, to_location_id, requested_date_time), " +
                    " KEY vehicle_id (vehicle_id), " +
                    " KEY from_location_id (from_location_id), " +
                    " KEY to_location_id (to_location_id), " +
                    " KEY customer_id (customer_id), " +
                    " CONSTRAINT `order_ibfk_1` FOREIGN KEY (customer_id) REFERENCES customer (id), " +
                    " CONSTRAINT `order_ibfk_2` FOREIGN KEY (vehicle_id) REFERENCES vehicle (id), " +
                    " CONSTRAINT `order_ibfk_3` FOREIGN KEY (from_location_id) REFERENCES location (id), " +
                    " CONSTRAINT `order_ibfk_4` FOREIGN KEY (to_location_id) REFERENCES location (id)" +
                "); "),

        CREATE_TABLE_NEXT_ID(
            "CREATE TABLE next_id ( " +
                "   next_val int PRIMARY KEY " +
                ");");


        private String query;

        DDL(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }

    public enum DML
    {
        /*
        * DML queries here
        * */
        SHOW_DATABASES(
            "SHOW DATABASES;"
        ),
        SHOW_TABLES(
                "SHOW TABLES;"
        ),
        INIT_NEXT_ID(
                "INSERT INTO `next_id` (next_val) VALUES (1);"
        ),
        INIT_DEFAULT_ADMIN(
               "INSERT INTO `admin` (id, username, password) " +
                    "VALUES (1, 'admin', 'admin');"
        ),
        GET_NEXT_ID(
           "SELECT `next_val` " +
                "FROM `next_id` LIMIT 1;"
        ),
        UPDATE_NEXT_ID(
            "UPDATE `next_id` " +
                 "SET `next_val`='%s';"
        ),

        //Queries related to admin
        SELECT_ADMIN_BY_UNAME_AND_PASSWORD(
               "SELECT * " +
                    "FROM `admin` " +
                    "WHERE `username` = '%s' AND `password` = '%s' LIMIT 1;"
        ),

        //Queries related to employee
        SELECT_EMPLOYEE_BY_UNAME_AND_PASSWORD(
               "SELECT * " +
                    "FROM `employee` " +
                    "WHERE username = '%s' AND password = '%s' LIMIT 1;"
        ),
        SELECT_EMPLOYEES(
                "SELECT * FROM `employee`;"
        ),

        INSERT_EMPLOYEE(
           "INSERT INTO `employee` " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
        ),

        UPDATE_EMPLOYEE(
        "UPDATE `employee` " +
                "SET `username`=?," +
                "`password`=?," +
                "`nic`=?," +
                "`first_name`=?, " +
                "`mid_name`=?," +
                "`last_name`=?," +
                "`address_line_1`=?," +
                "`address_line_2`=?, " +
                "`address_line_3`=?," +
                "`city`=?," +
                "`phone`=?," +
                "`blood_group`=?, " +
                "`license`=?, " +
                "`photo`=?, " +
                "`location_id`=?," +
                "`vehicle_id`=? " +
                "WHERE `id`=?;"
        ),

        UPDATE_EMPLOYEE_OPTIONAL(
                "UPDATE `employee` " +
                        "SET `username`=?," +
                        "`password`=?," +
                        "`address_line_1`=?," +
                        "`address_line_2`=?, " +
                        "`address_line_3`=?," +
                        "`city`=?," +
                        "`phone`=?," +
                        "`photo`=? " +
                        "WHERE `id`=?;"
        ),

        DELETE_EMPLOYEE(
               "DELETE FROM `employee` " +
                    "WHERE id = '%s';"
        ),

        SELECT_VEHICLES(
                "SELECT * " +
                     "FROM `vehicle`;"
        ),

        INSERT_VEHICLE(
                "INSERT INTO `vehicle` " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"),

        UPDATE_VEHICLE(
        "UPDATE `vehicle` " +
                "SET `model`=?," +
                "`brand`=?," +
                "`fuel_type`=?," +
                "`engine_type`=?," +
                "`plate_number`=?," +
                "`top_speed`=?, " +
                "`photo`=? " +
                "WHERE `id`=?;"
        ),

        UPDATE_VEHICLE_OPTIONAL(
        "UPDATE `vehicle` " +
                "SET `engine_type`=?, " +
                "`photo`=? " +
                "WHERE `id`=? ;"
        ),

        DELETE_VEHICLE("DELETE FROM `vehicle` WHERE `id` = '%s';"),

        SELECT_AVAILABLE_VEHICLES_NEAR_LOCATION(
          "SELECT *  " +
                "FROM `vehicle` " +
                "WHERE vehicle.id IN (" +
                    " SELECT `vehicle_id` " +
                    " FROM employee " +
                    " WHERE employee.`location_id` = '%s' " +
                ");"),

        //Queries related to location
        INSERT_LOCATION(
           "INSERT INTO `location` " +
                "VALUES ('%s', '%s', '%s');"
        ),

        SELECT_LOCATIONS(
               "SELECT * " +
                     "FROM `location`;"
        ),

        UPDATE_LOCATION(
           "UPDATE `location` " +
                "SET `district`='%s',`zone`='%s' " +
                "WHERE `id`='%s';"
        ),

        DELETE_LOCATION(
           "DELETE FROM `location` " +
                "WHERE `id`='%s';"
        ),

        //Queries related to order
        CREATE_ORDER(
               "INSERT INTO `order`(`customer_id`, `vehicle_id`, `from_location_id`, `to_location_id`, `requested_date_time`) " +
                    "VALUES ('%s', '%s', '%s', '%s', '%s');"
        ),

        SELECT_ORDERS(
                "SELECT * " +
                     "FROM `order` " +
                     "ORDER BY `requested_date_time` %s;"
        ),

        SELECT_ORDERS_OF_CUSTOMER(
                "SELECT * FROM `order` " +
                      "WHERE `customer_id`='%s' " +
                      "ORDER BY `requested_date_time` %s;"
        ),

        SELECT_ORDERS_OF_VEHICLE(
                "SELECT * FROM `order` " +
                      "WHERE `vehicle_id`='%s' AND `response` IS NULL " +
                      "ORDER BY `requested_date_time` %s;"
        ),

        UPDATE_ORDER(
          "UPDATE `order` " +
                "SET `vehicle_id`='%s', `from_location_id`='%s' AND `to_location_id`='%s' " +
                "WHERE `customer_id`='%s' AND `vehicle_id`='%s' AND `from_location_id`='%s' AND `to_location_id`='%s';"
        ),

        DELETE_ORDER(
                "DELETE FROM `order` " +
                     "WHERE `customer_id`='%s' AND `vehicle_id`='%s' AND `from_location_id`='%s' AND `to_location_id`='%s' AND `requested_date_time`='%s';"
        ),

        REVIEW_ORDER(
                "UPDATE `order` " +
                    "SET `review`= '%s' " +
                    "WHERE `customer_id`='%s' AND `vehicle_id`='%s' AND `from_location_id`='%s' AND `to_location_id`='%s' AND `requested_date_time`='%s';"
        ),

        ACCEPT_ORDER(
                "UPDATE `order` " +
                      "SET `response`='ACCEPTED', `responded_date_time`='%s' " +
                      "WHERE `customer_id`='%s' AND `vehicle_id`='%s' AND `from_location_id`='%s' AND `to_location_id`='%s' AND `requested_date_time`='%s';"
        ),

        INSERT_CUSTOMER(
                "INSERT INTO `customer` " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
        ),
        SELECT_CUSTOMERS(
                "SELECT * FROM " +
                        "`customer`;"
        ),
        SELECT_CUSTOMER_BY_UNAME_AND_PASSWORD(
                   "SELECT * " +
                        "FROM `customer` " +
                        "WHERE `username`='%s' AND `password`='%s';"
        ),

        UPDATE_CUSTOMER(
                "UPDATE `customer` " +
                        "SET " +
                            "`nic`=?, " +
                            "`username`=?, " +
                            "`password`=?, " +
                            "`first_name`=?, " +
                            "`mid_name`=?, " +
                            "`last_name`=?, " +
                            "`address_line_1`=?, " +
                            "`address_line_2`=?, " +
                            "`address_line_3`=?, " +
                            "`city`=?, " +
                            "`phone`=?, " +
                            "`photo`=?  " +
                        "WHERE `id`=?;"
        ),

        DELETE_CUSTOMER(
                "DELETE FROM `customer` " +
                      "WHERE `id`='%s';"
        ),

        //Queries related to advertisement
        SELECT_ADVERTISEMENTS(
               "SELECT * " +
                    "FROM `advertisement`;"
        ),

        INSERT_ADVERTISEMENT(
                "INSERT INTO `advertisement` " +
                        "VALUES (?, ?, ?, ?);"
        ),

        UPDATE_ADVERTISEMENT(
                "UPDATE `advertisement`  " +
                        "SET `content`=?, " +
                        "`photo`=?  " +
                        "WHERE `id`=?;"
        ),

        DELETE_ADVERTISEMENT(
           "DELETE FROM `advertisement` " +
                   "WHERE `id`='%s';"
        );

        String query;

        DML(String query)
        {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}
