// src/App.js
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './comps/Home';
import TrainsInfoHome from './comps/trains/TrainsInfoHome';
import AddTrain from './comps/trains/AddTrain';
import DeleteTrain from './comps/trains/DeleteTrain';
import UpdateTrains from './comps/trains/UpdateTrains';
import CheckAllTrains from './comps/trains/CheckAllTrains';
import TrainRoutesHome from './comps/routes/TrainRoutesHome';
import AddRoute from './comps/routes/AddRoute';
import DeleteRoute from './comps/routes/DeleteRoute';
import UpdateRoute from './comps/routes/UpdateRoute';
import CheckAllRoutes from './comps/routes/CheckAllRoutes';
import TrainSchedulesHome from './comps/schedules/TrainSchedulesHome';
import AddSchedule from './comps/schedules/AddSchedule';
import DeleteSchedule from './comps/schedules/DeleteSchedule';
import UpdateSchedule from './comps/schedules/UpdateSchedule';
import CheckAllSchedules from './comps/schedules/CheckAllSchedules';
import TicketBookingHome from './comps/ticketBooking/TicketBookingHome';
import AddTicket from './comps/ticketBooking/AddTicket';
import CancelTicket from './comps/ticketBooking/CancelTicket';
import UpdateTicket from './comps/ticketBooking/UpdateTicket';
import CheckAllTickets from './comps/ticketBooking/CheckAllTickets';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />

        {/* Trains Routes */}
        <Route path="/manage-trains" element={<TrainsInfoHome />} />
        <Route path="/manage-trains/check-all" element={<CheckAllTrains />} />
        <Route path="/manage-trains/add" element={<AddTrain />} />
        <Route path="/manage-trains/delete" element={<DeleteTrain />} />
        <Route path="/manage-trains/update" element={<UpdateTrains />} />


        {/* Routes Routes */}
        <Route path="/manage-routes" element={<TrainRoutesHome />} />
        <Route path="/manage-routes/check-all" element={<CheckAllRoutes />} />
        <Route path="/manage-routes/add" element={<AddRoute />} />
        <Route path="/manage-routes/delete" element={<DeleteRoute />} />
        <Route path="/manage-routes/update" element={<UpdateRoute />} />

        {/* Schedules Routes */}
        <Route path="/manage-schedules" element={<TrainSchedulesHome />} />
        <Route path="/manage-schedules/check-all" element={<CheckAllSchedules />} />
        <Route path="/manage-schedules/add" element={<AddSchedule />} />
        <Route path="/manage-schedules/delete" element={<DeleteSchedule />} />
        <Route path="/manage-schedules/update" element={<UpdateSchedule />} />

        {/* Ticket Booking Routes */}
        <Route path="/manage-tickets" element={<TicketBookingHome />} />
        <Route path="/manage-tickets/check-all" element={<CheckAllTickets />} />
        <Route path="/manage-tickets/add" element={<AddTicket />} />
        <Route path="/manage-tickets/cancel" element={<CancelTicket />} />
        <Route path="/manage-tickets/update" element={<UpdateTicket />} />
      </Routes>
    </Router>
  );
}

export default App;
