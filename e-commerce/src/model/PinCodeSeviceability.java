package model;

public class PinCodeSeviceability {
    private final String destinationPincode;
    private final PaymentMode paymentMode;
    public PinCodeSeviceability(String destinationPincode, PaymentMode paymentMode) {
        this.destinationPincode = destinationPincode;
        this.paymentMode = paymentMode;
    }

    public String getDestinationPincode() {
        return destinationPincode;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }
}
