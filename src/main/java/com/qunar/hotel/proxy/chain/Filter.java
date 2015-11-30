package com.qunar.hotel.proxy.chain;

import com.qunar.hotel.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/8.
 */
public interface Filter {

    boolean doFilter(RequestContext ctx) throws IOException;
}
