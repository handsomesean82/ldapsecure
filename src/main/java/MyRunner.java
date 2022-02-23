import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.util.ssl.SSLUtil;
import com.unboundid.util.ssl.TrustAllTrustManager;
import com.unboundid.util.ssl.TrustStoreTrustManager;

public class MyRunner {
    public static void main(String[] args) throws Exception{
        System.out.println("hello world");
        try {
            System.setProperty("javax.net.debug", "all");
            char[] c = "WebAS".toCharArray();
            //SSLUtil sslUtil = new SSLUtil(new TrustAllTrustManager());
            SSLUtil sslUtil = new SSLUtil(new TrustStoreTrustManager("/opt/IBM/WebSphere/AppServer/profiles/PRMS/config/cells/DTABA1ANode01Cell/nodes/PRMSNode01/trust.p12", c, "PKCS12", true));


            LDAPConnection conn = new LDAPConnection(
                    sslUtil.createSSLSocketFactory(), "127.0.0.1", 636, "cn=admin,dc=lta,dc=gov,dc=sg", "admin");

            conn.connect("127.0.0.1", 636);
            //conn.bind("cn=admin,dc=lta,dc=gov,dc=sg", "admin");


        }catch (Exception e){
            System.out.println("Exception catched");
            e.printStackTrace();
            throw e;
        }
        System.out.println("Connection established");
    }
}
