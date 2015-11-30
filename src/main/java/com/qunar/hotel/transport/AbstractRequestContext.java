package com.qunar.hotel.transport;

import com.qunar.hotel.api.RequestContext;
import com.qunar.hotel.api.ResponseContainer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hang.gao on 2015/6/9.
 */
public abstract class AbstractRequestContext implements RequestContext, ResponseContainer {
    protected Map<String, Object>   attachments;
    private   ByteArrayOutputStream os;
    private   boolean               error;
    private   String                errorMsg;
    private String contentType = "text/html";

    public abstract String getRemoteAddress();

    public void writeString(String content) throws IOException {
        if (error) {
            return;
        }
        if (os == null) {
            os = new ByteArrayOutputStream();
        }
        os.write(content.getBytes());
    }

    public void error(int code,
                      String msg) {
        error = true;
        errorMsg = "<h1>" + code + ":" + msg + "<h1>";
    }

    public boolean isError() {
        return error;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getResponseContent() {
        if (error) {
            return errorMsg.getBytes();
        }
        if (os == null) {
            return new byte[0];
        }
        return os.toByteArray();
    }
}
