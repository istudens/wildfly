/*
* JBoss, Home of Professional Open Source.
* Copyright 2011, Red Hat Middleware LLC, and individual contributors
* as indicated by the @author tags. See the copyright.txt file in the
* distribution for a full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.as.host.controller.model.jvm;

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.jboss.as.controller.AttributeDefinition;
import org.jboss.as.controller.AttributeMarshaller;
import org.jboss.as.controller.PropertiesAttributeDefinition;
import org.jboss.as.controller.SimpleAttributeDefinition;
import org.jboss.as.controller.SimpleAttributeDefinitionBuilder;
import org.jboss.as.controller.StringListAttributeDefinition;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.controller.operations.validation.EnumValidator;
import org.jboss.as.controller.operations.validation.StringLengthValidator;
import org.jboss.as.controller.parsing.Element;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

/**
 *
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 */
public class JvmAttributes {

    public static final String JVM_AGENT_LIB = "agent-lib";
    public static final String JVM_AGENT_PATH = "agent-path";
    public static final String JVM_DEBUG_ENABLED = "debug-enabled";
    public static final String JVM_DEBUG_OPTIONS = "debug-options";
    public static final String JVM_ENV_CLASSPATH_IGNORED = "env-classpath-ignored";
    public static final String JVM_ENV_VARIABLES = "environment-variables";
    public static final String JVM_HEAP = "heap-size";
    public static final String JVM_MAX_HEAP = "max-heap-size";
    public static final String JVM_JAVA_AGENT = "java-agent";
    public static final String JVM_JAVA_HOME = "java-home";
    public static final String JVM_OPTIONS = "jvm-options";
    public static final String JVM_OPTION = "jvm-option";
    public static final String JVM_PERMGEN = "permgen-size";
    public static final String JVM_MAX_PERMGEN = "max-permgen-size";
    public static final String JVM_STACK = "stack-size";
    public static final String JVM_TYPE = "type";
    public static final String SIZE = "size";
    public static final String VALUE = "value";
    public static final String MAX_SIZE = "max-size";


    public static final SimpleAttributeDefinition AGENT_LIB =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_AGENT_LIB, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.VALUE)
            .build();
    public static final SimpleAttributeDefinition AGENT_PATH =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_AGENT_PATH, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.VALUE)
            .build();
    public static final SimpleAttributeDefinition ENV_CLASSPATH_IGNORED =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_ENV_CLASSPATH_IGNORED, ModelType.BOOLEAN, true)
            .setAllowExpression(false)
            .build();

    public static final PropertiesAttributeDefinition ENVIRONMENT_VARIABLES = new PropertiesAttributeDefinition.Builder(JvmAttributes.JVM_ENV_VARIABLES, true)
            .setXmlName(Element.VARIABLE.getLocalName())
            .setAllowNull(true)
            .setValidator(new StringLengthValidator(1, true))
            .setWrapperElement(JvmAttributes.JVM_ENV_VARIABLES)
            .build();

    public static final SimpleAttributeDefinition JAVA_AGENT =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_JAVA_AGENT, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.VALUE)
            .build();

    public static final SimpleAttributeDefinition JAVA_HOME =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_JAVA_HOME, ModelType.STRING, true)
            .setAllowExpression(false)
            .build();

    public static final AttributeDefinition OPTIONS = new StringListAttributeDefinition.Builder(JvmAttributes.JVM_OPTIONS)
            .setValidator(new StringLengthValidator(1, true, true))
            .setAllowExpression(true)
            .setAllowNull(true)
            .setAttributeMarshaller(new AttributeMarshaller() {
                @Override
                public void marshallAsElement(AttributeDefinition attribute, ModelNode resourceModel, boolean marshallDefault, XMLStreamWriter writer) throws XMLStreamException {
                    if (resourceModel.hasDefined(attribute.getName())) {
                        List<ModelNode> list = resourceModel.get(attribute.getName()).asList();
                        if (list.size() > 0) {
                            writer.writeStartElement(attribute.getName());
                            for (ModelNode child : list) {
                                writer.writeEmptyElement(Element.OPTION.getLocalName());
                                writer.writeAttribute(ModelDescriptionConstants.VALUE, child.asString());
                            }
                            writer.writeEndElement();
                        }
                    }
                }
            })
            .build();

    public static final SimpleAttributeDefinition STACK_SIZE =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_STACK, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.SIZE)
            .build();

    public static final SimpleAttributeDefinition TYPE =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_TYPE, ModelType.STRING, true)
            .setAllowExpression(true)
            .setValidator(new EnumValidator<JvmType>(JvmType.class, true, true))
            .build();

    public static final SimpleAttributeDefinition HEAP_SIZE =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_HEAP, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.SIZE)
            .build();

    public static final SimpleAttributeDefinition MAX_HEAP_SIZE =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_MAX_HEAP, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.MAX_SIZE)
            .build();

    public static final SimpleAttributeDefinition PERMGEN_SIZE =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_PERMGEN, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.SIZE)
            .build();

    public static final SimpleAttributeDefinition MAX_PERMGEN_SIZE =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_MAX_PERMGEN, ModelType.STRING, true)
            .setAllowExpression(false)
            .setXmlName(JvmAttributes.MAX_SIZE)
            .build();

    public static final SimpleAttributeDefinition DEBUG_ENABLED =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_DEBUG_ENABLED, ModelType.BOOLEAN, true)
            .setAllowExpression(false)
            .build();

    public static final SimpleAttributeDefinition DEBUG_OPTIONS =
            SimpleAttributeDefinitionBuilder.create(JvmAttributes.JVM_DEBUG_OPTIONS, ModelType.STRING, true)
            .setAllowExpression(false)
            .build();

    private static final AttributeDefinition[] GLOBAL_ATTRIBUTES = new AttributeDefinition[] {
        AGENT_LIB, AGENT_PATH, ENV_CLASSPATH_IGNORED, ENVIRONMENT_VARIABLES, JAVA_AGENT, JAVA_HOME,
        OPTIONS, STACK_SIZE, TYPE, HEAP_SIZE, MAX_HEAP_SIZE, PERMGEN_SIZE, MAX_PERMGEN_SIZE};

    private static final AttributeDefinition[] SERVER_ATTRIBUTES = new AttributeDefinition[] {
        AGENT_LIB, AGENT_PATH, ENV_CLASSPATH_IGNORED, ENVIRONMENT_VARIABLES, JAVA_AGENT, JAVA_HOME,
        OPTIONS, STACK_SIZE, TYPE, HEAP_SIZE, MAX_HEAP_SIZE, PERMGEN_SIZE, MAX_PERMGEN_SIZE,
        DEBUG_ENABLED, DEBUG_OPTIONS};

    static AttributeDefinition[] getAttributes(boolean server) {
        return server ? SERVER_ATTRIBUTES : GLOBAL_ATTRIBUTES;
    }
}
