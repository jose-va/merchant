package com.example.merchant.service;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.mappers.MerchantMapper;
import com.example.merchant.model.Merchant;
import com.example.merchant.repository.MerchantRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    public void saveMerchant(MerchantDTO dto) {
        Merchant merchant = merchantMapper.toEntity(dto);

        merchant.setId(UUID.randomUUID().toString());
        merchant.setPk("MERCHANT#" + merchant.getId());
        merchant.setSk("ADDRESS#" + merchant.getAddress());
        merchant.setGIndex2Pk("TYPE#" + merchant.getMerchantType());

        merchantRepository.save(merchant);
    }

    public void updateMerchant(MerchantDTO dto, String id) {

        Merchant updatedMerchant = merchantMapper.toEntity(dto);

        updatedMerchant.setId(id);
        updatedMerchant.setPk("MERCHANT#" + id);
        updatedMerchant.setSk("ADDRESS#" + updatedMerchant.getAddress());
        updatedMerchant.setGIndex2Pk("TYPE#" + updatedMerchant.getMerchantType());

        merchantRepository.save(updatedMerchant);
    }

    public MerchantDTO findById(String id, String address) {

        String pk = "MERCHANT#" + id;
        String sk = "ADDRESS#" + address;

        Merchant merchant = merchantRepository.findById(pk, sk);
        if (merchant == null) throw new RuntimeException("Comerciante no encontrado");

        return merchantMapper.toDto(merchant);
    }
}
