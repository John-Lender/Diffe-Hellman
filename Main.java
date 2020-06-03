import java.math.BigInteger;

public class Main {
//Теория
//https://tproger.ru/translations/diffie-hellman-key-exchange-explained/
    private static final int VALUE_BIT = 1024;
    public static void main(String[] args) throws Exception {
        BigInteger s_private =  GeneratorEaseNumber.generateNbitEV(VALUE_BIT);
        BigInteger s_public = GeneratorEaseNumber.generateNlenEV(s_private.toString().length());
        BigInteger m_private =  GeneratorEaseNumber.generateNbitEV(VALUE_BIT);
        BigInteger m_public = GeneratorEaseNumber.generateNlenEV(m_private.toString().length());
        DH Sadat  = new DH(s_public,m_public,s_private);
        DH Michael = new DH(s_public, m_public, m_private);
        String msg =  Sadat.encrypt_message("it's just text", Michael.generate_partial_key());
        System.out.println("Sadat" + "\n" + Sadat.toString());
        System.out.println("Michael" + "\n" + Michael.toString());
        System.out.println("Encrypt msg: " + msg);
        System.out.println("Decrypt msg: " + Michael.decrypt_message(msg, Sadat.generate_partial_key()));

    }
}
