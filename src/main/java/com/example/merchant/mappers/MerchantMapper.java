package com.example.merchant.mappers;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.model.Merchant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantDTO toDto(Merchant merchant);

    Merchant toEntity(MerchantDTO merchantDTO);
}
