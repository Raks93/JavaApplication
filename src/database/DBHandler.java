package database;

import entities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class DBHandler {
    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:src\\database\\data.sqlite");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Соединение установлено");
        return dbConnection;
    }

    public ObservableList<BD> selectBD() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT BD.ID_BD, BD.plantNameBD, BD.dateTOBD, TypeBD.nameTypeBD " +
                    "FROM BD INNER JOIN TypeBD ON TypeBD.IDTypeBD = BD.idTypeBD;" );
            ObservableList<BD> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new BD(rs.getInt("ID_BD"),
                        rs.getString("plantNameBD"),
                        rs.getString("dateTOBD"),
                        rs.getString("nameTypeBD")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<BOI> selectBOI() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT BOI.ID_BOI, BOI.plantNameBOI, BOI.dateTOBOI, TypeBOI.nameTypeBOI " +
                    "FROM BOI INNER JOIN TypeBOI ON TypeBOI.IDTypeBOI = BOI.typeBOI;" );
            ObservableList<BOI> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new BOI(rs.getInt("ID_BOI"),
                        rs.getString("plantNameBOI"),
                        rs.getString("dateTOBOI"),
                        rs.getString("nameTypeBOI")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<FrameTable> selectFrameTable() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Frame;" );
            ObservableList<FrameTable> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new FrameTable(rs.getInt("ID_Frame"),
                        rs.getInt("sizeFrame")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<II> selectII() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM II;" );
            ObservableList<II> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new II(rs.getInt("ID_II"),
                        rs.getString("plantNameII"),
                        rs.getString("dateTransferII")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Region> selectRegion() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Region;" );
            ObservableList<Region> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new Region(rs.getInt("ID_Region"),
                        rs.getString("nameRegion")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Tube> selectTube() {
        try {
            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ID_Tube, dateTOTube, diameterTube, " +
                    " CASE WHEN liningTube = 1 THEN 'Да' " +
                    " ELSE 'Нет'  END AS lining " +
                    " FROM Tube;" );
            ObservableList<Tube> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new Tube(rs.getInt("ID_Tube"),
                        rs.getString("dateTOTube"),
                        rs.getInt("diameterTube"),
                        rs.getString("lining")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<TypeBD> selectTypeBD() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM TypeBD;" );
            ObservableList<TypeBD> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new TypeBD(rs.getInt("IDTypeBD"),
                        rs.getString("nameTypeBD")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<TypeBOI> selectTypeBOI() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM TypeBOI;" );
            ObservableList<TypeBOI> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new TypeBOI(rs.getInt("IDTypeBOI"),
                        rs.getString("nameTypeBOI")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Schemes> selectSchemes() {
        try {
            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT Schemes.ID, Region.nameRegion, Schemes.name, BD.plantNameBD, TypeBD.nameTypeBD, " +
                    " BOI.plantNameBOI, TypeBOI.nameTypeBOI, II.plantNameII, Tube.diameterTube, Frame.sizeFrame, " +
                    " CASE WHEN liningTube = 1 THEN 'Да' " +
                    " ELSE 'Нет'  END AS lining " +
                    " FROM TypeBOI INNER JOIN (TypeBD INNER JOIN (Tube INNER JOIN (Region INNER JOIN (Frame INNER JOIN (II INNER JOIN " +
                    " (BOI INNER JOIN (BD INNER JOIN Schemes ON BD.ID_BD = Schemes.ID_BD) ON BOI.ID_BOI = Schemes.ID_BOI) " +
                    " ON II.ID_II = Schemes.ID_II) ON Frame.ID_Frame = Schemes.ID_Frame) ON Region.ID_Region = Schemes.ID_Region) " +
                    " ON Tube.ID_Tube = Schemes.ID_Tube) ON TypeBD.IDTypeBD = BD.idTypeBD) ON TypeBOI.IDTypeBOI = BOI.typeBOI;" );
            ObservableList<Schemes> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new Schemes(rs.getInt("ID"),
                        rs.getString("nameRegion"),
                        rs.getString("name"),
                        rs.getString("plantNameBD"),
                        rs.getString("nameTypeBD"),
                        rs.getString("plantNameBOI"),
                        rs.getString("nameTypeBOI"),
                        rs.getString("plantNameII"),
                        rs.getInt("diameterTube"),
                        rs.getString("lining"),
                        rs.getInt("sizeFrame")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Schemes> selectTO() {
        try {
            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT Region.nameRegion, Schemes.name, BD.plantNameBD, BD.dateTOBD, TypeBD.nameTypeBD " +
                    " FROM Region INNER JOIN ((TypeBD INNER JOIN BD ON TypeBD.IDTypeBD = BD.idTypeBD) " +
                    " INNER JOIN Schemes ON BD.ID_BD = Schemes.ID_BD) ON Region.ID_Region = Schemes.ID_Region;" );
            ObservableList<Schemes> select = FXCollections.observableArrayList();

            //название
            //заводской№
            //дата
            // примечание
            while ( rs.next() ) {
                select.add(new Schemes(rs.getString("nameRegion"),
                        rs.getString("name"),
                        "БД",
                        rs.getString("plantNameBD"),
                        rs.getString("dateTOBD"),
                        "Тип - " + rs.getString("nameTypeBD")));
            }

            rs = stmt.executeQuery( "SELECT Region.nameRegion, Schemes.name, BOI.plantNameBOI, BOI.dateTOBOI, TypeBOI.nameTypeBOI" +
                    " FROM TypeBOI INNER JOIN (BOI INNER JOIN (Region INNER JOIN Schemes ON Region.ID_Region = Schemes.ID_Region) " +
                    " ON BOI.ID_BOI = Schemes.ID_BOI) ON TypeBOI.IDTypeBOI = BOI.typeBOI;" );

            while ( rs.next() ) {
                select.add(new Schemes(rs.getString("nameRegion"),
                        rs.getString("name"),
                        "БОИ",
                        rs.getString("plantNameBOI"),
                        rs.getString("dateTOBOI"),
                        "Тип - " + rs.getString("nameTypeBOI")));
            }

            rs = stmt.executeQuery( "SELECT Region.nameRegion, Schemes.name, Tube.dateTOTube, Tube.diameterTube, " +
                    " CASE WHEN liningTube = 1 THEN 'футирована' " +
                    " ELSE 'не футирована'  END AS lining " +
                    " FROM Tube INNER JOIN (Region INNER JOIN Schemes ON Region.ID_Region = Schemes.ID_Region) " +
                    " ON Tube.ID_Tube = Schemes.ID_Tube;" );

            while ( rs.next() ) {
                select.add(new Schemes(rs.getString("nameRegion"),
                        rs.getString("name"),
                        "Катушка",
                        "-",
                        rs.getString("dateTOTube"),
                        "Диаметр = " + rs.getString("diameterTube") + ", " +
                        rs.getString("lining")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Schemes> selectSchemesWithID(String id) {
        try {
            DBHandler db = new DBHandler();
            Statement stmt = db.getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Region.nameRegion, Schemes.name, BD.plantNameBD, TypeBD.nameTypeBD, " +
                    " BOI.plantNameBOI, TypeBOI.nameTypeBOI, II.plantNameII, Tube.diameterTube, Frame.sizeFrame, " +
                    " CASE WHEN liningTube = 1 THEN 'Да' " +
                    " ELSE 'Нет'  END AS lining " +
                    " FROM TypeBOI INNER JOIN (TypeBD INNER JOIN (Tube INNER JOIN (Region INNER JOIN (Frame INNER JOIN (II INNER JOIN " +
                    " (BOI INNER JOIN (BD INNER JOIN Schemes ON BD.ID_BD = Schemes.ID_BD) ON BOI.ID_BOI = Schemes.ID_BOI) " +
                    " ON II.ID_II = Schemes.ID_II) ON Frame.ID_Frame = Schemes.ID_Frame) ON Region.ID_Region = Schemes.ID_Region) " +
                    " ON Tube.ID_Tube = Schemes.ID_Tube) ON TypeBD.IDTypeBD = BD.idTypeBD) ON TypeBOI.IDTypeBOI = BOI.typeBOI" +
                    " WHERE ID='" + id +"';");
            ObservableList<Schemes> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(new Schemes(rs.getString("nameRegion"),
                        rs.getString("name"),
                        rs.getString("plantNameBD"),
                        rs.getString("nameTypeBD"),
                        rs.getString("plantNameBOI"),
                        rs.getString("nameTypeBOI"),
                        rs.getString("plantNameII"),
                        rs.getInt("diameterTube"),
                        rs.getString("lining"),
                        rs.getInt("sizeFrame")));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public ObservableList<String> selectNamesTypeBD() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT nameTypeBD FROM TypeBD;" );
            ObservableList<String> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(rs.getString("nameTypeBD"));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIDTypeBD(String name) {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT IDTypeBD FROM TypeBD WHERE nameTypeBD = '"+ name +"';" );
            String select = "";

            while ( rs.next() ) {
                select = (rs.getString("IDTypeBD"));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<String> selectNamesTypeBOI() {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT nameTypeBOI FROM TypeBOI;" );
            ObservableList<String> select = FXCollections.observableArrayList();

            while ( rs.next() ) {
                select.add(rs.getString("nameTypeBOI"));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIDTypeBOI(String name) {
        try {

            Statement stmt = getDbConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT IDTypeBOI FROM TypeBOI WHERE nameTypeBOI = '"+ name +"';" );
            String select = "";

            while ( rs.next() ) {
                select = (rs.getString("IDTypeBOI"));
            }

            rs.close();
            stmt.close();
            return select;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertBD (String name, String date, String idType) {
        try {
            System.out.println(name);
            System.out.println(date);
            System.out.println(idType);
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO BD (plantNameBD, dateTOBD, idTypeBD) " +
                    " VALUES ('" + name + "', '" + date + "', '" + idType + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertBOI (String name, String date, String idType) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO BOI (plantNameBOI, dateTOBOI, typeBOI) " +
                    " VALUES ('" + name + "', '" + date + "', '" + idType + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertFrame (String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Frame (sizeFrame) " +
                    " VALUES ('" + str + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertII (String name, String date) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO II (plantNameII, dateTransferII) " +
                    " VALUES ('" + name + "', '" + date + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertTube (String date, String diameter, String lining) {
        try {
            if (lining.equals("true"))
                    lining = "1";
            else
                lining = "0";

            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Tube (dateTOTube, diameterTube, liningTube) " +
                    " VALUES ('" + date + "', '" + diameter + "', '" + lining + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertSchemes(String idRegion, String name, String idBD, String idBOI,
                              String idII, String idTube, String idFrame) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Schemes (ID_Region, name, ID_BD, ID_BOI, ID_II, ID_Tube, ID_Frame) " +
                    " VALUES ('" + idRegion + "', '" + name + "', '" + idBD + "', '" + idBOI + "', '" + idII + "', '" + idTube + "', '" + idFrame + "');");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertRegion (String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO Region (nameRegion) " +
                    " VALUES ('" + str + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertTypeBD (String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO TypeBD (nameTypeBD) " +
                    " VALUES ('" + str + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertTypeBOI(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("INSERT INTO TypeBOI (nameTypeBOI) " +
                    " VALUES ('" + str + "');");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteBD(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM BD WHERE ID_BD='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='BD';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteBOI(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM BOI WHERE ID_BOI='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='BOI';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteFrame(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM Frame WHERE ID_Frame='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='Frame';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteII(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM II WHERE ID_II='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='II';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRegion(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM Region WHERE ID_Region='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='Region';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteTube(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM Tube WHERE ID_Tube='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='Tube';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeBD(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM TypeBD WHERE IDTypeBD='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='TypeBD';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeBOI(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM TypeBOI WHERE IDTypeBOI='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='TypeBOI';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteSchemes(String str) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("DELETE FROM Schemes WHERE ID='" + str +"';");
            stmt.executeUpdate( "DELETE FROM sqlite_sequence WHERE name='Schemes';" );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTwoFields(String idOld, String idNew, String name, int index) {
        try {
            Statement stmt = getDbConnection().createStatement();
            switch (index) {
                case (2):
                    stmt.executeUpdate("UPDATE Frame SET ID_Frame = '" + idNew +"', sizeFrame = '" + name +"'" +
                            " WHERE ID_Frame = '" + idOld +"';");
                    break;
                case (4):
                    stmt.executeUpdate("UPDATE Region SET ID_Region = '" + idNew +"', nameRegion = '" + name +"'" +
                            " WHERE ID_Region = '" + idOld +"';");
                    break;
                case (7):
                    stmt.executeUpdate("UPDATE TypeBD SET IDTypeBD = '" + idNew +"', nameTypeBD = '" + name +"'" +
                            " WHERE IDTypeBD = '" + idOld +"';");
                    break;
                case (8):
                    stmt.executeUpdate("UPDATE TypeBOI SET IDTypeBOI = '" + idNew +"', nameTypeBOI = '" + name +"'" +
                            " WHERE IDTypeBOI = '" + idOld +"';");
                    break;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateII(String idOld, String idNew, String name, String date) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("UPDATE II SET ID_II = '" + idNew +"', plantNameII = '" + name +"', dateTransferII = '" + date + "'" +
                            " WHERE ID_II = '" + idOld +"';");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateBD(String idOld, String idNew, String name, String date, String idType) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("UPDATE BD SET ID_BD = '" + idNew +"', plantNameBD = '" + name + "', dateTOBD = '" + date + "', idTypeBD = '" + idType + "'" +
                    " WHERE ID_BD = '" + idOld +"';");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateBOI(String idOld, String idNew, String name, String date, String idType) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("UPDATE BOI SET ID_BOI = '" + idNew +"', plantNameBOI = '" + name + "', dateTOBOI = '" + date + "', typeBOI = '" + idType + "'" +
                    " WHERE ID_BOI = '" + idOld +"';");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateTube(String idOld, String idNew, String date, String diameter, String lining) {
        try {

            if (lining.equals("true"))
                lining = "1";
            else
                lining = "0";

            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("UPDATE Tube SET ID_Tube = '" + idNew +"', dateTOTube = '" + date +"', diameterTube = '" + diameter + "', liningTube = '" + lining + "'" +
                    " WHERE ID_Tube = '" + idOld +"';");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateSchemes(String idOld, String idNew, String idRegion, String name, String idBD, String idBOI,
                              String idII, String idTube, String idFrame) {
        try {
            Statement stmt = getDbConnection().createStatement();
            stmt.executeUpdate("UPDATE Schemes SET ID = '" + idNew +"', ID_Region = '" + idRegion + "', " +
                    " name = '" + name + "', ID_BD = '" + idBD + "', ID_BOI = '" + idBOI + "', ID_II = '" + idII + "', " +
                    " ID_Tube = '" + idTube +"', ID_Frame = '" + idFrame + "' " +
                    " WHERE ID = '" + idOld +"';");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
