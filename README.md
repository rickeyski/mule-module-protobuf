Protobuf Module
===========

A Mule module for handling Google Protocol Buffer objects in flows. The module includes a custom Transformer Resolver for
protocol buffer objects and also provides the functionality to serialize and deserialize them explicitly.

Installation
------------

The module can either be installed for all applications running within the Mule instance or can be setup to be used
for a single application.

*All Applications*

Download the module from the link above and place the resulting jar file in
/lib/user directory of the Mule installation folder.

*Single Application*

To make the module available only to single application then place it in the
lib directory of the application otherwise if using Maven to compile and deploy
your application the following can be done:

Add the connector's maven repo to your pom.xml:

    <repositories>
        <repository>
            <id>mulesoft-snapshots</id>
            <name>MuleForge Snapshot Repository</name>
            <url>https://repository.mulesoft.org/snapshots/</url>
            <layout>default</layout>
        </repsitory>
    </repositories>

Add the connector as a dependency to your project. This can be done by adding
the following under the dependencies element in the pom.xml file of the
application:

    <dependency>
        <groupId>org.mule.modules</groupId>
        <artifactId>mule-module-protobuf</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

Usage
-----

Protocol Buffers are a way of serializing structured data in an efficient format. For more on Google Protocol Buffers see
https://developers.google.com/protocol-buffers/.

The module provides a way to serialize and deserialize protocol buffer objects.

<protobuf:deserialize protobufClass="org.mule.module.protocol.generated.Packet"/>

<protobuf:serialize-to-input-stream />

<protobuf:serialize-to-byte-array />