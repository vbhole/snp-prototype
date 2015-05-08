package com.github.jlgrock.snp.core.classifier;

import com.github.jlgrock.snp.core.model.xml.fhir.Condition;
import org.jvnet.hk2.annotations.Service;

import java.util.UUID;

/**
 * Replace Post Coordinated Expressions in Logic Graph with Classifier ID
 *
 */
@Service
public class FhirLogicGraphClassifierImpl implements LogicGraphClassifier<Condition> {

	@Override
	public UUID classify(final Condition pce) {

		//Set the expression from the
		//FhirLogicGraphBuilder fhirLogicGraphBuilder = new FhirLogicGraphBuilder(pce);
		//LogicGraph logicGraph = (LogicGraph)fhirLogicGraphBuilder;

		// TODO : call a service that takes a LogicGraph and returns a classifier ID
		//UUID classifierID = vaLogicService.classify(logicGraph);
		return UUID.randomUUID();
	}
	
}