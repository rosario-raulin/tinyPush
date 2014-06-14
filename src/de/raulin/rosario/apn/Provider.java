package de.raulin.rosario.apn;

import de.raulin.rosario.apn.frameitems.Priority;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.List;

/**
 * Created by Rosario on 13/06/14.
 */
public class Provider {

    //public final static String PUSH_SERVER_URL = "gateway.push.apple.com";
    public final static String PUSH_TEST_SERVER_URL = "gateway.sandbox.push.apple.com";
    public final static String PUSH_SERVER_URL = "gateway.sandbox.push.apple.com";

    public final static String FEEDBACK_SERVER_URL = "feedback.sandbox.push.apple.com";
    public final static int FEEDBACK_SERVER_PORT = 2196;

    public final static int PUSH_SERVER_PORT = 2195;

    private final String pushServerUrl;
    private final int pushServerPort;
    private final String clientCertPath;
    private final String password;
    private final List<Notification> notifications;

    Provider(String pushServerUrl, int pushServerPort, String clientCertPath, String password,
             List<Notification> notifications) {
        this.pushServerUrl = pushServerUrl;
        this.pushServerPort = pushServerPort;
        this.clientCertPath = clientCertPath;
        this.password = password;
        this.notifications = notifications;
    }

    public void sendNotifications() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        SSLSocket socket = (SSLSocket) getFactory().createSocket(pushServerUrl, pushServerPort);

        try {
            OutputStream outStream = socket.getOutputStream();

            for (Notification notification : notifications) {
                outStream.write(notification.getBytes());
            }

            outStream.flush();

            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[6];
            while (inputStream.available() > 0) {
                inputStream.read(buffer);
                System.out.println("return code: " + buffer[1]);
            }
        } finally {
            socket.close();
        }
    }

    private SSLSocketFactory getFactory() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SUNX509");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        InputStream keyInput = new FileInputStream(new File(clientCertPath));
        try {
            keyStore.load(keyInput, password.toCharArray());
        } finally {
            keyInput.close();
        }
        keyManagerFactory.init(keyStore, password.toCharArray());

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

        return context.getSocketFactory();
    }
}
