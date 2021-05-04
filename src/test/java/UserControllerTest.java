import control.UserController;
import entity.User;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

public class UserControllerTest extends DBTestCase {

    private final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();

    @Override
    protected IDataSet getDataSet() throws Exception {
        return builder.build(getClass().getResourceAsStream("user-data.xml"));
    }

    public UserControllerTest(String testName) {
        super(testName);

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "oracle.jdbc.driver.OracleDriver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:oracle:thin:@localhost:1521");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                "theatre");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                "admin");
    }

    @Test
    public void testSave() throws Exception {
        UserController userController = new UserController();
        User user = new User();
        user.setEmail("aaa@test.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
        userController.addUser(user);
        user = new User();
        user.setEmail("test@aaa.com");
        user.setFirstName("bbb");
        user.setLastName("ccc");
        user.setPassword("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
        userController.addUser(user);

        IDataSet dataSet = getConnection().createDataSet();
        ITable table = dataSet.getTable("users");

        IDataSet expected = builder.build(getClass().getResourceAsStream("user-expected-save.xml"));
        ITable expectedTable = expected.getTable("users");

        Assertion.assertEquals(expectedTable, table);
    }

    @Test
    public void testGetById() {
        UserController userController = new UserController();
        User user = userController.getUserByEmail("test@test.com");

        User expectedUser = new User();
        expectedUser.setEmail("test@test.com");
        expectedUser.setFirstName("test");
        expectedUser.setLastName("test");
        expectedUser.setPassword("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");

        Assert.assertEquals(expectedUser, user);
    }
}
