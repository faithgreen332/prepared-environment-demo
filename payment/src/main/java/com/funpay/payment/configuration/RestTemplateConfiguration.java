package com.funpay.payment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import javax.xml.transform.Source;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Leeko
 * @date 2022/2/14
 **/
@Configuration
public class RestTemplateConfiguration {

    public static final String HTTPS_TEMPLATE = "httpsRestTemplate";
    public static final String HTTP_TEMPLATE = "httpRestTemplate";

    @Bean
    public HttpsClientRequestFactory getHttpsClientRequestFactory() {
        return new HttpsClientRequestFactory();
    }

    private final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

    private static final boolean ROME_PRESENT =
            ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", RestTemplateConfiguration.class.getClassLoader());

    private static final boolean JAXB2_PRESENT =
            ClassUtils.isPresent("javax.xml.bind.Binder", RestTemplateConfiguration.class.getClassLoader());

    private static final boolean JACKSON2_PRESENT =
            ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", RestTemplateConfiguration.class.getClassLoader()) &&
                    ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", RestTemplateConfiguration.class.getClassLoader());

    private static final boolean JACKSON2_XML_PRESENT =
            ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", RestTemplateConfiguration.class.getClassLoader());

    private static final boolean GSON_PRESENT =
            ClassUtils.isPresent("com.google.gson.Gson", RestTemplateConfiguration.class.getClassLoader());

    @Bean(HTTPS_TEMPLATE)
    @Primary
    public RestTemplate getHttpsRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(getHttpsClientRequestFactory());
        return addMessageConverters(restTemplate);
    }

    @Bean(HTTP_TEMPLATE)
    public RestTemplate getHttpRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return addMessageConverters(restTemplate);
    }

    private RestTemplate addMessageConverters(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter<Source>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());

        if (ROME_PRESENT) {
            messageConverters.add(new AtomFeedHttpMessageConverter());
            messageConverters.add(new RssChannelHttpMessageConverter());
        }

        if (JACKSON2_XML_PRESENT) {
            messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        } else if (JAXB2_PRESENT) {
            messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        }

        if (JACKSON2_PRESENT) {
            messageConverters.add(new MappingJackson2HttpMessageConverter());
        } else if (GSON_PRESENT) {
            messageConverters.add(new GsonHttpMessageConverter());
        }

        messageConverters.forEach(item -> {
            if (item instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) item;
                jsonConverter.setSupportedMediaTypes(
                        Arrays.asList(new MediaType("text", "ejson", Charset.defaultCharset())
                                , new MediaType("application", "json", Charset.defaultCharset()),
                                new MediaType("text", "javascript", Charset.defaultCharset())));
            }
        });
        return restTemplate;
    }
}

class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {

        try {
            if (!(connection instanceof HttpsURLConnection)) {
                throw new RuntimeException("an instance of HttpsUrlConnection is expected!");
            }
            HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
            TrustManager[] trustAllCerts = {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            SSLContext tls = SSLContext.getInstance("TLS");
            tls.init(null, trustAllCerts, new java.security.SecureRandom());

            httpsConnection.setSSLSocketFactory(new SandboxSSLSocketFactory(tls.getSocketFactory()));
            httpsConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            super.prepareConnection(httpsConnection, httpMethod);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.prepareConnection(connection, httpMethod);
    }

    private class SandboxSSLSocketFactory extends SSLSocketFactory {

        private final SSLSocketFactory delegate;


        public SandboxSSLSocketFactory(SSLSocketFactory socketFactory) {
            this.delegate = socketFactory;
        }

        /**
         * 返回默认启用的密码套件，除非一个列表启用，对 SSL 连接的握手会使用这些密码套件
         * 这些默认的发忘得最低质量要求保密保护和服务器得身份验证
         */
        @Override
        public String[] getDefaultCipherSuites() {
            return delegate.getDefaultCipherSuites();
        }

        /**
         * 返回得密码套件可用于 SSL 连接启用的名字
         *
         * @return
         */
        @Override
        public String[] getSupportedCipherSuites() {
            return delegate.getSupportedCipherSuites();
        }

        @Override
        public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(s, host, port, autoClose);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
            final Socket underlyingSocket = delegate.createSocket(host, port);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
            final Socket underlyingSocket = delegate.createSocket(host, port, localHost, localPort);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(InetAddress host, int port) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(address, port, localAddress, localPort);
            return overrideProtocol(underlyingSocket);
        }

        private Socket overrideProtocol(final Socket socket) {
            if (!(socket instanceof SSLSocket)) {
                throw new RuntimeException("An instance of SSLSocket is expected");
            }
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1", "TLSv1.2", "TLSv1.1", "SSLv3"});
            return socket;
        }
    }
}