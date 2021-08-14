package com.ea.client;

import com.ea.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "MANAGER")
public interface UserClientFeign {

    @GetMapping("/all")
    List<User> getAll();
}
