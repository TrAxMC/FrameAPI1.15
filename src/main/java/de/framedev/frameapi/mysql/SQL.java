package de.framedev.frameapi.mysql;

import de.framedev.frameapi.main.Main;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQL {
    public static void createTable(String tablename, String... columns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]);
            if (i < columns.length - 1) {
                stringBuilder.append(",");
            }
        }
        String builder = stringBuilder.toString();
        try {
            String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (" + builder + ",Numbers INT AUTO_INCREMENT KEY,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
            PreparedStatement stmt = MYSQL.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue Create Table §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
    }

    public static void insertData(String table, String data, String... columns) {
        StringBuilder newStringBuilder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            newStringBuilder.append(columns[i]);
            if (i < columns.length - 1) {
                newStringBuilder.append(",");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO " + table);
        stringBuilder.append(" (").append(newStringBuilder.toString()).append(")").append(" VALUES ").append("(").append(data).append(")");
        String builder2 = stringBuilder.toString();
        try {
            Statement stmt = MYSQL.getConnection().createStatement();
            stmt.executeUpdate(builder2);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue insertData §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
    }

    public static void updateData(String table, String selected, String data, String where) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE " + table + " SET ").append(selected + " = " + data).append(" WHERE " + where);
        String sql = stringBuilder.toString();
        try {
            Statement stmt = MYSQL.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue if updateData §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
    }


    public static void deleteDataInTable(String table, String where) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + table)
                .append(" WHERE " + where);
        String sql = sb.toString();
        try {
            Statement stmt = MYSQL.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue deleteData §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
    }

    public static boolean exists(String table, String column, String data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(table)
                .append(" WHERE ")
                .append(column)
                .append(" = '" + data + "';");

        try {
            Statement statement = MYSQL.getConnection().createStatement();
            String sql = stringBuilder.toString();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                if (res.getString(column) == null) {
                    return false;
                }
                return true;
            }

            return false;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue if Exists Data §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
        return false;
    }


    public static Object get(String table, String selected, String column, String data) {
        Object o = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(table)
                .append(" WHERE " + column + " = '")
                .append(data)
                .append("'");
        String sql = stringBuilder.toString();
        try {
            Statement statement = MYSQL.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                o = res.getObject(selected);
                if (o != null) {
                    return o;
                }
                return o;
            }

            return o;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue getObject §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
        return o;
    }

    public static void deleteTable(String table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE " + table);
        String sql = stringBuilder.toString();

        try {
            Statement stmt = MYSQL.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue Delete Table §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + " §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + " §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
    }

    public static boolean isTableExists(String table) {
        try {
            Statement statement = MYSQL.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SHOW TABLES LIKE '" + table + "'");
            if (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "An Issue isTableExists Table §6: §f" + e.getMessage());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getSQLState());
            Bukkit.getConsoleSender().sendMessage(Main.Variables.getPrefix() + "§6: §f" + e.getErrorCode());
        } finally {
            if (MYSQL.con != null) {
                MYSQL.close();
            }
        }
        return false;
    }
}


