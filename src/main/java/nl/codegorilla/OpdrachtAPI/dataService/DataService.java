package nl.codegorilla.OpdrachtAPI.dataService;

import nl.codegorilla.OpdrachtAPI.model.Client;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    private final String URL = "jdbc:h2:file:./rest_api";
    private final String USER = "sa";
    private final String PASS = "";

    public void initializeDB() {
        initializeClientsTable();
        initializeOrdersTable();
    }

    public void initializeClientsTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS clients (
                	id int auto_increment primary key,
                	first_name varchar(255),
                	street_name varchar(255),
                	house_number int,
                	house_number_suffix varchar(5),
                	postal_code varchar(10),
                    phone_number varchar(255)
                );
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeOrdersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS orders (
                	id int auto_increment primary key,
                	client_id int,
                	title varchar(255),
                	amount double,
                	date_ordered datetime,
                	description varchar(255),
                	foreign key (client_id) references Clients(id)
                );
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTestData() {
        String sql = """
                insert into clients (first_name, street_name, house_number, house_number_suffix, postal_code, phone_number)
                values('Fred', 'Schonestraat', 43,'b', '4231 AB', '06-42516903');
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> findAllClients() {
        String sql = "SELECT * FROM clients";
        List<Client> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet clients = statement.executeQuery();
            while (clients.next()) {
                Client client = new Client(
                        clients.getInt("id"),
                        clients.getString("first_name"),
                        clients.getString("street_name"),
                        clients.getInt("house_number"),
                        clients.getString("house_number_suffix"),
                        clients.getString("postal_code"),
                        clients.getString("phone_number")
                );
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Client findClientById(int id) {
        Client client = new Client();
        String sql = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet cl = statement.executeQuery();
            if (cl.next()) {
                client.setId(cl.getInt("id"));
                client.setFirstName(cl.getString("first_name"));
                client.setStreetName(cl.getString("street_name"));
                client.setHouseNumber(cl.getInt("house_number"));
                client.setHouseNumberSuffix(cl.getString("house_number_suffix"));
                client.setPostalCode(cl.getString("postal_code"));
                client.setPhoneNumber(cl.getString("phone_number"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public int addClient(Client client) {
        int i = 0;
        if (checkValidClientForCreation(client)) {
            String sql = "INSERT INTO clients (first_name, street_name, house_number, house_number_suffix, postal_code, phone_number)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, client.getFirstName());
                statement.setString(2, client.getStreetName());
                statement.setInt(3, client.getHouseNumber());
                statement.setString(4, client.getHouseNumberSuffix());
                statement.setString(5, client.getPostalCode());
                statement.setString(6, client.getPhoneNumber());
                i = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return i;
    }

    public int updateClient(Client client) {
        int i = 0;
        if (!checkValidClientForUpdate(client)) {
            return -1;
        }
        String sql = """
                UPDATE clients
                SET first_name = ?,
                street_name = ?,
                house_number = ?,
                house_number_suffix = ?,
                postal_code = ?,
                phone_number = ?
                WHERE id = ?;
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getStreetName());
            statement.setInt(3, client.getHouseNumber());
            statement.setString(4, client.getHouseNumberSuffix());
            statement.setString(5, client.getPostalCode());
            statement.setString(6, client.getPhoneNumber());
            statement.setInt(7, client.getId());
            i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int deleteClient(int id) {
        int i = 0;
        String sql = "DELETE FROM clients WHERE id = ?";
        if (id == 0) {
            return -1;
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    private boolean checkValidClientForCreation(Client client) {
        return client.getFirstName() != null &&
                client.getHouseNumber() != 0 &&
                client.getHouseNumberSuffix() != null &&
                client.getPostalCode() != null &&
                client.getPhoneNumber() != null;
    }

    public boolean checkValidClientForUpdate (Client client) {
        return client.getId() != 0 &&
                client.getFirstName() != null &&
                client.getHouseNumber() != 0 &&
                client.getHouseNumberSuffix() != null &&
                client.getPostalCode() != null &&
                client.getPhoneNumber() != null;
    }
}



