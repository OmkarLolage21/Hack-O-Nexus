import axios from 'axios';

const API_BASE_URL = 'http://localhost:8084'; // Your Spring Boot backend URL

const apiService = axios.create({
  baseURL: API_BASE_URL,
});

export const getJobs = () => {
  console.log('Calling getJobs API');
  return apiService.get('/hackathons');
};

export const createJob = (jobData) => {
  console.log('Calling createJob API with data:', jobData);
  return apiService.post('/hackathons', jobData);
};

export default {
  getJobs,
  createJob,
};
