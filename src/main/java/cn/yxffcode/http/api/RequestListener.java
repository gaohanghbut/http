package cn.yxffcode.http.api;

/**
 * Created by hang.gao on 2015/6/10.
 */
public interface RequestListener {

    void onRequestStart(RequestContext ctx);

    void onRequestEnd(RequestContext ctx);
}
