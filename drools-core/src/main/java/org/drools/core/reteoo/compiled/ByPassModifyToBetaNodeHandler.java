package org.drools.core.reteoo.compiled;

import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.reteoo.ModifyPreviousTuples;
import org.drools.core.reteoo.ObjectTypeNode;
import org.drools.core.spi.PropagationContext;

public class ByPassModifyToBetaNodeHandler extends AbstractCompilerHandler {

	private final StringBuilder builder;
	
    private static final String FACT_HANDLE_PARAM_TYPE = InternalFactHandle.class.getName();
    private static final String MODIFY_PREVIOUS_TUPLES_PARAM_TYPE = ModifyPreviousTuples.class.getName();
    private static final String PROP_CONTEXT_PARAM_TYPE = PropagationContext.class.getName();
    private static final String WORKING_MEMORY_PARAM_TYPE = InternalWorkingMemory.class.getName();

    private static final String FACT_HANDLE_PARAM_NAME = "handle";
    private static final String MODIFY_PREVIOUS_TUPLES_PARAM_NAME = "modifyPreviousTuples";
    private static final String PROP_CONTEXT_PARAM_NAME = "context";
    private static final String WORKING_MEMORY_PARAM_NAME = "wm";

    private static final String BYPASS_METHOD_SIGNATURE = "public final void byPassModifyToBetaNode("
            + FACT_HANDLE_PARAM_TYPE + " " + FACT_HANDLE_PARAM_NAME + ","
            + MODIFY_PREVIOUS_TUPLES_PARAM_TYPE + " " + MODIFY_PREVIOUS_TUPLES_PARAM_NAME + ","
            + PROP_CONTEXT_PARAM_TYPE + " " + PROP_CONTEXT_PARAM_NAME + ","
            + WORKING_MEMORY_PARAM_TYPE + " " + WORKING_MEMORY_PARAM_NAME + "){";


	public ByPassModifyToBetaNodeHandler(StringBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void startObjectTypeNode(ObjectTypeNode objectTypeNode) {
		builder.append(BYPASS_METHOD_SIGNATURE)
				.append(NEWLINE).append("// Do I need to implement this?")
				.append(NEWLINE);
	}

	public void endObjectTypeNode(ObjectTypeNode objectTypeNode) {
		// close the method
		builder.append("}").append(NEWLINE);
	}

}
