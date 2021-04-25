package password;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static String getPasswordHash(String password) {
        String passwordHash = password;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            passwordHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordHash;
    }

    public static boolean checkPassword(String password, String passwordHash) {
        return passwordHash.equals(getPasswordHash(password));
    }
}
