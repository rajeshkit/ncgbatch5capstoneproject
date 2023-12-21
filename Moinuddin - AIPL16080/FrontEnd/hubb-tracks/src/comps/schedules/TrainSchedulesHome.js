// src/comps/schedules/TrainSchedulesHome.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const TrainSchedulesHome = () => {
  const navigate = useNavigate();

  const handleButtonClick = (route) => {
    // Use navigate to go to the specified route
    navigate(`/manage-schedules/${route}`);
  };

  return (
    <div>
      <h2>Train Schedules Home</h2>
      <button onClick={() => handleButtonClick('check-all')}>Check All Schedules</button>
      <button onClick={() => handleButtonClick('add')}>Add Schedule</button>
      <button onClick={() => handleButtonClick('delete')}>Delete Schedule</button>
      <button onClick={() => handleButtonClick('update')}>Update Schedule</button>
      <NavigatingBack/>
    </div>
  );
};

export default TrainSchedulesHome;
