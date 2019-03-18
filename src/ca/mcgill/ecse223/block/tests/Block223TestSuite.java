package ca.mcgill.ecse223.block.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.mcgill.ecse223.block.tests.f01.AddGameTests;
import ca.mcgill.ecse223.block.tests.f02.DefineGameSettingsTests;
import ca.mcgill.ecse223.block.tests.f03.DeleteGameTests;
import ca.mcgill.ecse223.block.tests.f04.UpdateGameTests;
import ca.mcgill.ecse223.block.tests.f05.AddBlockTests;
import ca.mcgill.ecse223.block.tests.f06.DeleteBlockTests;
import ca.mcgill.ecse223.block.tests.f07.UpdateBlockTests;
import ca.mcgill.ecse223.block.tests.f08.PositionBlockTests;
import ca.mcgill.ecse223.block.tests.f09.MoveBlockTests;
import ca.mcgill.ecse223.block.tests.f10.RemoveBlockTests;
import ca.mcgill.ecse223.block.tests.f11.SaveGameTests;
import ca.mcgill.ecse223.block.tests.f12.LogInLogOutTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AddGameTests.class,
   DefineGameSettingsTests.class,
   DeleteGameTests.class,
   UpdateGameTests.class,
   AddBlockTests.class,
   DeleteBlockTests.class,
   UpdateBlockTests.class,
   PositionBlockTests.class,
   MoveBlockTests.class,
   RemoveBlockTests.class,
   SaveGameTests.class,
   LogInLogOutTests.class,
})
public class Block223TestSuite {

}
