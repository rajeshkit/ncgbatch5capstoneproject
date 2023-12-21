import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const UpdateTrains = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const trainNumber = new URLSearchParams(location.search).get('trainNumber');

  const [updatedData, setUpdatedData] = useState({
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

  
  useEffect(() => {
    // Fetch individual train data based on trainNumber
    fetch(`http://localhost:8085/trains/${trainNumber}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        // console.log(data);
        setUpdatedData({
          trainNumber: data.trainNumber,
          trainName: data.trainName,
          totalDistance: Number(data.totalDistance), 
          numberOfACCoaches: Number(data.numberOfACCoaches), 
          acCoachTotalSeats: Number(data.acCoachTotalSeats), 
          numberOfSleeperCoaches: Number(data.numberOfSleeperCoaches), 
          sleeperCoachTotalSeats: Number(data.sleeperCoachTotalSeats), 
          numberOfGeneralCoaches: Number(data.numberOfGeneralCoaches), 
          generalCoachTotalSeats: Number(data.generalCoachTotalSeats), 
        });
      })
      .catch((error) => console.error('Error fetching individual train data:', error));
  }, [trainNumber]);
  

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleUpdate = () => {
    const updatedDataJson = JSON.stringify(updatedData);
    console.log(updatedDataJson);

    fetch(`http://localhost:8085/trains`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: updatedDataJson,
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((updatedTrain) => {
        console.log('Train updated successfully:', updatedTrain);
        alert(`Train ${updatedTrain.trainNumber} updated successfully`);
        navigate('./../check-all');
      })
      .catch((error) => {
        console.error('Error updating train data:', error);
      });
  };

  return (
    <div>
      <h2>Update Train</h2>
      <form>
      <label>
          Train Number:
          <input
            type="text"
            name="trainNumber"
            value={updatedData.trainNumber}
            onChange={handleChange}
          />
        </label>
        <label>
          Train Name:
          <input
            type="text"
            name="trainName"
            value={updatedData.trainName}
            onChange={handleChange}
          />
        </label>
        <label>
          Total Distance:
          <input
            type="number"
            name="totalDistance"
            value={updatedData.totalDistance}
            onChange={handleChange}
          />
        </label>
        <label>
          AC Coaches:
          <input
            type="number"
            name="numberOfACCoaches"
            value={updatedData.numberOfACCoaches}
            onChange={handleChange}
          />
        </label>
        <label>
          AC Coach Total Seats:
          <input
            type="number"
            name="acCoachTotalSeats"
            value={updatedData.acCoachTotalSeats}
            onChange={handleChange}
          />
        </label>
        <label>
          Sleeper Coaches:
          <input
            type="number"
            name="numberOfSleeperCoaches"
            value={updatedData.numberOfSleeperCoaches}
            onChange={handleChange}
          />
        </label>
        <label>
          Sleeper Coach Total Seats:
          <input
            type="number"
            name="sleeperCoachTotalSeats"
            value={updatedData.sleeperCoachTotalSeats}
            onChange={handleChange}
          />
        </label>
        <label>
          General Coaches:
          <input
            type="number"
            name="numberOfGeneralCoaches"
            value={updatedData.numberOfGeneralCoaches}
            onChange={handleChange}
          />
        </label>
        <label>
          General Coach Total Seats:
          <input
            type="number"
            name="generalCoachTotalSeats"
            value={updatedData.generalCoachTotalSeats}
            onChange={handleChange}
          />
        </label>
        <button type="button" onClick={handleUpdate}>
          Update Train
        </button>
      </form>
   <NavigatingBack/>
    </div>
  );
};

export default UpdateTrains;
