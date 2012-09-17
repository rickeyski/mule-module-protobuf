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
import static org.junit.Assert.assertTrue;

public class ProtoModuleTest extends FunctionalTestCase {

    private static final String VALID_MESSAGE = "VALID";

    @Override
    protected String getConfigResources() {
        return "mule-config.xml";
    }

    @Test
    public void testDeserialize() throws Exception {
        Packet packet = Packet.newBuilder().setMessage(TEST_MESSAGE).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
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
    public void testTimeFilter() throws Exception {
        Packet packet = Packet.newBuilder().setMessage(TEST_MESSAGE).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
        runFlowWithPayload("timeFilter", VALID_MESSAGE, packet.toByteArray());
    }

    @Test
    public void testTimeFilterExpired() throws Exception {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.HOUR, -1);
        Packet packet = Packet.newBuilder().setMessage(TEST_MESSAGE).setDateTime(ProtoBufModule.DATE_FORMAT.format(calendar.getTime())).build();
        MuleEvent response = runFlowWithPayload("timeFilter", packet.toByteArray());
        assertTrue(response.getMessage().getPayload() instanceof NullPayload);
    }

    @Test
    public void testSerializeToInputStreamTransformer() throws Exception {
        Packet packet = Packet.newBuilder().setMessage(TEST_MESSAGE).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
        MuleEvent response = runFlowWithPayload("serializeToInputStream", packet);
        assertEquals(packet, Packet.parseFrom((InputStream) response.getMessage().getPayload()));
    }

    @Test
    public void testSerializeToByteArrayTransformer() throws Exception {
        Packet packet = Packet.newBuilder().setMessage(TEST_MESSAGE).setDateTime(ProtoBufModule.DATE_FORMAT.format(new Date())).build();
        MuleEvent response = runFlowWithPayload("serializeToByteArray", packet);
        assertEquals(packet, Packet.parseFrom((byte[]) response.getMessage().getPayload()));
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
