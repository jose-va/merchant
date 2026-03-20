package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping ("/api/merchant")
@RestController
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createMerchant(@RequestBody MerchantDTO dto) {
        merchantService.saveMerchant(dto);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MerchantDTO findById(@PathVariable String id, @RequestParam String address) {
        return merchantService.findById(id, address);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMerchant(@RequestBody MerchantDTO dto, @PathVariable String id){
        merchantService.updateMerchant(dto, id);
    }
}
