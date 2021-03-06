// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Teacher.proto

package com.tenglu.ch10;

/**
 * Protobuf type {@code com.tenglu.ch10.TeacherResponse}
 */
public  final class TeacherResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:com.tenglu.ch10.TeacherResponse)
    TeacherResponseOrBuilder {
  // Use TeacherResponse.newBuilder() to construct.
  private TeacherResponse(com.google.protobuf.GeneratedMessage.Builder builder) {
    super(builder);
  }
  private TeacherResponse() {
    realname_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TeacherResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();

            realname_ = bs;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.tenglu.ch10.TeacherPtoro.internal_static_com_tenglu_ch10_TeacherResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.tenglu.ch10.TeacherPtoro.internal_static_com_tenglu_ch10_TeacherResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.tenglu.ch10.TeacherResponse.class, com.tenglu.ch10.TeacherResponse.Builder.class);
  }

  public static final int REALNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object realname_;
  /**
   * <code>optional string realname = 2;</code>
   */
  public java.lang.String getRealname() {
    java.lang.Object ref = realname_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        realname_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string realname = 2;</code>
   */
  public com.google.protobuf.ByteString
      getRealnameBytes() {
    java.lang.Object ref = realname_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      realname_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getRealnameBytes().isEmpty()) {
      output.writeBytes(2, getRealnameBytes());
    }
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (!getRealnameBytes().isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, getRealnameBytes());
    }
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static com.tenglu.ch10.TeacherResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.tenglu.ch10.TeacherResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.tenglu.ch10.TeacherResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.tenglu.ch10.TeacherResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.tenglu.ch10.TeacherResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.tenglu.ch10.TeacherResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.tenglu.ch10.TeacherResponse)
      com.tenglu.ch10.TeacherResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tenglu.ch10.TeacherPtoro.internal_static_com_tenglu_ch10_TeacherResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tenglu.ch10.TeacherPtoro.internal_static_com_tenglu_ch10_TeacherResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tenglu.ch10.TeacherResponse.class, com.tenglu.ch10.TeacherResponse.Builder.class);
    }

    // Construct using com.tenglu.ch10.TeacherResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      realname_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.tenglu.ch10.TeacherPtoro.internal_static_com_tenglu_ch10_TeacherResponse_descriptor;
    }

    public com.tenglu.ch10.TeacherResponse getDefaultInstanceForType() {
      return com.tenglu.ch10.TeacherResponse.getDefaultInstance();
    }

    public com.tenglu.ch10.TeacherResponse build() {
      com.tenglu.ch10.TeacherResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.tenglu.ch10.TeacherResponse buildPartial() {
      com.tenglu.ch10.TeacherResponse result = new com.tenglu.ch10.TeacherResponse(this);
      result.realname_ = realname_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.tenglu.ch10.TeacherResponse) {
        return mergeFrom((com.tenglu.ch10.TeacherResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.tenglu.ch10.TeacherResponse other) {
      if (other == com.tenglu.ch10.TeacherResponse.getDefaultInstance()) return this;
      if (!other.getRealname().isEmpty()) {
        realname_ = other.realname_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.tenglu.ch10.TeacherResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.tenglu.ch10.TeacherResponse) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object realname_ = "";
    /**
     * <code>optional string realname = 2;</code>
     */
    public java.lang.String getRealname() {
      java.lang.Object ref = realname_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          realname_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string realname = 2;</code>
     */
    public com.google.protobuf.ByteString
        getRealnameBytes() {
      java.lang.Object ref = realname_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        realname_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string realname = 2;</code>
     */
    public Builder setRealname(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      realname_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string realname = 2;</code>
     */
    public Builder clearRealname() {
      
      realname_ = getDefaultInstance().getRealname();
      onChanged();
      return this;
    }
    /**
     * <code>optional string realname = 2;</code>
     */
    public Builder setRealnameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      realname_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.tenglu.ch10.TeacherResponse)
  }

  // @@protoc_insertion_point(class_scope:com.tenglu.ch10.TeacherResponse)
  private static final com.tenglu.ch10.TeacherResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.tenglu.ch10.TeacherResponse();
  }

  public static com.tenglu.ch10.TeacherResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static final com.google.protobuf.Parser<TeacherResponse> PARSER =
      new com.google.protobuf.AbstractParser<TeacherResponse>() {
    public TeacherResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new TeacherResponse(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  @java.lang.Override
  public com.google.protobuf.Parser<TeacherResponse> getParserForType() {
    return PARSER;
  }

  public com.tenglu.ch10.TeacherResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

