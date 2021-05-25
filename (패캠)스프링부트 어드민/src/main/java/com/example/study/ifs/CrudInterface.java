package com.example.study.ifs;

import com.example.study.model.network.Header;

// 제네릭 -> 추후 User, Order 등 컨트롤러가 증가해도 인터페이스를 구현함으로써 유연하게 Header에 제네릭을 사용할 수 있음
public interface CrudInterface<Req, Res> {

    // TODO request object 추가
    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

}
