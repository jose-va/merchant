package com.example.merchant.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.merchant.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MerchantRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public void save(Merchant merchant) {
        dynamoDBMapper.save(merchant);
    }

    public Merchant findById(String pk, String sk) {
        return dynamoDBMapper.load(Merchant.class, pk, sk);
    }

}
