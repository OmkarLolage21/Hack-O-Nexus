package com.practice.hackathonms.Hackathon.mapper;

import com.practice.hackathonms.Hackathon.Hackathon;
import com.practice.hackathonms.Hackathon.dto.HackathonDTO;
import com.practice.hackathonms.Hackathon.external.Company;
import com.practice.hackathonms.Hackathon.external.Review;

import java.util.List;

public class HackathonMapper {
    public static HackathonDTO mapToHackathonWithCompanyDto(Hackathon hackathon, Company company, List<Review> reviews){
        HackathonDTO hackathonDTO = new HackathonDTO();
        hackathonDTO.setId(hackathon.getId());
        hackathonDTO.setTitle(hackathon.getTitle());
        hackathonDTO.setDescription(hackathon.getDescription());
        hackathonDTO.setMinSalary(hackathon.getMinSalary());
        hackathonDTO.setMaxSalary(hackathon.getMaxSalary());
        hackathonDTO.setLocation(hackathon.getLocation());
        hackathonDTO.setCompany(company);
        hackathonDTO.setReview(reviews);

        return hackathonDTO;
    }
}
