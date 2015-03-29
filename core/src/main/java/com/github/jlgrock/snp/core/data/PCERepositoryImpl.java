package com.github.jlgrock.snp.core.data;

import com.github.jlgrock.snp.apis.connection.MongoDbFactory;
import com.github.jlgrock.snp.core.domain.PCE;
import com.mongodb.DBObject;

/**
 *
 */
public class PCERepositoryImpl extends AbstractRepositoryImpl<PCE, Long> implements PceRepository {

    public PCERepositoryImpl(final MongoDbFactory mongoDbFactoryIn) {
        super(mongoDbFactoryIn);
    }

    @Override
    protected PCE convertCollection(DBObject dbObjectin) {
        return null;
    }

    @Override
    protected DBObject convertToDBObject(PCE pce) {
        return null;
    }

    @Override
    protected String getCollection() {
        return null;
    }
}
