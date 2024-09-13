package com.practice.hackathonms.Hackathon.impl;


import com.practice.hackathonms.Hackathon.Hackathon;
import com.practice.hackathonms.Hackathon.HackathonRepository;
import com.practice.hackathonms.Hackathon.HackathonService;
import com.practice.hackathonms.Hackathon.clients.CompanyClient;
import com.practice.hackathonms.Hackathon.clients.ReviewClient;
import com.practice.hackathonms.Hackathon.dto.HackathonDTO;
import com.practice.hackathonms.Hackathon.external.Company;
import com.practice.hackathonms.Hackathon.external.Review;
import com.practice.hackathonms.Hackathon.mapper.HackathonMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HackathonServiceImpl implements HackathonService {

    HackathonRepository hackathonRepository;

    @Autowired
    RestTemplate restTemplate;

    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    int attempts = 0;

    public HackathonServiceImpl(HackathonRepository hackathonRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.hackathonRepository = hackathonRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<HackathonDTO> findAll() {
//        System.out.println("Attempt: " + ++attempts);
        List<Hackathon> hackathons = hackathonRepository.findAll();
        List<HackathonDTO> hackathonDTOS = new ArrayList<>();
        return hackathons.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("You have reached fallback");
        return list;
    }
    private HackathonDTO convertToDto(Hackathon hackathon){
            Company company = companyClient.getCompany(hackathon.getCompanyId());
//            ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("https://REVIEWMS:8083/reviews?companyId=" + hackathon.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
//            });
            List<Review> reviews = reviewClient.getReviews(hackathon.getCompanyId());
            HackathonDTO hackathonDTO = HackathonMapper.mapToHackathonWithCompanyDto(hackathon,company, reviews);
            return hackathonDTO;
    }
    @Override
    public void createJob(Hackathon hackathon) {
        hackathonRepository.save(hackathon);
    }

    @Override
    public HackathonDTO getJobById(Long id) {
        Hackathon hackathon = hackathonRepository.findById(id).orElse(null);
        return convertToDto(hackathon);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            hackathonRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Hackathon updatedHackathon) {
        Optional<Hackathon> jobOptional = hackathonRepository.findById(id);
        if(jobOptional.isPresent()){
            Hackathon hackathon = jobOptional.get();
            hackathon.setTitle(updatedHackathon.getTitle());
            hackathon.setDescription(updatedHackathon.getDescription());
            hackathon.setMinSalary(updatedHackathon.getMinSalary());
            hackathon.setMaxSalary(updatedHackathon.getMaxSalary());
            hackathon.setLocation(updatedHackathon.getLocation());
            hackathonRepository.save(hackathon);
            return true;
        }
        return false;
    }
}
