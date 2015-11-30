package cn.yxffcode.http.manager;

import cn.yxffcode.http.api.ApplicationListener;
import cn.yxffcode.http.api.RequestListener;
import cn.yxffcode.http.common.WebAppConstants;
import cn.yxffcode.http.manager.event.ListenerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by hang.gao on 2015/6/10.
 */
public class ApplicationConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private static final Pattern CONFIG_LINE_SPLITTER = Pattern.compile(":");

    private Map<String, Class<?>> webComponentMappings = new HashMap<String, Class<?>>();

    private List<ListenerWrapper<RequestListener>> requestListeners = new ArrayList<ListenerWrapper<RequestListener>>();

    private List<ListenerWrapper<ApplicationListener>> applicationListener = new ArrayList<ListenerWrapper<ApplicationListener>>();

    private ApplicationConfiguration() {
    }

    public static ApplicationConfiguration getInstance() {
        return Holder.instance;
    }

    private void init() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(WebAppConstants.COMPONENT_MAPPING_CONF));
            String line;
            while ((line = in.readLine()) != null) {
                String[] elems = CONFIG_LINE_SPLITTER.split(line);
                if (elems.length != 2) {
                    logger.error("config error, earch line must be seperated by ':' : {}", line);
                    throw new RuntimeException("config error:" + line);
                }
                if (elems[0].charAt(0) == '/') {
                    webComponentMappings.put(elems[0].trim(), Class.forName(elems[1].trim()));
                } else {
                    Class<?> type = Class.forName(elems[1].trim());
                    if (type.isAssignableFrom(RequestListener.class)) {
                        requestListeners.add(new ListenerWrapper<RequestListener>(
                                (RequestListener) type.newInstance(), Boolean.parseBoolean(elems[0])));
                    } else if (type.isAssignableFrom(ApplicationListener.class)) {
                        applicationListener.add(new ListenerWrapper<ApplicationListener>(
                                (ApplicationListener) type.newInstance(), Boolean.parseBoolean(elems[0])));
                    }
                }
            }
            webComponentMappings = Collections.unmodifiableMap(webComponentMappings);
            requestListeners = Collections.unmodifiableList(requestListeners);
            applicationListener = Collections.unmodifiableList(applicationListener);
        } catch (FileNotFoundException e) {
            logger.error("config file not found:{}", WebAppConstants.COMPONENT_MAPPING_CONF, e);
        } catch (IOException e) {
            logger.error("read config file:{}", WebAppConstants.COMPONENT_MAPPING_CONF, e);
        } catch (ClassNotFoundException e) {
            logger.error("Class not found", e);
        } catch (InstantiationException e) {
            logger.error("cannot create the instance", e);
        } catch (IllegalAccessException e) {
            logger.error("cannot create the instance, access rejected", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.warn("close reader error", e);
                }
            }
        }
    }

    public List<ListenerWrapper<ApplicationListener>> getApplicationListener() {
        return applicationListener;
    }

    public List<ListenerWrapper<RequestListener>> getRequestListeners() {
        return requestListeners;
    }

    public Map<String, Class<?>> getWebComponentMappings() {
        return webComponentMappings;
    }

    private static final class Holder {
        private static final ApplicationConfiguration instance;

        static {
            ApplicationConfiguration cfg = new ApplicationConfiguration();
            cfg.init();
            instance = cfg;
        }
    }

}
