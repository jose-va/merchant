package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RequestMapping("/api/merchant")
@RestController
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMerchant(@RequestBody MerchantDTO dto) {
        merchantService.saveMerchant(dto);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateMerchant(@RequestBody MerchantDTO dto) {
        merchantService.updateMerchant(dto);
    }

    @GetMapping("/all")
    public List<MerchantDTO> findAll() {
        return merchantService.findAll();
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MerchantDTO> findById(@PathVariable String id) {
        return merchantService.findById(id);
    }

    @GetMapping("/search/name")
    public List<MerchantDTO> findByName(@RequestParam String name) {
        return merchantService.findByName(name);
    }

    @GetMapping("/search/client")
    @ResponseStatus(HttpStatus.OK)
    public List<MerchantDTO> findByClientId(@RequestParam String clientId) {
        return merchantService.findByClientId(clientId);
    }

    @DeleteMapping("/delete/{id}/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMerchant(@PathVariable String id, @PathVariable String clientId) {
        merchantService.deleteMerchant(id, clientId);
    }
}