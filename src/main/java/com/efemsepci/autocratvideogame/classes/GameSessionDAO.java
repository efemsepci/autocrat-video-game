package com.efemsepci.autocratvideogame.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.efemsepci.autocratvideogame.classes.DatabaseConnection.getConnection;

public class GameSessionDAO {

    public static void addGameSession (GameSession gameSession) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = getConnection();
            String query = "INSERT INTO gamesessions (numOfPlayers, startTime, endTime, name) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gameSession.getNumOfPlayers());
            preparedStatement.setDate(2,gameSession.getStartTime());
            preparedStatement.setDate(3, gameSession.getEndTime());
            preparedStatement.setString(4, gameSession.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Game Session added successfully.");
            } else {
                System.out.println("Failed to add game session.");
            }
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    public static void addPlayer(GameSession gameSession, User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String playerQuery = "UPDATE players SET gamesession_id = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(playerQuery);
            preparedStatement.setInt(1, gameSession.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            String gameSessionQuery = " UPDATE gamesessions SET numOfPlayers = numOfPlayers + 1 WHERE id = ?";
            preparedStatement = connection.prepareStatement(gameSessionQuery);
            preparedStatement.setInt(1, gameSession.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void removePlayer(GameSession gameSession, User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String playerQuery = "UPDATE players SET gamesession_id = NULL WHERE id = ?";
            preparedStatement = connection.prepareStatement(playerQuery);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            String gameSessionQuery = " UPDATE gamesessions SET numOfPlayers = numOfPlayers - 1 WHERE id = ?";
            preparedStatement = connection.prepareStatement(gameSessionQuery);
            preparedStatement.setInt(1, gameSession.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static List<GameSession> getGameSessions() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<GameSession> gameSessions = new ArrayList<>();

        try{
            connection = getConnection();
            String query = "SELECT * FROM gamesessions";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String roomName = resultSet.getString("name");
                int numOfPlayers = resultSet.getInt("numOfPlayers");
                GameSession gameSession = new GameSession(id, roomName, numOfPlayers);
                gameSessions.add(gameSession);
            }
            return gameSessions;
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

    public static GameSession getGameSessionById(int sessionId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        GameSession gameSession = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM gamesessions WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, sessionId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String roomName = resultSet.getString("name");
                int numOfPlayers = resultSet.getInt("numOfPlayers");
                gameSession = new GameSession(sessionId, roomName, numOfPlayers);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return gameSession;
    }

}
