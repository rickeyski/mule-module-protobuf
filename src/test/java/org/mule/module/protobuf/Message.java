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

import java.util.ArrayList;
import java.util.List;

public class Message {

    private long id;
    private String dateTime;
    private List<String> uris = new ArrayList<String>();

    public List<String> getUris() {
        return uris;
    }

    public void addUri(String uri) {
        uris.add(uri);
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
