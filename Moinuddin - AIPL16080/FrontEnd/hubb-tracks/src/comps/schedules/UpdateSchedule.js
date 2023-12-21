import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const UpdateSchedule = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const scheduleId = new URLSearchParams(location.search).get('scheduleId');

  const [updatedData, setUpdatedData] = useState({
    scheduleId: '',
    departureDateTime: '',
    arrivalDateTime: '',
    routeId: '',
    trainNumber: ''
  });

  
  useEffect(() => {
    // Fetch individual schedule data based on scheduleId
    fetch(`http://localhost:8087/schedules/${scheduleId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        // console.log(data);
        setUpdatedData({
          scheduleId: data.scheduleId,
          departureDateTime: formatDateTime(data.departureDateTime),
          arrivalDateTime: formatDateTime(data.arrivalDateTime),
          routeId: data.routeId,
          trainNumber: data.trainNumber
        });
      })
      .catch((error) => console.error('Error fetching individual schedule data:', error));
  }, [scheduleId]);
  

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleUpdate = () => {
    const updatedDataJson = JSON.stringify(updatedData);
    console.log(updatedDataJson);

    fetch(`http://localhost:8087/schedules`, {
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
      .then((updatedSchedule) => {
        console.log('Schedule updated successfully:', updatedSchedule);
        alert(`Schedule ${updatedSchedule.scheduleId} updated successfully`);
        navigate('./../');
      })
      .catch((error) => {
        console.error('Error updating schedule data:', error);
      });
  };
  const formatDateTime = (dateTimeArray) => {
    const date = new Date(...dateTimeArray);
    return date.toISOString().slice(0, 16);
  };
  

  return (
    <div>
      <h2>Update a Schedule</h2>
      <form>
      <label>
          Schedule Id:
          <input
            type="text"
            name="scheduleId"
            value={updatedData.scheduleId}
            onChange={handleChange}
          />
        </label>
        <label>
  Departure Date and Time:
  <input
    type="datetime-local"
    name="departureDateTime"
    value={updatedData.departureDateTime}
    onChange={handleChange}
  />
</label>
<label>
  Arrival Date and Time:
  <input
    type="datetime-local"
    name="arrivalDateTime"
    value={updatedData.arrivalDateTime}
    onChange={handleChange}
  />
</label>

        <label>
        routeId:
          <input
            type="text"
            name="routeId"
            value={updatedData.routeId}
            onChange={handleChange}
          />
        </label>
        <label>
        trainNumber:
          <input
            type="text"
            name="trainNumber"
            value={updatedData.trainNumber}
            onChange={handleChange}
          />
        </label>
        <button type="button" onClick={handleUpdate}>
          Update Schedule
        </button>
      </form>
   <NavigatingBack/>
    </div>
  );
};

export default UpdateSchedule;
