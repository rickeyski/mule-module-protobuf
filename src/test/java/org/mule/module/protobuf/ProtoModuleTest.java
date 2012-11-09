/**
 * Protobuf Module
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */


package org.mule.module.protobuf;

import org.mule.api.MuleEvent;
import org.mule.api.registry.TransformerResolver;
import org.mule.api.transformer.Transformer;
import org.mule.construct.Flow;
import org.mule.module.protocol.generated.Content;
import org.mule.module.protocol.generated.Packet;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transformer.types.DataTypeFactory;
import org.mule.transport.NullPayload;


import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProtoModuleTest extends FunctionalTestCase {

    private static final String VALID_MESSAGE = "VALID";

    @Override
    protected String getConfigResources() {
        return "mule-config.xml";
    }

    @Test
    public void testDeserialize() throws Exception {
        Packet packet = Packet.newBuilder().setContent(Content.newBuilder().setId(1234)).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
        runFlowWithPayload("deserialize", packet, packet.toByteArray());
    }

    @Test
    public void testTransformerResolver() throws Exception {
        List<TransformerResolver> resolvers = (List<TransformerResolver>) muleContext.getRegistry().lookupObjects(TransformerResolver.class);
        assertEquals(2, resolvers.size());
        assertTrue(containsTransformerResolver(resolvers));

        Transformer transformer = muleContext.getRegistry().lookupTransformer(DataTypeFactory.create(InputStream.class), DataTypeFactory.create(Packet.class));
        assertTrue(transformer instanceof ObjectToProtobuf);
    }

    @Test
    public void testSerializeToInputStreamTransformer() throws Exception {
        Packet packet = Packet.newBuilder().setContent(Content.newBuilder().setId(1234)).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
        MuleEvent response = runFlowWithPayload("serializeToInputStream", packet);
        assertEquals(packet, Packet.parseFrom((InputStream) response.getMessage().getPayload()));
    }

    @Test
    public void testSerializeToByteArrayTransformer() throws Exception {
        Packet packet = Packet.newBuilder().setContent(Content.newBuilder().setId(1234)).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
        MuleEvent response = runFlowWithPayload("serializeToByteArray", packet);
        assertEquals(packet, Packet.parseFrom((byte[]) response.getMessage().getPayload()));
    }

    @Test
    public void testMessageBuilderTransformer() throws Exception {
        Message message = new Message();
        message.addUri("http://localhost:8080/demo");
        message.addUri("http://localhost:8081/demo2");
        message.setId(1234);
        message.setDateTime(new Date().toString());
        MuleEvent response = runFlowWithPayload("messageBuilderTransformer", message);
        assertTrue(response.getMessage().getPayload() instanceof Packet);
        Packet packet = (Packet) response.getMessage().getPayload();
        assertEquals(message.getId(), packet.getContent().getId());
        assertEquals(message.getDateTime(), packet.getDateTime());
        assertEquals(message.getUris(), packet.getContent().getUrisList());
    }

    @Test
    public void testSetCollection() throws Exception {
        Message message = new Message();
        message.addUri("http://localhost:8080/demo");
        message.setId(1234);

        MuleEvent response = runFlowWithPayload("testSetCollection", message);
        assertTrue(response.getMessage().getPayload() instanceof Content);
        Content content = (Content) response.getMessage().getPayload();
        assertEquals(message.getId(), content.getId());
        assertEquals(message.getUris(), content.getUrisList());
    }

    private boolean containsTransformerResolver(List<TransformerResolver> transformerResolvers) {
        for(TransformerResolver transformerResolver : transformerResolvers) {
            if(transformerResolver.getClass().getName().contains("ProtobufTransformerResolver")) {
                return true;
            }
        }
        return false;
    }

    protected <T, U> void runFlowWithPayload(String flowName, T expect, U payload) throws Exception {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = getTestEvent(payload);
        MuleEvent responseEvent = flow.process(event);
        if (responseEvent.getMessage().getExceptionPayload() != null) {
            throw new Exception(responseEvent.getMessage().getExceptionPayload().getRootException());
        }
        assertEquals(expect, responseEvent.getMessage().getPayload());
    }


    protected Flow lookupFlowConstruct(String name) {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }

    protected MuleEvent runFlowWithPayload(String flowName, Object payload) throws Exception {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = getTestEvent(payload);
        MuleEvent responseEvent = flow.process(event);
        if (responseEvent.getMessage().getExceptionPayload() != null) {
            throw new Exception(responseEvent.getMessage().getExceptionPayload().getRootException());
        }
        return responseEvent;
    }


}
