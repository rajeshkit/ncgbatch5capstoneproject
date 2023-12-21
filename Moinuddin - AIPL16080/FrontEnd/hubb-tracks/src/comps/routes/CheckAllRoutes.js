import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaTrash } from 'react-icons/fa';
import NavigatingBack from '../NavigatingBack';

const CheckAllRoutes = () => {
  const navigate = useNavigate();
  const [routes, setRoutes] = useState([]);
  const [filteredRoutes, setFilteredRoutes] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const handleSearch = useCallback(() => {
    const searchTermLower = searchTerm.toLowerCase();
    const filtered = routes.filter(
      (route) =>
        (route.routeId && route.routeId.toLowerCase().includes(searchTermLower)) ||
        (route.source && route.source.toLowerCase().includes(searchTermLower))||
        (route.destination && route.destination.toLowerCase().includes(searchTermLower))
    );
    setFilteredRoutes(filtered);
  }, [searchTerm, routes]);
  
  useEffect(() => {
    // Fetch data from the server
    fetch('http://localhost:8086/routes')
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setRoutes(data);
        setFilteredRoutes(data); // Initialize filteredRoutes with all routes
      })
      .catch((error) => setError(error.message))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => {
    handleSearch();
  }, [handleSearch, searchTerm]);

  const handleRowClick = (route) => {
    // Navigate to the update page with the route data
    navigate(`/manage-routes/update?routeId=${route.routeId}`);
  };

  const handleDeleteRoute = (route) => {
    navigate(`/manage-routes/delete?routeId=${route.routeId}`);
  };

  if (loading) {
    return (
      <div>
        <div>Loading...</div>
        <div>No Routes Found!!</div>
      </div>
    );
  }

  if (error) {
    return <div>No Routes Found!!</div>;
  }

  return (
    <div>
      <h2>Check All Routes</h2>
      <NavigatingBack />
      
      <div>
        <input
          type="text"
          placeholder="Enter Route No. or Name"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>
      <p id='note'>--------click on any row to modify---------</p>
      <table>
        <thead>
          <tr>
            <th>Route Id</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Total Distance</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {filteredRoutes.map((route) => (
            <tr key={route.routeId} onClick={() => handleRowClick(route)}>
              <td>{route.routeId}</td>
              <td>{route.source}</td>
              <td>{route.destination}</td>
              <td>{route.totalKms}</td>
              <td>
                <button onClick={(e) => { e.stopPropagation(); handleDeleteRoute(route); }}>
                  <FaTrash />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CheckAllRoutes;
