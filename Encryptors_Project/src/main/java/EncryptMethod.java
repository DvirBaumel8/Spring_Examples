public enum EncryptMethod {
    REVERSE("reverse_skip_first"),
    REVERSE_SKIP_FIRST("reverse"),
    SWITCH_FIRST_LAST("switch_first_last");

    private String encryptName;

    EncryptMethod(String encryptName) {
        this.encryptName = encryptName;
    }

    public String getEncryptName() {
        return encryptName;
    }
}
