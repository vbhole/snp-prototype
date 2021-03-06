package com.github.jlgrock.snp.core.data;

import com.github.jlgrock.snp.apis.data.MongoRepository;
import com.github.jlgrock.snp.core.domain.Encounter;
import org.jvnet.hk2.annotations.Contract;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

/**
 * The Encounter Repository provides an abstraction layer to executing queries against the Encounter
 * Collection within MongoDB.
 */
@Contract
@Named
public interface EncounterRepository extends MongoRepository<Encounter, Long> {
    /**
     * This function finds a list of encounters on a particular date
     *
     * @param date parameter of type Date
     * @return list of encounters
     */
    List<Encounter> findByDate(LocalDate date);
    //public List<Encounter> findByPatientId(Long id);

    //@Query("'$or':[{'firstName':{'$regex':?0,'$options':'i'}},{'lastName':{'$regex':?0,'$options':'i'}}]")
    //List<User> findByEmailOrFirstnameOrLastnameLike(String searchText);
}

