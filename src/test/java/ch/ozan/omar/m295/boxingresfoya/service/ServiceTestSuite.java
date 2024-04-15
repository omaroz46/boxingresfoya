package ch.ozan.omar.m295.boxingresfoya.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({BoxingClubServiceTest.class, EventServiceTest.class, FighterServiceTest.class, FightServiceTest.class})
public class ServiceTestSuite {
}
