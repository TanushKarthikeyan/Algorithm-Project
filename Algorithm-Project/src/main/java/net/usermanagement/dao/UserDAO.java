package net.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.usermanagement.model.User;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Tanush14082003#$";
    private static final String INSERT_USERS_SQL = "INSERT INTO users  (name, email, country) VALUES  (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
        } catch (SQLException var3) {
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

        return connection;
    }

    public void insertUser(User user) throws Throwable {
        System.out.println("INSERT INTO users  (name, email, country) VALUES  (?, ?, ?);");

        try {
            Throwable var2 = null;
            Object var3 = null;

            try {
                Connection connection = this.getConnection();

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users  (name, email, country) VALUES  (?, ?, ?);");

                    try {
                        preparedStatement.setString(1, user.getName());
                        preparedStatement.setString(2, user.getEmail());
                        preparedStatement.setString(3, user.getCountry());
                        System.out.println(preparedStatement);
                        preparedStatement.executeUpdate();
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }

                    }
                } catch (Throwable var19) {
                    if (var2 == null) {
                        var2 = var19;
                    } else if (var2 != var19) {
                        var2.addSuppressed(var19);
                    }

                    if (connection != null) {
                        connection.close();
                    }

                    throw var2;
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Throwable var20) {
                if (var2 == null) {
                    var2 = var20;
                } else if (var2 != var20) {
                    var2.addSuppressed(var20);
                }

                throw var2;
            }
        } catch (SQLException var21) {
            this.printSQLException(var21);
        }

    }

    public User selectUser(int id) throws Throwable {
        User user = null;

        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                Connection connection = this.getConnection();

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("select id,name,email,country from users where id =?");

                    try {
                        preparedStatement.setInt(1, id);
                        System.out.println(preparedStatement);

                        String name;
                        String email;
                        String country;
                        for(ResultSet rs = preparedStatement.executeQuery(); rs.next(); user = new User(id, name, email, country)) {
                            name = rs.getString("name");
                            email = rs.getString("email");
                            country = rs.getString("country");
                        }
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }

                    }
                } catch (Throwable var24) {
                    if (var3 == null) {
                        var3 = var24;
                    } else if (var3 != var24) {
                        var3.addSuppressed(var24);
                    }

                    if (connection != null) {
                        connection.close();
                    }

                    throw var3;
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Throwable var25) {
                if (var3 == null) {
                    var3 = var25;
                } else if (var3 != var25) {
                    var3.addSuppressed(var25);
                }

                throw var3;
            }
        } catch (SQLException var26) {
            this.printSQLException(var26);
        }

        return user;
    }

    public List<User> selectAllUsers() throws Throwable {
        List<User> users = new ArrayList();

        try {
            Throwable var2 = null;
            Object var3 = null;

            try {
                Connection connection = this.getConnection();

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("select * from users");

                    try {
                        System.out.println(preparedStatement);
                        ResultSet rs = preparedStatement.executeQuery();

                        while(rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            String email = rs.getString("email");
                            String country = rs.getString("country");
                            users.add(new User(id, name, email, country));
                        }
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }

                    }
                } catch (Throwable var24) {
                    if (var2 == null) {
                        var2 = var24;
                    } else if (var2 != var24) {
                        var2.addSuppressed(var24);
                    }

                    if (connection != null) {
                        connection.close();
                    }

                    throw var2;
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Throwable var25) {
                if (var2 == null) {
                    var2 = var25;
                } else if (var2 != var25) {
                    var2.addSuppressed(var25);
                }

                throw var2;
            }
        } catch (SQLException var26) {
            this.printSQLException(var26);
        }

        return users;
    }

    public boolean deleteUser(int id) throws Throwable {
        Throwable var3 = null;
        Object var4 = null;

        try {
            Connection connection = this.getConnection();

            boolean rowDeleted;
            try {
                PreparedStatement statement = connection.prepareStatement("delete from users where id = ?;");

                try {
                    statement.setInt(1, id);
                    rowDeleted = statement.executeUpdate() > 0;
                } finally {
                    if (statement != null) {
                        statement.close();
                    }

                }
            } catch (Throwable var17) {
                if (var3 == null) {
                    var3 = var17;
                } else if (var3 != var17) {
                    var3.addSuppressed(var17);
                }

                if (connection != null) {
                    connection.close();
                }

                throw var3;
            }

            if (connection != null) {
                connection.close();
            }

            return rowDeleted;
        } catch (Throwable var18) {
            if (var3 == null) {
                var3 = var18;
            } else if (var3 != var18) {
                var3.addSuppressed(var18);
            }

            throw var3;
        }
    }

    public boolean updateUser(User user) throws Throwable {
        Throwable var3 = null;
        Object var4 = null;

        try {
            Connection connection = this.getConnection();

            boolean rowUpdated;
            try {
                PreparedStatement statement = connection.prepareStatement("update users set name = ?,email= ?, country =? where id = ?;");

                try {
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getEmail());
                    statement.setString(3, user.getCountry());
                    statement.setInt(4, user.getId());
                    rowUpdated = statement.executeUpdate() > 0;
                } finally {
                    if (statement != null) {
                        statement.close();
                    }

                }
            } catch (Throwable var17) {
                if (var3 == null) {
                    var3 = var17;
                } else if (var3 != var17) {
                    var3.addSuppressed(var17);
                }

                if (connection != null) {
                    connection.close();
                }

                throw var3;
            }

            if (connection != null) {
                connection.close();
            }

            return rowUpdated;
        } catch (Throwable var18) {
            if (var3 == null) {
                var3 = var18;
            } else if (var3 != var18) {
                var3.addSuppressed(var18);
            }

            throw var3;
        }
    }

    private void printSQLException(SQLException ex) {
        Iterator var3 = ex.iterator();

        while(true) {
            Throwable e;
            do {
                if (!var3.hasNext()) {
                    return;
                }

                e = (Throwable)var3.next();
            } while(!(e instanceof SQLException));

            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException)e).getSQLState());
            System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
            System.err.println("Message: " + e.getMessage());

            for(Throwable t = ex.getCause(); t != null; t = t.getCause()) {
                System.out.println("Cause: " + t);
            }
        }
    }
}
