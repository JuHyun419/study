package com.example.springthreadtransactionexample.service;

import com.example.springthreadtransactionexample.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@RequiredArgsConstructor
public class OuterService {

    private final InnerService innerService;
    private final ItemRepository itemRepository;

    @Transactional
    public void outerMethod() {
        System.out.println("=========================================");
        System.out.println(Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
        System.out.println("=========================================");

        itemRepository.addCount("item", 0);

        innerService.innerMethod();

        itemRepository.print();
    }

}
