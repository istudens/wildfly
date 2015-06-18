/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
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

package org.wildfly.extension.messaging.activemq;

import static org.jboss.as.controller.OperationContext.Stage.MODEL;

import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ReloadRequiredWriteAttributeHandler;
import org.jboss.dmr.ModelNode;

/**
 * Write attribute handler for attributes that update a bridge resource.
 *
 * @author Brian Stansberry (c) 2011 Red Hat Inc.
 */
public class BridgeWriteAttributeHandler extends ReloadRequiredWriteAttributeHandler {

    public static final BridgeWriteAttributeHandler INSTANCE = new BridgeWriteAttributeHandler();

    private BridgeWriteAttributeHandler() {
        super(BridgeDefinition.ATTRIBUTES);
    }

    @Override
    public void execute(OperationContext context, ModelNode operation) throws OperationFailedException {
        context.addStep(new AlternativeAttributeCheckHandler(BridgeDefinition.ATTRIBUTES), MODEL);

        super.execute(context, operation);
    }
}
