package com.github.jlgrock.snp.core.converters;

import com.github.jlgrock.snp.apis.converters.Converter;
import com.github.jlgrock.snp.core.data.EncounterTags;
import com.github.jlgrock.snp.core.domain.Encounter;
import com.github.jlgrock.snp.core.domain.Observation;
import com.mongodb.DBObject;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Conversion class to convert between a MongoDB DBObject to an Encounter object.
 */
@Service
@Named
public class EncounterReadConverter extends AbstractReadConverter implements Converter<DBObject, Encounter> {

    private final ObservationReadConverter observationReadConverter;

    /**
     * @param observationReadConverterIn object of type ObservationReadConverter that has values from a MongoDB
     *                                   object stored in an Observation object.
     */
    @Inject
    public EncounterReadConverter(final ObservationReadConverter observationReadConverterIn) {
        observationReadConverter = observationReadConverterIn;
    }

    @Override
    public Encounter convert(final DBObject source) {
        Encounter encounter = new Encounter();
        encounter.setId(parseLong(source, EncounterTags.ID_TAG));
        encounter.setPatientId(parseLong(source, EncounterTags.PATIENT_TAG));
        encounter.setDate(parseLocalDate(source, EncounterTags.DATE_TAG));
        encounter.setType(parseInteger(source, EncounterTags.TYPE_TAG));
        encounter.setReasonForVisit(parseString(source, EncounterTags.REASON_FOR_VISIT_TAG));

        List<DBObject> observationsObjs = (List<DBObject>) source.get(EncounterTags.OBSERVATIONS_TAG);
        List<Observation> observations = new ArrayList<>();
        if (observationsObjs != null) {
            observations = observationsObjs.stream().map(
                    observationObj->observationReadConverter.convert(observationObj)).collect(Collectors.toList());
        }
        encounter.setObservations(observations);
        return encounter;
    }
}



