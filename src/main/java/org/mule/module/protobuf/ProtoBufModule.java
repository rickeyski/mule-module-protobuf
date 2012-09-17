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
import org.mule.api.annotations.Transformer;
import org.mule.api.annotations.TransformerResolver;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.param.Payload;
import org.mule.api.callback.SourceCallback;
import org.mule.api.transformer.DataType;

import com.google.protobuf.GeneratedMessage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Protocol Buffer Module
 *
 * @author MuleSoft, Inc.
 */
@Module(name="protobuf", schemaVersion="1.0-SNAPSHOT")
public class ProtoBufModule {

    protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    /**
     * Parses the payload to build the specified Protocol Buffer class object. Accepted payload types are InputStream or
     * byte[].
     *
     * <p/>
     * {@sample.xml ../../../doc/ProtoBuf-connector.xml.sample protobuf:to-protobuf-transformer}
     *
     *
     * @param payload Payload to process
     * @param protobufClass Name of protocol buffer class
     * @return an instance of the specified class built from the incoming payload
     */
    @Processor
    public Object toProtobufTransformer(@Payload Object payload, String protobufClass) {
        if(payload instanceof InputStream || payload instanceof byte[]) {
            try {
                Class clazz = Class.forName(protobufClass);
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
     * Serializes a Protocol Buffer instance into an InputStream
     *
     * <p/>
     * {@sample.xml ../../../doc/ProtoBuf-connector.xml.sample protobuf:protobuf-to-input-stream-transformer}
     *
     * @param protobuf the Protocol Buffer object to transform
     * @return an InputStream representation of the given object
     */
    @Transformer(sourceTypes = Object.class)
    public static InputStream protobufToInputStreamTransformer(Object protobuf) {
        if(protobuf != null && protobuf instanceof GeneratedMessage) {
            return new ByteArrayInputStream(((GeneratedMessage)protobuf).toByteArray());
        }
        return null;
    }

    /**
     * Serialized a Protocol Buffer instance into a Byte Array
     *
     * <p/>
     * {@sample.xml ../../../doc/ProtoBuf-connector.xml.sample protobuf:protobuf-to-byte-array-transformer}
     *
     * @param protobuf the Protocol Buffer object to transform
     * @return a byte array representation of the given object
     */
    @Transformer (sourceTypes = Object.class)
    public static byte[] protobufToByteArrayTransformer(Object protobuf) {
        if(protobuf != null && protobuf instanceof GeneratedMessage) {
            return ((GeneratedMessage)protobuf).toByteArray();
        }
        return null;
    }

    /**
     * Filters expired messages according to maximum seconds allowed
     *
     * <p/>
     * {@sample.xml ../../../doc/ProtoBuf-connector.xml.sample protobuf:time-filter}
     *
     * @param dateTime The time of the message
     * @param maxSeconds The maximum amount of seconds until a message is considered to be expired
     * @param afterChain the after chain
     * @return the after chain process result
     * @throws Exception if an exception occurs
     */
    @Processor(intercepting = true)
    public Object timeFilter(String dateTime, @Optional @Default("30") int maxSeconds,  SourceCallback afterChain) throws Exception {
        long currentTime = System.currentTimeMillis();
        Date date = DATE_FORMAT.parse(dateTime);
        if(((currentTime - date.getTime())) < (maxSeconds*1000)) {
            return afterChain.process();
        }
        return null;
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

        if(isProtobufClass(source.getType())) {
            if(result.getType().equals(InputStream.class)) {
                t = new ProtobufToInputStream();
            } else if(result.getType().equals(byte[].class)) {
                t = new ProtobufToByteArray();
            }
        } else if(isProtobufClass(result.getType())) {
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

    private static boolean isProtobufClass(Class clazz) {
        return clazz.getSuperclass() != null && clazz.getSuperclass().equals(GeneratedMessage.class);
    }

}
