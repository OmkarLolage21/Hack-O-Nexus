// src/components/JobForm.js
import React, { useState } from 'react';
import apiService from './apiService';
import './App.css'; // Import the CSS file

const JobForm = () => {
  const [jobData, setJobData] = useState({
    title: '',
    description: '',
    minSalary: '',
    maxSalary: '',
    location: '',
    companyId: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setJobData({
      ...jobData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Submitting job:', jobData);
    apiService.createJob(jobData)
      .then(response => {
        console.log('Job created:', response.data);
        // Reset form after successful submission
        setJobData({
          title: '',
          description: '',
          minSalary: '',
          maxSalary: '',
          location: '',
          companyId: '',
        });
      })
      .catch(error => {
        console.error('Error creating job:', error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add a new Hackathon</h2>
      <input
        type="text"
        name="title"
        placeholder="Job Title"
        value={jobData.title}
        onChange={handleChange}
      />
      <textarea
        name="description"
        placeholder="Job Description"
        value={jobData.description}
        onChange={handleChange}
      />
      <input
        type="text"
        name="minSalary"
        placeholder="Min Salary"
        value={jobData.minSalary}
        onChange={handleChange}
      />
      <input
        type="text"
        name="maxSalary"
        placeholder="Max Salary"
        value={jobData.maxSalary}
        onChange={handleChange}
      />
      <input
        type="text"
        name="location"
        placeholder="Location"
        value={jobData.location}
        onChange={handleChange}
      />
      <input
        type="text"
        name="companyId"
        placeholder="Company ID"
        value={jobData.companyId}
        onChange={handleChange}
      />
      <button type="submit">Add hackathon</button>
    </form>
  );
};

export default JobForm;
