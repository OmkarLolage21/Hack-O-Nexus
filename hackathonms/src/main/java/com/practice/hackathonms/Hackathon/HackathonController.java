package com.practice.hackathonms.Hackathon;

import com.practice.hackathonms.Hackathon.dto.HackathonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hackathons")
public class HackathonController {

    private HackathonService hackathonService;
    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }
    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<HackathonDTO>> findAll(){
        return ResponseEntity.ok(hackathonService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Hackathon hackathon){
        hackathonService.createJob(hackathon);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HackathonDTO> getJobById(@PathVariable Long id){
        HackathonDTO hackathonDTO = hackathonService.getJobById(id);
        if(hackathonDTO != null)
            return new ResponseEntity<>(hackathonDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = hackathonService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Hackathon updatedHackathon){
        boolean updated = hackathonService.updateJob(id, updatedHackathon);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
/*

GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST /jobs: Create a new job (request body should contain the job details)
DELETE /jobs/{id}: Delete a specific job by ID
PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job details)

Example API URLs:
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1

*/

