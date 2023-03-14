package com.mh.controller;

import com.mh.data.reponse.VoteResponse;
import com.mh.data.request.VoteRequest;
import com.mh.service.VoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "/vote/{id}")
    public VoteResponse insert(@RequestBody VoteRequest voteRequest,@PathVariable(name = "id") int id){
        return voteService.insert(voteRequest,id);
    }
}
