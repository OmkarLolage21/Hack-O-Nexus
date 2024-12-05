import React, { useState } from 'react';
import apiService from './apiService';
import './App.css'; 

const JobList = () => {
  const [jobs, setJobs] = useState([]);

  const fetchJobs = () => {
    console.log('Fetching jobs');
    apiService.getJobs()
      .then(response => {
        console.log('Jobs fetched:', response.data);
        if (Array.isArray(response.data)) {
          setJobs(response.data);
        } else {
          console.error('Unexpected response data format:', response.data);
        }
      })
      .catch(error => {
        console.error('Error fetching jobs:', error);
      });
  };

  return (
    <div className="container">
      <h1>Hackathon List</h1>
      <button onClick={fetchJobs}>Get All Hackathons</button>
      <ul className="job-list">
        {jobs.length > 0 ? (
          jobs.map(job => (
            <li key={job.id} className="job-card">
              <h2 className="job-title">{job.title}</h2>
              <p className="job-description"><strong>Description:</strong> {job.description}</p>
              <p className="min-salary"><strong>Min Salary:</strong> {job.minSalary}</p>
              <p className="max-salary"><strong>Max Salary:</strong> {job.maxSalary}</p>
              <p className="location"><strong>Location:</strong> {job.location}</p>
              <div className="company-info">
                <h3>Company</h3>
                <p><strong>Name:</strong> {job.company.name}</p>
                <p className="company-description"><strong>Description:</strong> {job.company.description}</p>
              </div>
              <div className="review-info">
                <h3>Reviews</h3>
                <ul>
                  {job.review.map(r => (
                    <li key={r.id}>
                      <p><strong>Title:</strong> {r.title}</p>
                      <p className="review-description"><strong>Description:</strong> {r.description}</p>
                      <p><strong>Rating:</strong> {r.rating}</p>
                    </li>
                  ))}
                </ul>
              </div>
            </li>
          ))
        ) : (
          <li>No hackathons available</li>
        )}
      </ul>
    </div>
  );
};

export default JobList;
