import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const AddTrain = () => {
  const navigate = useNavigate();

  const [newTrainData, setNewTrainData] = useState({
    trainNumber: '',
    trainName: '',
    totalDistance: 0,
    numberOfACCoaches: 0,
    acCoachTotalSeats: 0,
    numberOfSleeperCoaches: 0,
    sleeperCoachTotalSeats: 0,
    numberOfGeneralCoaches: 0,
    generalCoachTotalSeats: 0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewTrainData((prevData) => ({ ...prevData, [name]: value }));
  };




  const handleAddTrain = () => {
    const newTrainDataJson = JSON.stringify(newTrainData);
  
    fetch('http://localhost:8085/trains', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: newTrainDataJson,
    })
      .then((response) => {
        if (!response.ok) {
          // Check if the response has a JSON content type
          const contentType = response.headers.get('content-type');
          if (contentType && contentType.includes('application/json')) {
            return response.json().then((errorData) => {
              console.error('Validation errors:', errorData);
              // Do something with the validation errors, such as displaying them in the UI
            });
          } else {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
        }
        return response.json();
      })
      .then((addedTrain) => {
        console.log('Train added successfully:', addedTrain);
        alert(`Train ${addedTrain.trainNumber} added successfully`);
  
        // Navigate back to the Train Home
        navigate('/manage-trains');
      })
      .catch((error) => {
        alert('Error adding train:', error);
        console.error('Error adding train:', error);
      });
  };
  return (
    <div>
      <h2>Add New Train</h2>
      <form>
      <label>
          Train Number:
          <input
            type="text"
            name="trainNumber"
            value={newTrainData.trainNumber}
            onChange={handleChange}
          />
        </label>
        <label>
          Train Name:
          <input
            type="text"
            name="trainName"
            value={newTrainData.trainName}
            onChange={handleChange}
          />
        </label>
        <label>
          Total Distance Traveled:
          <input
            type="number"
            name="totalDistance"
            value={newTrainData.totalDistance}
            onChange={handleChange}
          />
        </label>
        <label>
          AC Coaches:
          <input
            type="number"
            name="numberOfACCoaches"
            value={newTrainData.numberOfACCoaches}
            onChange={handleChange}
          />
        </label>
        <label>
          AC Coach Total Seats:
          <input
            type="number"
            name="acCoachTotalSeats"
            value={newTrainData.acCoachTotalSeats}
            onChange={handleChange}
          />
        </label>
        <label>
          Sleeper Coaches:
          <input
            type="number"
            name="numberOfSleeperCoaches"
            value={newTrainData.numberOfSleeperCoaches}
            onChange={handleChange}
          />
        </label>
        <label>
          Sleeper Coach Total Seats:
          <input
            type="number"
            name="sleeperCoachTotalSeats"
            value={newTrainData.sleeperCoachTotalSeats}
            onChange={handleChange}
          />
        </label>
        <label>
          General Coaches:
          <input
            type="number"
            name="numberOfGeneralCoaches"
            value={newTrainData.numberOfGeneralCoaches}
            onChange={handleChange}
          />
        </label>
        <label>
          General Coach Total Seats:
          <input
            type="number"
            name="generalCoachTotalSeats"
            value={newTrainData.generalCoachTotalSeats}
            onChange={handleChange}
          />
        </label>
        <button type="button" onClick={handleAddTrain}>
          Add Train
        </button>
      </form>
      <NavigatingBack/>
    </div>
  );
};

export default AddTrain;
