import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavigatingBack from '../NavigatingBack';

const UpdateRoute = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const routeId = new URLSearchParams(location.search).get('routeId');

  const [updatedData, setUpdatedData] = useState({
    routeId: '',
    source: '',
    destination: '',
    totalKms: 1,
  });

  useEffect(() => {
    // Fetch individual route data based on routeId
    fetch(`http://localhost:8086/routes/${routeId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setUpdatedData({
          routeId: data.routeId,
          source: data.source,
          destination: data.destination,
          totalKms: Number(data.totalKms),
        });
      })
      .catch((error) =>
        console.error('Error fetching individual route data:', error)
      );
  }, [routeId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUpdatedData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleUpdate = () => {
    const updatedDataJson = JSON.stringify(updatedData);

    fetch(`http://localhost:8086/routes`, {
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
      .then((updatedRoute) => {
        console.log('Route updated successfully:', updatedRoute);
        alert(`Route ${updatedRoute.routeId} updated successfully`);
        navigate('./../');
      })
      .catch((error) => {
        console.error('Error updating route data:', error);
      });
  };

  return (
    <div>
      <h2>Update a Route</h2>
      <form>
        <label>
          Route Id:
          <input
            type="text"
            name="routeId"
            value={updatedData.routeId}
            onChange={handleChange}
          />
        </label>
        <label>
          Source:
          <input
            type="text"
            name="source"
            value={updatedData.source}
            onChange={handleChange}
          />
        </label>
        <label>
          Destination:
          <input
            type="text"
            name="destination"
            value={updatedData.destination}
            onChange={handleChange}
          />
        </label>
        <label>
          Total Kms:
          <input
            type="number"
            name="totalKms"
            value={updatedData.totalKms}
            onChange={handleChange}
          />
        </label>
        <button type="button" onClick={handleUpdate}>
          Update Route
        </button>
      </form>
      <NavigatingBack />
    </div>
  );
};

export default UpdateRoute;
