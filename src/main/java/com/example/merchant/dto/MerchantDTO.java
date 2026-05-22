package com.example.merchant.dto;

import com.example.merchant.model.MerchantType;
import lombok.*;

@Data
public class MerchantDTO {
    private String id;
    private String name;
    private String address;
    private MerchantType merchantType;
    private String clientId;
}
