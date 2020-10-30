package Server;

import java.security.*;

public class GenerateKeyPair {
    private PublicKey public_key;
    private PrivateKey private_key;
    public PublicKey getPublic_key() {
        return public_key;
    }

    private void setPublic_key(PublicKey public_key) {
        this.public_key = public_key;
    }

    public PrivateKey getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(PrivateKey private_key) {
        this.private_key = private_key;
    }

    public GenerateKeyPair() {
        SecureRandom sr = new SecureRandom();//Initial value
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024,sr);//Do dai cua khoa
            KeyPair kp = kpg.genKeyPair();//Tao ra 1 cap khoa
            this.setPublic_key(kp.getPublic());
            this.setPrivate_key(kp.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Co loi trong qua trinh khoi tao thuat toan");
        }
    }

}
