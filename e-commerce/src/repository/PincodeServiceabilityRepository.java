package repository;

import model.PaymentMode;
import model.PinCodeSeviceability;

import java.util.HashMap;

public class PincodeServiceabilityRepository {
    HashMap<String, HashMap<String, PaymentMode>> pincodes;

    public PincodeServiceabilityRepository(){
        pincodes = new HashMap<>();
    }

    public boolean createPincodeServiceability(String sourcePincode, PinCodeSeviceability pinCodeServiceability){
        if(pincodes.get(sourcePincode)== null){
            HashMap<String, PaymentMode> destinationCodes = new HashMap<>();
            destinationCodes.put(pinCodeServiceability.getDestinationPincode(), pinCodeServiceability.getPaymentMode());
            pincodes.put(sourcePincode, destinationCodes);
        }
        pincodes.get(sourcePincode).put(pinCodeServiceability.getDestinationPincode(), pinCodeServiceability.getPaymentMode());
        return true;
    }
    public HashMap<String,PaymentMode> getAllDestinationPincode(String sourcePincode){
        return pincodes.get(sourcePincode);
    }
}
