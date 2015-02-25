package com.github.jlgrock.snp.core.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The gender of the patient.
 */
public enum Gender {
    MALE(1), FEMALE(2), OTHER(3);

    private static Map<Integer, Gender> valuesById;

    static {
        valuesById = new HashMap<>();
        Arrays.asList(Gender.values()).forEach(gender -> valuesById.put(gender.getId(), gender));
    }

    private final Integer id;

    private Gender(final Integer i) {
        id = i;
    }

    /**
     * @return the Integer representing the gender.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the integer representing the gender
     * @return the Gender enumeration that matches the integer
     */
    public static Gender getValueById(final Integer id) {
        return valuesById.get(id);
    }
}

