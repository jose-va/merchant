package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateMerchant(@RequestBody MerchantDTO dto){
        merchantService.updateMerchant(dto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMerchant(@RequestParam String id, @RequestParam String address){
        merchantService.deleteMerchant(id, address);
    }

    @GetMapping("/findAll")
    public List<MerchantDTO> findAll(){
        return merchantService.findAll();
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public MerchantDTO findById(@RequestParam String id, @RequestParam String address) {
        return merchantService.findById(id, address);
    }

    @GetMapping("/findByName/{name}")
    public List<MerchantDTO> findByName(@PathVariable String name){
        return merchantService.findByName(name);
    }
}
