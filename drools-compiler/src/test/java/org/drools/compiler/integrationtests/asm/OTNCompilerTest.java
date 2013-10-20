package org.drools.compiler.integrationtests.asm;

import java.util.List;

import org.drools.compiler.CommonTestMethodBase;
import org.drools.compiler.Person;
import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.reteoo.compiled.ObjectTypeNodeCompiler;
import org.drools.core.base.ClassObjectType;
import org.drools.core.common.InternalRuleBase;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.drools.core.reteoo.ObjectTypeNode;
import org.drools.core.reteoo.compiled.CompiledNetwork;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.builder.conf.RuleEngineOption;
import org.kie.internal.io.ResourceFactory;

public class OTNCompilerTest extends CommonTestMethodBase {

	@Test
	public void nonHashedAlphaNodes() {

		String drl = "";
		drl += "package org.drools.compiler.test\n";
		drl += "import org.drools.compiler.Person\n";
		drl += "rule test1\n";
		drl += "when\n";
		drl += "   Person(name == \"Leo\", age == 32)\n";
		drl += "   Person(name == \"Leo\", happy == true, likes == 'mustard')\n";
		drl += "then\n";
		drl += "end\n";
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		KieBase kbase = loadKieBase(kbuilder, phreak, drl);

		ObjectTypeNode otn = getObjectTypeNode(kbase, Person.class);

		CompiledNetwork compiledNetwork = ObjectTypeNodeCompiler.compile(
				((KnowledgeBuilderImpl) kbuilder).getPackageBuilder(), otn);

		assertNotNull(compiledNetwork);

	}

	@Test
	public void hashedAlphaNodes() {

		String drl = "";
		drl += "package org.drools.compiler.test\n";
		drl += "import org.drools.compiler.Person\n";
		drl += "rule test1\n";
		drl += "when\n";
		drl += "   Person(name == 'Leo')\n";
		drl += "then\n";
		drl += "end\n";
		drl += "rule test2\n";
		drl += "when\n";
		drl += "   Person(name == 'Ana')\n";
		drl += "then\n";
		drl += "end\n";
		drl += "rule test3\n";
		drl += "when\n";
		drl += "   Person(name == 'Jose')\n";
		drl += "then\n";
		drl += "end\n";
		drl += "rule test4\n";
		drl += "when\n";
		drl += "   Person(name == 'Maria')\n";
		drl += "then\n";
		drl += "end\n";

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		KieBase kbase = loadKieBase(kbuilder, phreak, drl);

		ObjectTypeNode otn = getObjectTypeNode(kbase, Person.class);

		CompiledNetwork compiledNetwork = ObjectTypeNodeCompiler.compile(
				((KnowledgeBuilderImpl) kbuilder).getPackageBuilder(), otn);

		assertNotNull(compiledNetwork);

	}

	@Test
	public void betaNodes() {

		String drl = "";
		drl += "package org.drools.compiler.test\n";
		drl += "import org.drools.compiler.Person\n";
		drl += "rule test1\n";
		drl += "when\n";
		drl += "   $p1: Person(name == 'Leo')\n";
		drl += "   $p2: Person(name == $p1.name)\n";
		drl += "then\n";
		drl += "end\n";

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		KieBase kbase = loadKieBase(kbuilder, phreak, drl);

		ObjectTypeNode otn = getObjectTypeNode(kbase, Person.class);

		CompiledNetwork compiledNetwork = ObjectTypeNodeCompiler.compile(
				((KnowledgeBuilderImpl) kbuilder).getPackageBuilder(), otn);

		assertNotNull(compiledNetwork);

	}

	private ObjectTypeNode getObjectTypeNode(KieBase kbase, Class<?> nodeClass) {
		List<ObjectTypeNode> nodes = ((InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase)
				.getRete().getObjectTypeNodes();
		
		for (ObjectTypeNode n : nodes) {
			if (((ClassObjectType) n.getObjectType()).getClassType() == nodeClass) {
				return n;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	protected KieBase loadKieBase(KnowledgeBuilder kbuilder,
			RuleEngineOption phreak, String... drlContentStrings) {

		for (String drlContentString : drlContentStrings) {
			kbuilder.add(ResourceFactory.newByteArrayResource(drlContentString
					.getBytes()), ResourceType.DRL);
		}

		if (kbuilder.hasErrors()) {
			fail(kbuilder.getErrors().toString());
		}
		// KieBaseConfiguration kBaseConfig =
		// KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		//
		// kBaseConfig.setOption(phreak);
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return (KieBase) kbase;
	}

}
