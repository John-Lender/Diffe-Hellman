import java.math.BigInteger;

public class DH {
    public BigInteger pb_key_1;
    public BigInteger pb_key_2;
    public BigInteger pv_key;
    public BigInteger full_key;
    public DH(BigInteger pb_key_1, BigInteger pb_key_2, BigInteger pv_key){
        this.pb_key_1 = pb_key_1;
        this.pb_key_2 = pb_key_2;
        this.pv_key = pv_key;
        full_key = new BigInteger("0");
    }
    public BigInteger generate_partial_key(){
        BigInteger bi = new BigInteger(String.valueOf(pb_key_1));
        return bi.modPow(new BigInteger(String.valueOf(pv_key)) , new BigInteger(String.valueOf(pb_key_2)));
    }
    public BigInteger generate_full_key(BigInteger partial_key_r){
        return partial_key_r.modPow(new BigInteger(String.valueOf(pv_key)) , new BigInteger(String.valueOf(pb_key_2)));
    }
    public String encrypt_message(String msg, BigInteger partial_key){
        String encry_msg = "";
        full_key = generate_full_key(partial_key);
        for (char c: msg.toCharArray()) {
            encry_msg += (char)((int)c + full_key.intValue());
        }
        return encry_msg;
    }
    public String decrypt_message(String encrypt_message, BigInteger partial_key){
        String decrypt_message = "";
        full_key = generate_full_key(partial_key);
        for (char c: encrypt_message.toCharArray()) {
            decrypt_message += (char)((int)c - full_key.intValue());
        }
        return decrypt_message;
    }

    @Override
    public String toString() {
        return   "public key 1: " + pb_key_1 + "\n" +
                "public key 2: " + pb_key_2 + "\n" +
                 "private key: " + pv_key + "\n" +
                 "partial key: " + generate_partial_key() + "\n";
    }

}
