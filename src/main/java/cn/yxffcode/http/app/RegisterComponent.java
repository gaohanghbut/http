package cn.yxffcode.http.app;

import cn.yxffcode.http.api.WebComponent;
import cn.yxffcode.http.api.RequestContext;

import java.io.IOException;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class RegisterComponent implements WebComponent {
    public void init() {

    }

    public void onRequest(RequestContext ctx) throws IOException {
        String userid   = ctx.getParameter("userid");
        String username = ctx.getParameter("username");
        ctx.writeString("<h1>userid: " + userid + "</h1>");
        ctx.writeString("<h1>username: " + username + "</h1>");
    }

    public void destroy() {

    }
}
