package org.drools.core.reteoo.compiled;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.reteoo.ObjectTypeNode;

public class GetAssociationsHandler extends AbstractCompilerHandler {

	private final StringBuilder builder;

	private static final String MAP_PARAM_TYPE = Map.class.getName();
	private static final String HASHMAP_PARAM_TYPE = HashMap.class.getName();

	private static final String GET_ASSOCIATIONS_METHOD_SIGNATURE = "public "
			+ MAP_PARAM_TYPE + " getAssociations() {";

	public GetAssociationsHandler(StringBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void startObjectTypeNode(ObjectTypeNode objectTypeNode) {
		builder.append(GET_ASSOCIATIONS_METHOD_SIGNATURE).append(NEWLINE)
				.append("return ").append("new ").append(HASHMAP_PARAM_TYPE)
				.append("(0);").append(NEWLINE);
	}

	public void endObjectTypeNode(ObjectTypeNode objectTypeNode) {
		// close the method
		builder.append("}").append(NEWLINE);
	}

}
