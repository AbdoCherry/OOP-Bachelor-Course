package Week08.Task03.Model;

public class Bank {

    private String iban, bankName;

    public Bank() {

    }

    public Bank(String iban, String bankName) {
        this.iban = iban;
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public String getBankName() {
        return bankName;
    }

}
