package com.railways.schedule.controller;

import com.railways.schedule.exceptions.ScheduleIdNotFoundException;
import com.railways.schedule.model.Schedule;
import com.railways.schedule.model.ScheduleDemo;
import com.railways.schedule.services.ScheduleServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleServices scheduleServices;

    @PostMapping(value = "/scheduledetails")
    public Schedule insertScheduleDetails(@RequestBody @Valid ScheduleDemo scheduleDemo)  {
        return scheduleServices.insertScheduleDetails(scheduleDemo);
    }

    @GetMapping(value = "/scheduledetails")
    public List<Schedule> getAllScheduleDetails() {
        return scheduleServices.getAllScheduleDetails();
    }

    @GetMapping(value = "/scheduledetails/{id}")
    public Schedule getScheduleById(@PathVariable("id") long routeIdToSearch)  throws ScheduleIdNotFoundException {
        return scheduleServices.getScheduleById(routeIdToSearch);
    }

    @PutMapping(value = "/scheduledetails")
    public Schedule updateScheduleById(@RequestBody @Valid Schedule schedule)  throws ScheduleIdNotFoundException {
        return scheduleServices.updateScheduleById(schedule);

    }

    @DeleteMapping(value = "/scheduledetails/{id}")
    public String deleteScheduleById(@PathVariable("id") long scheduleIdToDelete)  throws ScheduleIdNotFoundException {
        return scheduleServices.deleteScheduleById(scheduleIdToDelete);
    }

}
