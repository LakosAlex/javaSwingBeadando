import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashConverter {

    public static String convertToHash(char[] password) {

        String pswString = String.valueOf(password);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        md.reset();
        byte[] digest = md.digest(pswString.getBytes());
        BigInteger bigInteger = new BigInteger(1, digest);
        return bigInteger.toString(16);
    }
}
