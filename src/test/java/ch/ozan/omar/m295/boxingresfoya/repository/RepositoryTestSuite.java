package ch.ozan.omar.m295.boxingresfoya.repository;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({BoxingClubRepositoryTest.class, EventRepositoryTest.class, FighterRepositoryTest.class, FightRepositoryTest.class})
public class RepositoryTestSuite {
}
