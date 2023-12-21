package Service;

import Model.Schedule;
import Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final Schedule scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = (Schedule) scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        Optional<Schedule> existingScheduleOptional = scheduleRepository.findById(id);
        if (existingScheduleOptional.isPresent()) {
            updatedSchedule.setId(id);
            return scheduleRepository.save(updatedSchedule);
        }
        return null;
    }

    public boolean deleteScheduleById(Long id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        if (scheduleOptional.isPresent()) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false; 
    }

    public Schedule addSchedule(Schedule schedule) {
        return schedule;
    }

    public void deleteSchedule(Long id) {
    }

    public Schedule getScheduleDetailsById(long l) {
        return null;
    }
}
