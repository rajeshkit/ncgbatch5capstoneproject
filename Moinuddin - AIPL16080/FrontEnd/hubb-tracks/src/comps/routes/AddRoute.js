import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const AddRoute = () => {
  const navigate = useNavigate();

  const [newRouteData, setNewRouteData] = useState({
    routeId: '',
    source: '',
    destination: '',
    totalKms: 1,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewRouteData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleAddRoute = () => {
    const newRouteDataJson = JSON.stringify(newRouteData);
    console.log(newRouteDataJson);

    fetch('http://localhost:8086/routes', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: newRouteDataJson,
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
      .then((addedRoute) => {
        console.log('Route added successfully:', addedRoute);
        alert(`Route ${addedRoute.routeId} added successfully`);

        // Navigate back to the routes management page
        navigate('/manage-routes');
      })
      .catch((error) => {
        alert('Error adding route:', error);
        console.error('Error adding route:', error);
      });
  };

  return (
    <div>
      <h2>Add New Route</h2>
      <form>
        <label>
          Route Id:
          <input
            type="text"
            name="routeId"
            value={newRouteData.routeId}
            onChange={handleChange}
          />
        </label>
        <label>
          Source:
          <input
            type="text"
            name="source"
            value={newRouteData.source}
            onChange={handleChange}
          />
        </label>
        <label>
          Destination:
          <input
            type="text"
            name="destination"
            value={newRouteData.destination}
            onChange={handleChange}
          />
        </label>
        <label>
          Total Kms:
          <input
            type="number"
            name="totalKms"
            value={newRouteData.totalKms}
            onChange={handleChange}
          />
        </label>

        <button type="button" onClick={handleAddRoute}>
          Add Route
        </button>
      </form>
      <NavigatingBack />
    </div>
  );
};

export default AddRoute;