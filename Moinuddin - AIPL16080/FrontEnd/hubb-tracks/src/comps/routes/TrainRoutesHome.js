// src/comps/routes/TrainRoutesHome.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const TrainRoutesHome = () => {
  const navigate = useNavigate();

  const handleButtonClick = (route) => {
    // Use navigate to go to the specified route
    navigate(`/manage-routes/${route}`);
  };

  return (
    <div>
      <h2>Train Routes Home</h2>
      <button onClick={() => handleButtonClick('check-all')}>Check All Routes</button>
      <button onClick={() => handleButtonClick('add')}>Add Route</button>
      <button onClick={() => handleButtonClick('delete')}>Delete Route</button>
      <button onClick={() => handleButtonClick('update')}>Update Route</button>
      <NavigatingBack/>
    </div>
  );
};

export default TrainRoutesHome;
