package ch.ozan.omar.m295.boxingresfoya;

import ch.ozan.omar.m295.boxingresfoya.controller.ControllerTestSuite;
import ch.ozan.omar.m295.boxingresfoya.repository.RepositoryTestSuite;
import ch.ozan.omar.m295.boxingresfoya.service.ServiceTestSuite;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for the entire Boxingresfoya application.
 * This test suite includes all JUnit test classes for controllers, services, and repositories.
 */
@Suite
@SelectClasses({ControllerTestSuite.class, ServiceTestSuite.class, RepositoryTestSuite.class})
public class BoxingresfoyaTestSuite {
}
