package pl.lodz.p.it.ssbd2020.ssbd05.exceptions.io.database;

import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.AppBaseException;

public class DatabaseConnectionException extends AppBaseException {
    static final public String KEY_DATABASE_CONNECTION_PROBLEM = "error.database.connection.problem";

    public DatabaseConnectionException(){ super(KEY_DATABASE_CONNECTION_PROBLEM); }

    public DatabaseConnectionException(String msg){
        super(msg);
    }

    public DatabaseConnectionException(Throwable ex){
        super(ex);
    }
}
