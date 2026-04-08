package com.example.merchant.service;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.mappers.MerchantMapper;
import com.example.merchant.model.Merchant;
import com.example.merchant.repository.MerchantRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public MerchantDTO findById(String id, String address) {

        String pk = "MERCHANT#" + id;
        String sk = "ADDRESS#" + address;

        Merchant merchant = merchantRepository.findById(pk, sk);
        if (merchant == null) throw new RuntimeException("Comerciante no encontrado");

        return merchantMapper.toDto(merchant);
    }

    public List<MerchantDTO> findByName(String name) {
        return merchantRepository.findAll()
                .stream()
                .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .map(merchantMapper::toDto)
                .toList();
    }

    public void saveMerchant(MerchantDTO dto) {
        Merchant merchant = merchantMapper.toEntity(dto);

        merchant.setId(UUID.randomUUID().toString());
        merchant.setPk("MERCHANT#" + merchant.getId());
        merchant.setSk("ADDRESS#" + merchant.getAddress());
        merchant.setGIndex1Pk("STATUS#OPERATIVE");
        merchant.setGIndex2Pk("TYPE#" + merchant.getMerchantType());

        merchantRepository.save(merchant);
    }

    public void updateMerchant(MerchantDTO dto) {

        Merchant updatedMerchant = merchantMapper.toEntity(dto);

        updatedMerchant.setPk("MERCHANT#" + updatedMerchant.getId());
        updatedMerchant.setSk("ADDRESS#" + updatedMerchant.getAddress());
        updatedMerchant.setGIndex1Pk("STATUS#OPERATIVE");
        updatedMerchant.setGIndex2Pk("TYPE#" + updatedMerchant.getMerchantType());

        merchantRepository.save(updatedMerchant);
    }

    public void deleteMerchant(String id, String address) {
        String pk = "MERCHANT#" + id;
        String sk = "ADDRESS#" + address;

        Merchant merchant = merchantRepository.findById(pk, sk);

        merchantRepository.delete(merchant);
    }


}
