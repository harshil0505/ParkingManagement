package com.Online.ParkigManagement.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.Online.ParkigManagement.model.VehicalType;





@Component
public class feeConfig {

    private static final Map<VehicalType, Double> fees = new HashMap<>();

    static {
        fees.put(VehicalType.TWO_WHEELER, 20.0);
        fees.put(VehicalType.THREE_WHEELER, 30.0);
        fees.put(VehicalType.FOUR_WHEELER, 40.0);
        fees.put(VehicalType.TRUCK, 50.0);
    }

    public double getFee(VehicalType vehicalType) {
        return fees.getOrDefault(vehicalType, 0.0);
    }
}
