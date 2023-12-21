// src/comps/trains/TrainsInfoHome.js
import React from 'react';
import './../../App.css';
import { useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const TrainsInfoHome = () => {
  const navigate = useNavigate();

  const handleButtonClick = (route) => {
    // Use navigate to go to the specified route
    navigate(`/manage-trains/${route}`);
  };

  return (
    <div>
      <h2>Trains Info Home</h2>
      <button onClick={() => handleButtonClick('check-all')}>Check All Trains</button>
      <button onClick={() => handleButtonClick('add')}>Add Train</button>
      <button onClick={() => handleButtonClick('delete')}>Delete Train</button>
      <button onClick={() => handleButtonClick('update')}>Update Train</button>
      <NavigatingBack/>
    </div>
  );
};

export default TrainsInfoHome;
