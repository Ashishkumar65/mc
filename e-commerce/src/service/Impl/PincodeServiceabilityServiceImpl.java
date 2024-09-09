package service.Impl;

import exception.EcommerceException;
import model.PaymentMode;
import model.PinCodeSeviceability ;
import repository.PincodeServiceabilityRepository;
import service.PincodeServiceabilityService;

import java.util.HashMap;


public class PincodeServiceabilityServiceImpl  implements PincodeServiceabilityService {

    PincodeServiceabilityRepository pincodeServiceabilityRepository;

    public PincodeServiceabilityServiceImpl(PincodeServiceabilityRepository pincodeServiceabilityRepository){
        this.pincodeServiceabilityRepository = pincodeServiceabilityRepository;
    }


    @Override
    public Boolean createPinCodeServiceability(String sourcePinCode, String destinationPinCode, PaymentMode paymentMode) throws EcommerceException {
        PinCodeSeviceability  pinCodeServiceability = new PinCodeSeviceability (destinationPinCode,paymentMode);
        return pincodeServiceabilityRepository.createPincodeServiceability(sourcePinCode,pinCodeServiceability);

    }

    @Override
    public Boolean checkIsSourceAndDestPinCodeMatchesForPaymentType(String sourcePinCode, String destinationPinCode, PaymentMode paymentMode) throws EcommerceException {
        HashMap<String, PaymentMode> allDestinationPincodes = pincodeServiceabilityRepository.getAllDestinationPincode(sourcePinCode);
        return allDestinationPincodes.containsKey(destinationPinCode) &&
                paymentMode.equals(allDestinationPincodes.get(destinationPinCode));
    }
}