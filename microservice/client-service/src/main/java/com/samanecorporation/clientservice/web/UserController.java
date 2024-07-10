package com.samanecorporation.clientservice.web;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/clients")
@RefreshScope
public class UserController {

    private String dbname;
   private RestTemplate template;

    public UserController(@Value("${dbname}") String dbname, RestTemplate template) {
        this.dbname = dbname;
        this.template = template;
    }

    // via lapi gateway http://localhost:8889/CLIENT-SERVICE/clients/users
    @GetMapping("/users")
    public String hello() {
        String url = "http://SECURITY-SERVICE/users";

        return template.getForObject(url, String.class);
    }

    /**
     * Ce endpoint recupere la config du microservice via le micro service de confog
     * @return : la valeur de la propriete dbname qui est definie dans la config via lapi gateway http://localhost:8889/CLIENT-SERVICE/clients/config
     */
    @GetMapping("/config")
    public ResponseEntity<Map<String, Object>> getConfig() {

        Map<String, Object> params = new HashMap<>();
        params.put("dbanameParam", dbname);
        // Un thread est sous process du process parent : en ajoutant @RefreshScope
        params.put("threadName", Thread.currentThread().getName());
        return new ResponseEntity<>(params, HttpStatus.OK);
    }
}
