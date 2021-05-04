import control.CardController;
import entity.Card;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

public class CardControllerTest extends DBTestCase {

    private final FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();

    @Override
    protected IDataSet getDataSet() throws Exception {
        return builder.build(getClass().getResourceAsStream("card-data.xml"));
    }

    public CardControllerTest(String testName) {
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
        CardController cardController = new CardController();
        Card card = new Card();
        card.setQuestion("test?");
        card.setAnswer("test!");
        cardController.addCard(card);
        card = new Card();
        card.setQuestion("yes?");
        card.setAnswer("NO!");
        cardController.addCard(card);

        IDataSet dataSet = getConnection().createDataSet();
        ITable table = dataSet.getTable("cards");

        IDataSet expected = builder.build(getClass().getResourceAsStream("card-expected-save.xml"));
        ITable expectedTable = expected.getTable("cards");

        Assertion.assertEquals(expectedTable, table);
    }

    @Test
    public void testUpdate() throws Exception {
        CardController cardController = new CardController();
        Card card = new Card();
        card.setId(1L);
        card.setQuestion("test?");
        card.setAnswer("test!!!");
        cardController.updateCard(card);
        card = new Card();
        card.setId(2L);
        card.setQuestion("yes?");
        card.setAnswer("YES!");
        cardController.updateCard(card);

        IDataSet dataSet = getConnection().createDataSet();
        ITable table = dataSet.getTable("cards");

        IDataSet expected = builder.build(getClass().getResourceAsStream("card-expected-update.xml"));
        ITable expectedTable = expected.getTable("cards");

        Assertion.assertEquals(expectedTable, table);
    }

    @Test
    public void testDelete() throws Exception {
        CardController cardController = new CardController();
        Card card = new Card();
        card.setId(1L);
        card.setQuestion("test?");
        card.setAnswer("test!");
        cardController.deleteCard(card);

        IDataSet dataSet = getConnection().createDataSet();
        ITable table = dataSet.getTable("cards");

        IDataSet expected = builder.build(getClass().getResourceAsStream("card-expected-delete.xml"));
        ITable expectedTable = expected.getTable("cards");

        Assertion.assertEquals(expectedTable, table);
    }

    @Test
    public void testGetById() {
        CardController cardController = new CardController();
        Card card = cardController.getCardById(1L);

        Card expectedCard = new Card();
        expectedCard.setId(1L);
        expectedCard.setQuestion("test?");
        expectedCard.setAnswer("test!");

        Assert.assertEquals(expectedCard, card);
    }
}
