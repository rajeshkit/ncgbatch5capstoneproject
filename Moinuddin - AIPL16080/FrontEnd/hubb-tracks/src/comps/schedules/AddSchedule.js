import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const AddSchedule = () => {
  const navigate = useNavigate();

  const [newScheduleData, setNewScheduleData] = useState({
    scheduleId: '',
    departureDateTime: '',
    arrivalDateTime: '',
    routeId: '',
    trainNumber: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewScheduleData((prevData) => ({ ...prevData, [name]: value }));
  };


  const handleAddSchedule = () => {
    const newScheduleDataJson = JSON.stringify(newScheduleData);
    console.log(newScheduleDataJson);
  
    fetch('http://localhost:8087/schedules', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: newScheduleDataJson,
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
      .then((addedSchedule) => {
        console.log('Schedule added successfully:', addedSchedule);
        alert(`Schedule ${addedSchedule.scheduleId} added successfully`);
  
        // Navigate back to the Schedule Home
        navigate('/manage-schedules');
      })
      .catch((error) => {
        alert('Error adding schedule:', error);
        console.error('Error adding schedule:', error);
      });
  };
  return (
    <div>
      <h2>Add New Schedule</h2>
      <form>
      <label>
          Schedule Id:
          <input
            type="text"
            name="scheduleId"
            value={newScheduleData.scheduleId}
            onChange={handleChange}
          />
        </label>
        <label>
  Departure Date and Time:
  <input
    type="datetime-local"
    name="departureDateTime"
    value={newScheduleData.departureDateTime}
    onChange={handleChange}
  />
</label>
<label>
  Arrival Date and Time:
  <input
    type="datetime-local"
    name="arrivalDateTime"
    value={newScheduleData.arrivalDateTime}
    onChange={handleChange}
  />
</label>

        <label>
        routeId:
          <input
            type="text"
            name="routeId"
            value={newScheduleData.routeId}
            onChange={handleChange}
          />
        </label>
        <label>
        trainNumber:
          <input
            type="text"
            name="trainNumber"
            value={newScheduleData.trainNumber}
            onChange={handleChange}
          />
        </label>
        <button type="button" onClick={handleAddSchedule}>
          Add Schedule
        </button>
      </form>
      <NavigatingBack/>
    </div>
  );
};

export default AddSchedule;
