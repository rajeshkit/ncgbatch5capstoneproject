import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaTrash } from 'react-icons/fa';
import NavigatingBack from '../NavigatingBack';

const CheckAllSchedules = () => {
  const navigate = useNavigate();
  const [schedules, setSchedules] = useState([]);
  const [filteredSchedules, setFilteredSchedules] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const handleSearch = useCallback(() => {
    const searchTermLower = searchTerm.toLowerCase();
    const filtered = schedules.filter((schedule) => {
      const idMatches = schedule.scheduleId && schedule.scheduleId.toLowerCase().includes(searchTermLower);
      const departureMatches =
        schedule.departureDateTime &&
        schedule.departureDateTime.toLocaleString().toLowerCase().includes(searchTermLower);
      const arrivalMatches =
        schedule.arrivalDateTime &&
        schedule.arrivalDateTime.toLocaleString().toLowerCase().includes(searchTermLower);
  
      return idMatches || departureMatches || arrivalMatches;
    });
    setFilteredSchedules(filtered);
  }, [searchTerm, schedules]);
  
  const formatDate = (dateTimeArray) => {
    const date = new Date(...dateTimeArray);
    const options = {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    };
    return date.toLocaleString(undefined, options);
  };

  useEffect(() => {
    // Fetch data from the server
    fetch('http://localhost:8087/schedules')
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        setSchedules(data);
        console.log(data);
        setFilteredSchedules(data); // Initialize filteredSchedules with all schedules
      })
      .catch((error) => setError(error.message))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => {
    handleSearch();
  }, [handleSearch, searchTerm]);

  const handleRowClick = (schedule) => {
    // Navigate to the update page with the schedule data
    navigate(`/manage-schedules/update?scheduleId=${schedule.scheduleId}`);
  };

  const handleDeleteSchedule = (schedule) => {
    navigate(`/manage-schedules/delete?scheduleId=${schedule.scheduleId}`);
  };

  if (loading) {
    return (
      <>
        <div>Loading...</div>
        <div>No Schedules Found!! </div>
      </>
    );
  }

  if (error) {
    return <div>No Schedules Found!! </div>;
  }

  return (
    <div>
      <h2>Check All Schedules</h2>
      <NavigatingBack />
      
      <div>
        <input
          type="text"
          placeholder="Enter Schedule No. or Date"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>
      <p id='note'>--------click on any row to modify---------</p>
      <table>
        <thead>
          <tr>
            <th>Schedule Number</th>
            <th>departureDateTime</th>
            <th>arrivalDateTime</th>
            <th>Train Number</th>
            <th>routeId</th>
          </tr>
        </thead>
        <tbody>
          {filteredSchedules.map((schedule) => (
            <tr key={schedule.scheduleId} onClick={() => handleRowClick(schedule)}>
              <td>{schedule.scheduleId}</td>
              <td>{schedule.departureDateTime && formatDate(schedule.departureDateTime)}</td>
<td>{schedule.arrivalDateTime && formatDate(schedule.arrivalDateTime)}</td>
              <td>{schedule.trainNumber || 'N/A'}</td>
              <td>{schedule.routeId || 'N/A'}</td>
              <td>
                <button onClick={(e) => { e.stopPropagation(); handleDeleteSchedule(schedule); }}>
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

export default CheckAllSchedules;
