package ch.ozan.omar.m295.boxingresfoya.controller;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test suite for controller classes.
 */
@Suite
@SelectClasses({BoxingClubControllerTest.class, EventControllerTest.class, FightControllerTest.class, FighterControllerTest.class})
public class ControllerTestSuite {
}
