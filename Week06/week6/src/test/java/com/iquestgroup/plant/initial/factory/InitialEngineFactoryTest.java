package com.iquestgroup.plant.initial.factory;

import com.iquestgroup.plant.initial.domain.Employee;
import com.iquestgroup.plant.initial.domain.Engine;
import com.iquestgroup.plant.initial.domain.EngineArchitecture;
import com.iquestgroup.plant.initial.domain.EngineComponent;
import com.iquestgroup.plant.initial.exception.InsufficientStockException;
import com.iquestgroup.plant.initial.exception.UnauthorizedEmployeeException;
import com.iquestgroup.plant.initial.exception.UnqualifiedEmployeeException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class InitialEngineFactoryTest {

    private static EngineFactory factory;
    private static final List<Employee> employees = new LinkedList<>();
    private static final List<EngineComponent> engineComponents = new LinkedList<>();
    private static Employee e3;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initData() {

        Employee e1 = new Employee("Tim", true, true);
        Employee e2 = new Employee("Tom", false, false);
        e3 = new Employee("Carl", false, true);
        Employee e5 = new Employee("Mark", false, true);
        Employee e6 = new Employee("Calvin", true, false);

        employees.add(e1);
        employees.add(e2);
        employees.add(e5);
        employees.add(e6);

        for (int i = 1; i <= 50; i++) {

            Random rnd = new Random();
            engineComponents.add(new EngineComponent(String.valueOf(rnd.nextInt()), i * 5));
        }
        factory = new EngineFactory(employees, engineComponents);
    }

    @Test
    public void testManufactureEngines_thrownUnauthorizedEmployeeException() {

        expectedException.expect(UnauthorizedEmployeeException.class);
        expectedException.expectMessage("Employee [" + e3.getName() + "]is not authorized to work at this factory!");
        factory.manufactureEngines(2, e3);
    }

    @Test
    public void testManufactureEngines_thrownUnqualifiedEmployeeException() {

        expectedException.expect(UnqualifiedEmployeeException.class);
        expectedException.expectMessage("Employee [" + employees.get(3).getName() + "] is not qualified to build engines!");
        factory.manufactureEngines(2, employees.get(3));
    }

    @Test
    public void testManufactureEngines_thrownInsufficientStockException() {

        expectedException.expect(InsufficientStockException.class);
        expectedException.expectMessage("Not enough stock to build [" + 300 + "] engines!");
        factory.manufactureEngines(300, employees.get(0));
    }

    @Test
    public void testManufactureEngines_numberEnginesCreated() {
        List<Engine> result = factory.manufactureEngines(2, employees.get(0));
        Engine engine1 = new Engine(EngineArchitecture.L4, 2.0, 210);
        Engine engine2 = new Engine(EngineArchitecture.L4, 2.0, 210);
        List<Engine> ans = new ArrayList<>();
        ans.add(engine1);
        ans.add(engine2);
        if (ans.size() != result.size()) {
            Assertions.fail("Number of build engines does not correspond");
        }
    }

    @Test
    public void testManufactureEngines_thrownInsufficientStockExceptionEmptyComponentsList() {

        expectedException.expect(InsufficientStockException.class);
        expectedException.expectMessage("Not enough stock to build [" + 20 + "] engines!");
        engineComponents.removeAll(engineComponents);
        factory.manufactureEngines(20, employees.get(0));
    }

    @Test
    public void testGetComponentsPerEngine() {

        int result = factory.getComponentsPerEngine();
        Assertions.assertEquals(3, result, "ComponentsPerEngine field does not match");
    }

}