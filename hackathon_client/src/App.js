// src/App.js
import React from 'react';
import JobList from './JobList';
import JobForm from './JobForm';
import './App.css';

const App = () => {
  return (
    <div className="container">
      <h1>Job Application</h1>
      <JobForm />
      <JobList />
    </div>
  );
};

export default App;
