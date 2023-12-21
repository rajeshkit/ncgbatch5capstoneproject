package com.altimetrik.schedule.client;

import com.altimetrik.schedule.Dto.Train;
import com.altimetrik.schedule.exception.InvalidTrainException;

public interface TrainClient {
    public Train getTrain(int trainNumber) throws InvalidTrainException;
}
