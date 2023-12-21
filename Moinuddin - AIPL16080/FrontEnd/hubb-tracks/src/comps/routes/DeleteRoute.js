import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const DeleteRoute = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [routeId, setRouteNumber] = useState(new URLSearchParams(location.search).get('routeId'));

  const handleDeleteRoute = () => {
    if (routeId==null) {
      alert('Please enter a valid route Id.');
      return;
    }

  
    fetch(`http://localhost:8086/routes/${routeId}`, {
      method: 'DELETE',
    })
      .then((response) => {
        if (!response.ok) {
          if (response.headers.get('content-type')?.includes('application/json')) {
            return response.json().then((errorData) => {
              console.error('Error deleting route:', errorData);
  
            });
          } else {
            return response.text().then((errorText) => {
              console.error('Error deleting route:', errorText);
            });
          }
        }
        return response.json();
      })
      .then(() => {
        console.log(`Route with Id ${routeId} deleted successfully`);
        alert(`Route with Id ${routeId} deleted successfully`);
      
        // Navigate back to the list or wherever you want to go
        navigate('./../');
      })
      .catch((error) => {
        console.error('Error deleting route:', error);
        // Handle the error as needed
      });
  };
  
  return (
    <div>
      <h2>Delete Route</h2>
      <form>
        <label>
          Enter Route Number:
          <input
            type="text"
            value={routeId}
            onChange={(e) => setRouteNumber(e.target.value)}
          />
        </label>
        <button type="button" onClick={handleDeleteRoute}>
          Delete Route
        </button>

      </form>
      <NavigatingBack/>
    </div>
  );
};

export default DeleteRoute;
