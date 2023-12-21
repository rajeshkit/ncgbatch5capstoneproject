
import React from 'react';
import { useNavigate } from 'react-router-dom';

const TicketBookingHome = () => {
  const navigate = useNavigate();

  const handleButtonClick = (route) => {
    // Use navigate to go to the specified route
    navigate(`/manage-tickets/${route}`);
  };

  return (
    <div>
      <h2>Ticket Booking Home</h2>
      <button onClick={() => handleButtonClick('check-all')}>Check All Tickets</button>
      <button onClick={() => handleButtonClick('add')}>Add Ticket</button>
      <button onClick={() => handleButtonClick('cancel')}>Cancel Ticket</button>
      <button onClick={() => handleButtonClick('update')}>Update Ticket</button>
    </div>
  );
};

export default TicketBookingHome;
