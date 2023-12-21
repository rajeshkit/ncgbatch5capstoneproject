import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const DeleteSchedule = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [scheduleId, setScheduleNumber] = useState(new URLSearchParams(location.search).get('scheduleId'));

  const handleDeleteSchedule = () => {
    if (!scheduleId.trim()) {
      alert('Please enter a valid schedule number.');
      return;
    }

  
    fetch(`http://localhost:8087/schedules/${scheduleId}`, {
      method: 'DELETE',
    })
      .then((response) => {
        if (!response.ok) {
          if (response.headers.get('content-type')?.includes('application/json')) {
            return response.json().then((errorData) => {
              console.error('Error deleting schedule:', errorData);
  
            });
          } else {
            return response.text().then((errorText) => {
              console.error('Error deleting schedule:', errorText);
            });
          }
        }
        return response.json();
      })
      .then(() => {
        console.log(`Schedule with number ${scheduleId} deleted successfully`);
        alert(`Schedule with number ${scheduleId} deleted successfully`);
      
        // Navigate back to the list or wherever you want to go
        navigate('./../');
      })
      .catch((error) => {
        console.error('Error deleting schedule:', error);
        // Handle the error as neederouted
      });
  };
  
  return (
    <div>
      <h2>Delete Schedule</h2>
      <form>
        <label>
          Enter Schedule Number:
          <input
            type="text"
            value={scheduleId}
            onChange={(e) => setScheduleNumber(e.target.value)}
          />
        </label>
        <button type="button" onClick={handleDeleteSchedule}>
          Delete Schedule
        </button>

      </form>
      <NavigatingBack/>
    </div>
  );
};

export default DeleteSchedule;
