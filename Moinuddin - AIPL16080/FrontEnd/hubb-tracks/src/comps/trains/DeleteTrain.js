import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const DeleteTrain = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [trainNumber, setTrainNumber] = useState(new URLSearchParams(location.search).get('trainNumber'));

  const handleDeleteTrain = () => {
    if (!trainNumber.trim()) {
      alert('Please enter a valid train number.');
      return;
    }

  
    fetch(`http://localhost:8085/trains/${trainNumber}`, {
      method: 'DELETE',
    })
      .then((response) => {
        if (!response.ok) {
          if (response.headers.get('content-type')?.includes('application/json')) {
            return response.json().then((errorData) => {
              console.error('Error deleting train:', errorData);
  
            });
          } else {
            return response.text().then((errorText) => {
              console.error('Error deleting train:', errorText);
            });
          }
        }
        return response.json();
      })
      .then(() => {
        console.log(`Train with number ${trainNumber} deleted successfully`);
        alert(`Train with number ${trainNumber} deleted successfully`);
      
        // Navigate back to the list or wherever you want to go
        navigate('./../');
      })
      .catch((error) => {
        console.error('Error deleting train:', error);
        // Handle the error as needed
      });
  };
  
  return (
    <div>
      <h2>Delete Train</h2>
      <form>
        <label>
          Enter Train Number:
          <input
            type="text"
            value={trainNumber}
            onChange={(e) => setTrainNumber(e.target.value)}
          />
        </label>
        <button type="button" onClick={handleDeleteTrain}>
          Delete Train
        </button>

      </form>
      <NavigatingBack/>
    </div>
  );
};

export default DeleteTrain;
