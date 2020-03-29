package com.apple.assignment.flagpicker.controller;

import com.apple.assignment.flagpicker.GuavaConfig;
import com.apple.assignment.flagpicker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminSvc;

    @RequestMapping(value = "/load", method = GET)
    public String load(){
        return adminSvc.loadData();
    }

    @CacheEvict(value = GuavaConfig.COUNTRY_CACHE, allEntries = true)
    @RequestMapping(value = "/clear-cache", method = GET)
    public String clearCache() {
        return "Cache Cleared";
    }


}
