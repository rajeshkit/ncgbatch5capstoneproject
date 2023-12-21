import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaTrash } from 'react-icons/fa';
import NavigatingBack from '../NavigatingBack';
const CheckAllTrains = () => {
  const navigate = useNavigate();
  const [trains, setTrains] = useState([]);
  const [filteredTrains, setFilteredTrains] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const handleSearch = useCallback(() => {
    const searchTermLower = searchTerm.toLowerCase();
    const filtered = trains.filter(
      (train) =>
        train.trainNumber.toLowerCase().includes(searchTermLower) ||
        train.trainName.toLowerCase().includes(searchTermLower)
    );
    setFilteredTrains(filtered);
  }, [searchTerm, trains]);



  useEffect(() => {
    // Fetch data from the server
    fetch('http://localhost:8085/trains')
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setTrains(data);
        setFilteredTrains(data); // Initialize filteredTrains with all trains
      })
      .catch((error) => setError(error.message))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => {
    handleSearch();
  }, [handleSearch, searchTerm]);

  const handleRowClick = (train) => {
    // Navigate to the update page with the train data
    navigate(`/manage-trains/update?trainNumber=${train.trainNumber}`);
  };
  const handleDeleteTrain=(train)=>{
    navigate(`/manage-trains/delete?trainNumber=${train.trainNumber}`);

  }

  if (loading) {
    return <><div>Loading...</div>
 <div>No Trains Found!! </div></>
  }

  if (error) {
    return <div>No Trains Found!! </div>;
  }
  
  return (
    <div>
      <h2>Check All Trains</h2>
      <NavigatingBack/>
      <div>
        <input
          type="text"
          placeholder="Enter Train No. or Name"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>
      <p id='note'>--------click on any row to modify---------</p>
      <table>
        <thead>
          <tr>
            <th>Train Number</th>
            <th>Train Name</th>
            <th>Total Distance Traveled</th>
            <th>AC Coaches</th>
            <th>AC Coach Total Seats</th>
            <th>Sleeper Coaches</th>
            <th>Sleeper Coach Total Seats</th>
            <th>General Coaches</th>
            <th>General Coach Total Seats</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {filteredTrains.map((train) => (
            <tr key={train.trainNumber} onClick={() => handleRowClick(train)}>
              <td>{train.trainNumber}</td>
              <td>{train.trainName}</td>
              <td>{train.totalDistance}</td>
              <td>{train.numberOfACCoaches}</td>
              <td>{train.acCoachTotalSeats}</td>
              <td>{train.numberOfSleeperCoaches}</td>
              <td>{train.sleeperCoachTotalSeats}</td>
              <td>{train.numberOfGeneralCoaches}</td>
              <td>{train.generalCoachTotalSeats}</td>
              <td>
              <button onClick={(e) => { e.stopPropagation(); handleDeleteTrain(train); }}>
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
export default CheckAllTrains;
