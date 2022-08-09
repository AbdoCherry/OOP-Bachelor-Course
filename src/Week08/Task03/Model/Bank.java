package Week08.Task03.Model;

public class Bank {

    private String iban, bankName;

    public Bank() {

    }

    public Bank(String iban, String bankName) {
        this.iban = iban;
        this.bankName = bankName;
    }

    /**
     * @return String
     */
    public String getIban() {
        return iban;
    }

    /**
     * @param iban
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * @return String
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
