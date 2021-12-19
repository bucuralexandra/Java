package com.iquestgroup.plant.reorg.factory;

import com.iquestgroup.plant.reorg.domain.Employee;
import com.iquestgroup.plant.reorg.domain.Engine;
import com.iquestgroup.plant.reorg.domain.EngineArchitecture;
import com.iquestgroup.plant.reorg.service.EmployeeService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReOrgEngineFactoryTest {

    private static Employee goodEmployee = new Employee("Tom");
    private List<Engine> manufactureEngines;

    @Mock
    private EmployeeService employeeServiceMock;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testManufactureEngines_whenQualifiedEmployee() {

        assertNotNull(employeeServiceMock);
        when(employeeServiceMock.isAssemblyLineWorker(goodEmployee)).thenReturn(true);
        manufactureEngines = new ArrayList<>();
        manufactureEngines.add(new Engine(EngineArchitecture.L4, 2.0, 210));
        EngineFactory f = new EngineFactory(employeeServiceMock);
        List<Engine> resultedEngines = f.manufactureEngines(1, goodEmployee);
        Assertions.assertTrue(sameEngines(manufactureEngines, resultedEngines));
    }

    private boolean sameEngines(List<Engine> provided, List<Engine> result) {
        if (provided.size() != result.size())
            return false;
        boolean existingEngine = false;
        for (Engine eng : provided) {
            existingEngine = false;
            for (Engine i : result) {
                if (eng.getDisplacement() == i.getDisplacement() &&
                        eng.getHorsePower() == i.getHorsePower() &&
                        eng.getEngineArchitecture() == i.getEngineArchitecture()) {
                    existingEngine = true;
                    break;
                }
            }
        }
        return existingEngine;
    }
}