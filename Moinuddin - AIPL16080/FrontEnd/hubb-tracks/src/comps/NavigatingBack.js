import React from 'react'
import { useNavigate } from 'react-router-dom';

const NavigatingBack = () => {
    const navigate = useNavigate();
    const handleNavigateToHome = () => {
        navigate('./../');
      };
  return (
    <button type="button" className='navgating_back' id="navigating_back" onClick={handleNavigateToHome}>
    Go Back
  </button>
  )
}

export default NavigatingBack