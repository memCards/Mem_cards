package password;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    private Password() {
    }

    public static String getPasswordHash(String password) {
        String passwordHash = password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
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
