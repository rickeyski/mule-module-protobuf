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

import org.mule.api.MuleContext;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.TransformerResolver;
import org.mule.api.annotations.param.Payload;
import org.mule.api.transformer.DataType;

import com.google.protobuf.GeneratedMessage;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Protocol Buffer Module
 *
 * @author MuleSoft, Inc.
 */
@Module(name="protobuf", schemaVersion="1.0-SNAPSHOT")
public class ProtoBufModule {

    /**
     * Parses the payload to build the specified Protocol Buffer class object
     *
     * <p/>
     * {@sample.xml ../../../doc/ProtoBuf-connector.xml.sample protobuf:parse-proto}
     *
     *
     * @param payload Payload to process
     * @param protoBufClass Name of protocol buffer class
     * @return an instance of the specified class built from the incoming payload
     */
    @Processor
    public Object parseProto(@Payload Object payload, String protoBufClass) {
        if(payload instanceof InputStream || payload instanceof byte[]) {
            try {
                Class clazz = Class.forName(protoBufClass);
                Method parseFromMethod = clazz.getDeclaredMethod("parseFrom", payload.getClass());
                Object protobufResult = parseFromMethod.invoke(null, payload);
                return protobufResult;
            } catch (Exception e) {
                throw new RuntimeException("Unable to parse protocol buffer class", e);
            }
        }
        return payload;
    }

    /**
     *
     * Transformer Resolver to handle Protocol Buffer types
     *
     * @param source the source data type
     * @param result the result data type
     * @param muleContext the MuleContext
     * @return a Transformer or null
     * @throws Exception if an error occurs
     */
    @TransformerResolver
    public static org.mule.api.transformer.Transformer ProtobufTransformerResolver(DataType source, DataType result, MuleContext muleContext) throws Exception {
        org.mule.api.transformer.Transformer t = null;

        if(source.getType().getSuperclass().equals(GeneratedMessage.class)) {
            if(result.getType().equals(InputStream.class)) {
                t = new ProtobufToInputStream();
            } else if(result.getType().equals(byte[].class)) {
                t = new ProtobufToByteArray();
            }
        } else if(result.getType().getSuperclass().equals(GeneratedMessage.class)) {
            if(source.getType().equals(InputStream.class) || source.getType().equals(byte[].class)) {
                t = new ObjectToProtobuf();
                t.setReturnDataType(result);
            }
        }

        if(t != null) {
            muleContext.getRegistry().applyProcessorsAndLifecycle(t);
        }

        return t;
    }

    //@Transformer (sourceTypes = {Packet.class})
    //public static byte[] PacketToByteArray(Packet packet) {
    //    if(packet != null) {
    //        return packet.toByteArray();
    //    }
    //    return null;
    //}
    //
    //@Transformer (sourceTypes = {byte[].class})
    //public static Packet ByteArrayToPacket(byte[] byteArray) throws Exception {
    //    if(byteArray != null) {
    //        return Packet.parseFrom(byteArray);
    //    }
    //    return null;
    //}
    //
    //@Transformer (sourceTypes = {InputStream.class})
    //public static Packet InputStreamToPacket(InputStream input) throws Exception {
    //    if(input != null) {
    //        return Packet.parseFrom(input);
    //    }
    //    return null;
    //}
    //
    //@Transformer (sourceTypes = {Packet.class})
    //public static InputStream PacketToInputStream(Packet packet) {
    //    if(packet != null) {
    //        return new ByteArrayInputStream(packet.toByteArray());
    //    }
    //    return null;
    //}
}
