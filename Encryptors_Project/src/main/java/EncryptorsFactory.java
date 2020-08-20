import java.util.Map;

public class EncryptorsFactory {
    private Map<EncryptMethod, Encrypt> encryptMap;

    private Encrypt getEncrypt(EncryptMethod parsConst) {
        if (encryptMap.get(parsConst) != null) {
            return encryptMap.get(parsConst);
        }
        throw new IllegalArgumentException("Unknown Parser");
    }

    public Encrypt getEncrypt(String encryptMethodType) throws Exception {
        for(EncryptMethod encryptMethod : EncryptMethod.values()) {
            if(encryptMethod.getEncryptName().equals(encryptMethodType)) {
                return getEncrypt(encryptMethod);
            }
        }

        throw new Exception("Encrypt method type is not supported\n");
    }

    public void setEncryptsMap(Map<EncryptMethod, Encrypt> parserMap) {
        this.encryptMap = parserMap;
    }
}
