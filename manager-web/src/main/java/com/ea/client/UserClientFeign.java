package com.ea.client;

import com.ea.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "manager")
public interface UserClientFeign {

    @GetMapping("/user/all")
    List<UserVO> getAll();
}
