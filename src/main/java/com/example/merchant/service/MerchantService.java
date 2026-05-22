package com.example.merchant.service;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.mappers.MerchantMapper;
import com.example.merchant.model.Merchant;
import com.example.merchant.repository.MerchantRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    public List<MerchantDTO> findAll() {
        return merchantRepository.findAll()
                .stream()
                .map(merchantMapper::toDto)
                .toList();
    }

    public List<MerchantDTO> findById(String id) {

        return merchantRepository.findAll()
                .stream()
                .filter(m -> m.getId().toLowerCase().contains(id.toLowerCase()))
                .map(merchantMapper::toDto)
                .toList();
    }

    public List<MerchantDTO> findByName(String name) {
        return merchantRepository.findAll()
                .stream()
                .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .map(merchantMapper::toDto)
                .toList();
    }

    public List<MerchantDTO> findByClientId(String clientId) {
        return merchantRepository.findAll()
                .stream()
                .filter(m -> m.getSk().equals("CLIENT#" + clientId))
                .map(merchantMapper::toDto)
                .toList();
    }

    public void saveMerchant(MerchantDTO dto) {
        Merchant merchant = merchantMapper.toEntity(dto);

        merchant.setId(UUID.randomUUID().toString());
        merchant.setPk("MERCHANT#" + merchant.getId());
        merchant.setSk("CLIENT#" + merchant.getClientId());
        merchant.setGIndex1Pk("STATUS#OPERATIVE");

        merchantRepository.save(merchant);
    }

    public void updateMerchant(MerchantDTO dto) {

        Merchant updatedMerchant = merchantMapper.toEntity(dto);

        updatedMerchant.setPk("MERCHANT#" + updatedMerchant.getId());
        updatedMerchant.setSk("CLIENT#" + updatedMerchant.getClientId());
        updatedMerchant.setGIndex1Pk("STATUS#OPERATIVE");

        merchantRepository.save(updatedMerchant);
    }

    public void deleteMerchant(String id, String clientId) {
        String pk = "MERCHANT#" + id;
        String sk = "ADDRESS#" + clientId;

        Merchant merchant = merchantRepository.findById(pk, sk);

        merchantRepository.delete(merchant);
    }
}
