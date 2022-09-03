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
public class InnerService {

    private final ItemRepository itemRepository;

//    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerMethod() {
        System.out.println("=========================================");
        System.out.println(Thread.currentThread().getId() + ", " + Thread.currentThread().getName());

        itemRepository.addCount("item", 5);

        throw new RuntimeException();
    }

}
