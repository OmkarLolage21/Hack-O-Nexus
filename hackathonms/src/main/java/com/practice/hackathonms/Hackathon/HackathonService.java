package com.practice.hackathonms.Hackathon;

import com.practice.hackathonms.Hackathon.dto.HackathonDTO;

import java.util.List;

public interface HackathonService {

    List<HackathonDTO> findAll();
    void createJob(Hackathon hackathon);

    HackathonDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Hackathon hackathon);
}
