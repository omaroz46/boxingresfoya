package ch.ozan.omar.m295.boxingresfoya;

import ch.ozan.omar.m295.boxingresfoya.controller.ControllerTestSuite;
import ch.ozan.omar.m295.boxingresfoya.service.ServiceTestSuite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ControllerTestSuite.class, ServiceTestSuite.class})
public class BoxingresfoyaTestSuite {
}
