package com.github.jlgrock.snp.core.converters;

import com.github.jlgrock.snp.apis.converters.ReadConverter;
import com.github.jlgrock.snp.core.data.AssertionTags;
import com.github.jlgrock.snp.core.domain.ClassifiedAssertion;
import com.mongodb.DBObject;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;

/**
 * A Conversion class to convert between a MongoDB DBObject to an PCE object.
 */
@Service
@Named
public class ClassifiedAssertionReadConverter extends AbstractReadConverter implements ReadConverter<DBObject, ClassifiedAssertion> {
    @Override
    public ClassifiedAssertion convert(final DBObject source) {
        ClassifiedAssertion assertion = new ClassifiedAssertion();
        assertion.setId(parseLong(source, AssertionTags.ID_TAG));
        assertion.setDesc(parseString(source, AssertionTags.DESCRIPTION_TAG));
        return assertion;
    }
}
