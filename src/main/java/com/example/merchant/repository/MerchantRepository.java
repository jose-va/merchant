package com.example.merchant.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.example.merchant.model.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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

    public void delete(Merchant merchant) {
        dynamoDBMapper.delete(merchant);
    }

    public List<Merchant> findAll() {
        Merchant filter = new Merchant();
        filter.setGIndex1Pk("STATUS#OPERATIVE");

        DynamoDBQueryExpression<Merchant> queryExpression = new DynamoDBQueryExpression<Merchant>()
                .withHashKeyValues(filter)
                .withIndexName("GI1_PK")
                .withConsistentRead(false);

        return dynamoDBMapper.query(Merchant.class, queryExpression);
    }
}
