import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();

  const handleButtonClick = (route) => {
    navigate(route);
  };
  return (
    <div>
      <h2>Welcome to the Home Page</h2>
    
        <button onClick={() => handleButtonClick("/manage-trains")}>
          Manage Trains
        </button>

        <button onClick={() => handleButtonClick("/manage-routes")}>
          Manage Routes
        </button>
    
    
        <button onClick={() => handleButtonClick("/manage-schedules")}>
          Schedule Trains
        </button>
      
    

      
    
        <button onClick={() => handleButtonClick("/manage-tickets")}>
          Book Ticket
        </button>
      
    </div>
  );
};

export default Home;
