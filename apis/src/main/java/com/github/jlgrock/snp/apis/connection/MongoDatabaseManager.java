package com.github.jlgrock.snp.apis.connection;

import com.mongodb.DB;
import org.jvnet.hk2.annotations.Contract;

/**
 * Defines a class that will be able to index open connections to many MongoDB instances.
 */
@Contract
public interface MongoDatabaseManager {
    /**
     * Index a created database session.
     *
     * @param key the name of the database
     * @param session the database session
     */
    void addDb(String key, DB session);

    /**
     * Unindex a database that is no longer used.
     *
     * @param dbName the name of the database to un-index.
     * @return the database that matches the name, or {@literal null} if it doesn't exist
     */
    DB removeDb(String dbName);

    /**
     * Determine whether or not the DB has been indexed.
     * @param session the db to check for
     * @return {@literal true} if it contains the session, {@literal false} otherwise
     */
    boolean containsDb(DB session);

    /**
     * Determine whether or not the DB has been indexed.
     * @param key the db to check for
     * @return {@literal true} if it contains the key, {@literal false} otherwise
     */
    boolean containsDb(String key);

    /**
     * Whether or not this index has been initialized or not
     *
     * @return {@literal true} if it is empty, {@literal false} otherwise
     */
    boolean isEmpty();

    /**
     * Retrieve the database defined by the key
     *
     * @param key the database name
     *
     * @return the database identified by this key
     */
    DB getDb(String key);
}
