/**
 * Protobuf Module
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

// Generated by the protocol buffer compiler.  DO NOT EDIT!

package org.mule.module.protocol.generated;

public  final class Content extends
    com.google.protobuf.GeneratedMessage
    implements ContentOrBuilder {
  // Use Content.newBuilder() to construct.
  private Content(Builder builder) {
    super(builder);
  }
  private Content(boolean noInit) {}
  
  private static final Content defaultInstance;
  public static Content getDefaultInstance() {
    return defaultInstance;
  }
  
  public Content getDefaultInstanceForType() {
    return defaultInstance;
  }
  
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.mule.module.protocol.generated.Message.internal_static_Content_descriptor;
  }
  
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.mule.module.protocol.generated.Message.internal_static_Content_fieldAccessorTable;
  }
  
  private int bitField0_;
  // required int64 id = 1;
  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  public long getId() {
    return id_;
  }
  
  // repeated string uris = 2;
  public static final int URIS_FIELD_NUMBER = 2;
  private com.google.protobuf.LazyStringList uris_;
  public java.util.List<String>
      getUrisList() {
    return uris_;
  }
  public int getUrisCount() {
    return uris_.size();
  }
  public String getUris(int index) {
    return uris_.get(index);
  }
  
  private void initFields() {
    id_ = 0L;
    uris_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;
    
    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }
  
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, id_);
    }
    for (int i = 0; i < uris_.size(); i++) {
      output.writeBytes(2, uris_.getByteString(i));
    }
    getUnknownFields().writeTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;
  
    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < uris_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeBytesSizeNoTag(uris_.getByteString(i));
      }
      size += dataSize;
      size += 1 * getUrisList().size();
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }
  
  private static final long serialVersionUID = 0L;
  @java.lang.Override
  protected java.lang.Object writeReplace()
      throws java.io.ObjectStreamException {
    return super.writeReplace();
  }
  
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.mule.module.protocol.generated.Content)) {
      return super.equals(obj);
    }
    org.mule.module.protocol.generated.Content other = (org.mule.module.protocol.generated.Content) obj;
    
    boolean result = true;
    result = result && (hasId() == other.hasId());
    if (hasId()) {
      result = result && (getId()
          == other.getId());
    }
    result = result && getUrisList()
        .equals(other.getUrisList());
    result = result &&
        getUnknownFields().equals(other.getUnknownFields());
    return result;
  }
  
  @java.lang.Override
  public int hashCode() {
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + hashLong(getId());
    }
    if (getUrisCount() > 0) {
      hash = (37 * hash) + URIS_FIELD_NUMBER;
      hash = (53 * hash) + getUrisList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    return hash;
  }
  
  public static org.mule.module.protocol.generated.Content parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data).buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return newBuilder().mergeFrom(data, extensionRegistry)
             .buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static org.mule.module.protocol.generated.Content parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    Builder builder = newBuilder();
    if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
      return builder.buildParsed();
    } else {
      return null;
    }
  }
  public static org.mule.module.protocol.generated.Content parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input).buildParsed();
  }
  public static org.mule.module.protocol.generated.Content parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return newBuilder().mergeFrom(input, extensionRegistry)
             .buildParsed();
  }
  
  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(org.mule.module.protocol.generated.Content prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }
  
  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder>
     implements org.mule.module.protocol.generated.ContentOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.mule.module.protocol.generated.Message.internal_static_Content_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.mule.module.protocol.generated.Message.internal_static_Content_fieldAccessorTable;
    }
    
    // Construct using org.mule.module.protocol.generated.Content.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    private static Builder create() {
      return new Builder();
    }
    
    public Builder clear() {
      super.clear();
      id_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      uris_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    
    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }
    
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.mule.module.protocol.generated.Content.getDescriptor();
    }
    
    public org.mule.module.protocol.generated.Content getDefaultInstanceForType() {
      return org.mule.module.protocol.generated.Content.getDefaultInstance();
    }
    
    public org.mule.module.protocol.generated.Content build() {
      org.mule.module.protocol.generated.Content result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }
    
    private org.mule.module.protocol.generated.Content buildParsed()
        throws com.google.protobuf.InvalidProtocolBufferException {
      org.mule.module.protocol.generated.Content result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(
          result).asInvalidProtocolBufferException();
      }
      return result;
    }
    
    public org.mule.module.protocol.generated.Content buildPartial() {
      org.mule.module.protocol.generated.Content result = new org.mule.module.protocol.generated.Content(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        uris_ = new com.google.protobuf.UnmodifiableLazyStringList(
            uris_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.uris_ = uris_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.mule.module.protocol.generated.Content) {
        return mergeFrom((org.mule.module.protocol.generated.Content)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }
    
    public Builder mergeFrom(org.mule.module.protocol.generated.Content other) {
      if (other == org.mule.module.protocol.generated.Content.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (!other.uris_.isEmpty()) {
        if (uris_.isEmpty()) {
          uris_ = other.uris_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureUrisIsMutable();
          uris_.addAll(other.uris_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
    
    public final boolean isInitialized() {
      if (!hasId()) {
        
        return false;
      }
      return true;
    }
    
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder(
          this.getUnknownFields());
      while (true) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            this.setUnknownFields(unknownFields.build());
            onChanged();
            return this;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            id_ = input.readInt64();
            break;
          }
          case 18: {
            ensureUrisIsMutable();
            uris_.add(input.readBytes());
            break;
          }
        }
      }
    }
    
    private int bitField0_;
    
    // required int64 id = 1;
    private long id_ ;
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public long getId() {
      return id_;
    }
    public Builder setId(long value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0L;
      onChanged();
      return this;
    }
    
    // repeated string uris = 2;
    private com.google.protobuf.LazyStringList uris_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureUrisIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        uris_ = new com.google.protobuf.LazyStringArrayList(uris_);
        bitField0_ |= 0x00000002;
       }
    }
    public java.util.List<String>
        getUrisList() {
      return java.util.Collections.unmodifiableList(uris_);
    }
    public int getUrisCount() {
      return uris_.size();
    }
    public String getUris(int index) {
      return uris_.get(index);
    }
    public Builder setUris(
        int index, String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureUrisIsMutable();
      uris_.set(index, value);
      onChanged();
      return this;
    }
    public Builder addUris(String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureUrisIsMutable();
      uris_.add(value);
      onChanged();
      return this;
    }
    public Builder addAllUris(
        java.lang.Iterable<String> values) {
      ensureUrisIsMutable();
      super.addAll(values, uris_);
      onChanged();
      return this;
    }
    public Builder clearUris() {
      uris_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    void addUris(com.google.protobuf.ByteString value) {
      ensureUrisIsMutable();
      uris_.add(value);
      onChanged();
    }
    
    // @@protoc_insertion_point(builder_scope:Content)
  }
  
  static {
    defaultInstance = new Content(true);
    defaultInstance.initFields();
  }
  
  // @@protoc_insertion_point(class_scope:Content)
}

