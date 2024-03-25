package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController mockTC;
    TrainUser mockTU;
    TrainSensorImpl TrainSensor;
    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        TrainSensor = new TrainSensorImpl(mockTC, mockTU);

    }

    @Test
    public void AbsuMinMargin() { 
        TrainSensor.overrideSpeedLimit(-1);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void AbsuMaxMargin() { 
        TrainSensor.overrideSpeedLimit(501);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void RelMargin() {
        when(mockTC.getReferenceSpeed()).thenReturn(150);
        TrainSensor.overrideSpeedLimit(2);
        verify(mockTU, times(1)).setAlarmState(true);
    }

    @Test
    public void BetweenMargin() { 
        TrainSensor.overrideSpeedLimit(300);
        verify(mockTU, times(1)).setAlarmState(false);
    }

}
