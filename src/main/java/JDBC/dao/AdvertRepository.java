package JDBC.dao;

import com.emlakburada.entity.Advert;
import com.emlakburada.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertRepository extends JdbcConnectionRepository {
    private static final String INSERT_ADVERT = "INSERT INTO ADVERT (ID, BASLIK, CREATOR, FIYAT,AKTIF) VALUES (?,?,?,?,?);";
    private static final String SELECT_ALL_ADVERT = "SELECT * FROM ADVERT";
    private static final String FIND_ADVERT = "SELECT * FROM ADVERT WHERE id = ?";
    private static final String FIND_USER = "SELECT * FROM USER WHERE id = ?";


    public void save(Advert advert) {
        User user = new User();

        Connection connection = connect();

        if (connection != null) {

            PreparedStatement prepareStatement = null;
            try {

                prepareStatement = connection.prepareStatement(INSERT_ADVERT);
                prepareStatement.setInt(1, advert.id);
                prepareStatement.setString(2, advert.baslik);
                prepareStatement.setString(3, String.valueOf(user));
                prepareStatement.setInt(4, advert.fiyat);
                prepareStatement.setBoolean(5, advert.aktif);

                int executeUpdate = prepareStatement.executeUpdate();

                System.out.println("result: " + executeUpdate);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    prepareStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Connection oluşturululamadı!");
        }
    }

    public List<Advert> findAll() {

        List<Advert> advertList = new ArrayList<>();

        Connection connection = connect();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_ADVERT);

            ResultSet result = prepareStatement.executeQuery();

            while (result.next()) {

                int id = result.getInt("id");
                String baslik = result.getString("baslik");
                String creator = result.getString("creator");
                int fiyat = result.getInt("fiyat");
                Boolean aktif = result.getBoolean("aktif");

                advertList.add(prepareAdvert(id, baslik, creator, fiyat, aktif));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return advertList;
    }

    private Advert prepareAdvert(int id, String baslik, String creator, int fiyat, Boolean aktif) {
        User user = new User();
        Advert advert = new Advert();
        advert.id = id;
        advert.baslik = baslik;
        advert.creator = user;
        advert.fiyat = fiyat;
        advert.aktif = aktif;
        return advert;
    }

    public Advert findOne(int id) {
        User user = null;
        Advert advert = null;
        Connection connection = connect();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(FIND_USER);
            PreparedStatement prepareStatement1 = connection.prepareStatement(FIND_ADVERT);
            prepareStatement.setInt(1, id);
            prepareStatement1.setInt(1, id);

            ResultSet result = prepareStatement1.executeQuery();
            if (result.next()) {
                int advertId = result.getInt("id");
                String baslik = result.getString("baslik");
                String creator = result.getString("creator");
                int fiyat = result.getInt("fiyat");
                boolean aktif = result.getBoolean("aktif");
                advert = prepareAdvert(advertId, baslik, creator, fiyat, aktif);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return advert;

    }
}
