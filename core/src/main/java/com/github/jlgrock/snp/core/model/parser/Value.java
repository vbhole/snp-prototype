package com.github.jlgrock.snp.core.model.parser;

import com.google.common.base.MoreObjects;

/**
 * The Value class represents the value element in the LEGO XML document.
 *
 */
public class Value {
	private Expression expression;
	private Measurement measurement;
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(final Expression pExpression) {
		expression = pExpression;
	}
	
	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(final Measurement pMeasurement) {
		measurement = pMeasurement;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("expression", expression)
				.add("measurement", measurement)
				.toString();
	}
	
}
