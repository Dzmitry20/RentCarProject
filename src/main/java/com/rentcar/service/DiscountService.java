package com.rentcar.service;


import com.rentcar.controller.requests.DiscountRequest;
import com.rentcar.controller.requests.mappers.DiscountMapper;
import com.rentcar.domain.Discount;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    public final DiscountRepository discountRepository;

    public final DiscountMapper discountMapper;


    public DiscountRequest updateDiscount( Long id, DiscountRequest discountRequest) {

        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Discount not found by id "));

        discountMapper.updateDiscountFromDiscountRequest(discountRequest,discount);
        discountRepository.save(discount);

        DiscountRequest request = new DiscountRequest();
        discountMapper.updateDiscountRequestFromDiscount(discount, request);

        return request;
    }


    public List<DiscountRequest> findAll(){

        List<DiscountRequest> list= new ArrayList<>();
        List<Discount> discounts = discountRepository.findAll();
        DiscountRequest discountRequest = new DiscountRequest();

        for (Discount discount :  discounts){
            discountMapper.updateDiscountRequestFromDiscount(discount, discountRequest);
            list.add(discountRequest);
        }
        return list;
    }


    public void deleteDiscount(Long id){

       Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Discount not found by id "));

        discountRepository.delete(discount);
    }


    public DiscountRequest createDiscount(DiscountRequest discountRequest){

        Discount discount  = new Discount();

        discountMapper.updateDiscountFromDiscountRequest(discountRequest,discount);
        discountRepository.save(discount);

        DiscountRequest request = new DiscountRequest();

        discountMapper.updateDiscountRequestFromDiscount(discount, request);
         return request ;
    }
}
