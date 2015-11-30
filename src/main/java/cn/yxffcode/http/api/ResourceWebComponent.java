package cn.yxffcode.http.api;

import cn.yxffcode.http.common.WebAppConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hang.gao on 2015/6/9.
 */
public class ResourceWebComponent implements WebComponent {

    private String resourceBase;

    private Map<String, String> cache = new ConcurrentHashMap<String, String>();

    public void init() {
        resourceBase = WebAppConstants.RESOURCE_BASE;
    }

    public void onRequest(RequestContext ctx) {
        char[] uri = ctx.getRequestUri()
                        .toCharArray();
        for (int i = 0; i < uri.length; i++) {
            if (i == '/') {
                uri[i] = File.separatorChar;
            }
        }
        File           file = new File(resourceBase + new String(uri));
        BufferedReader in   = null;
        try {
            in = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String        str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
            cache.put(ctx.getRequestUri(), sb.toString());
            ctx.writeString(cache.get(ctx.getRequestUri()));
        } catch (FileNotFoundException e) {
            ctx.error(404, ctx.getRequestUri() + " not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
    }

    public void destroy() {

    }
}
