package de.framedev.frameapi.mysql;

import de.framedev.frameapi.main.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;


public class MYSQL {
    public static String MySQLPrefix = "§a[§bMySQL§a]";
    public static String host = Main.getInstance().getConfig().getString("MYSQL.Host");
    public static String user = Main.getInstance().getConfig().getString("MYSQL.User");
    public static String password = Main.getInstance().getConfig().getString("MYSQL.Password");
    public static String database = Main.getInstance().getConfig().getString("MYSQL.Database");
    public static String port = Main.getInstance().getConfig().getString("MYSQL.Port");
    public static Connection con;
    static ConsoleCommandSender console = Bukkit.getConsoleSender();
    static String JdbcURL = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false";


    public MYSQL() {
    }

    public static Connection getConnection() {
        if (con == null) {
            close();
            try {
                con = DriverManager.getConnection(JdbcURL, user, password);
                return con;
            } catch (SQLException ex) {

            }
        } else {
            close();
            try {
                con = DriverManager.getConnection(JdbcURL, user, password);
                return con;
            } catch (SQLException ex) {

            }
        }
        return con;
    }

    // connect
    public static void connect() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(JdbcURL, user, password);
                con.setNetworkTimeout(Executors.newFixedThreadPool(100), 1000000);
                con.createStatement().executeUpdate("SET GLOBAL max_connections=1000;");
                Bukkit.getConsoleSender().sendMessage(MySQLPrefix + "-Verbindung wurde aufgebaut!");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(MySQLPrefix + " §cEin Fehler ist aufgetreten: §a" + e.getMessage());
            }
        }
    }

    public static void close() {
        if (con != null) {
            try {
                if (con != null) {
                    con.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

