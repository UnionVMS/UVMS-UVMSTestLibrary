package eu.europa.ec.fisheries.uvms;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2gis.h2spatialext.CreateSpatialExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@Slf4j
public abstract class BaseDAOTest extends BaseUnitilsTest {

    private final String TEST_DB_USER = "sa";
    private final String TEST_DB_PASSWORD = "";
    private final String TEST_DB_URL = "jdbc:h2:mem:testdb;LOCK_MODE=0;INIT=CREATE SCHEMA IF NOT EXISTS "+ getSchema() +";DATABASE_TO_UPPER=false;TRACE_LEVEL_SYSTEM_OUT=1";

    protected DataSource ds = JdbcConnectionPool.create(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);

    protected EntityManager em;

    @SneakyThrows
    public BaseDAOTest() {

        CreateSpatialExtension.initSpatialExtension(ds.getConnection());

        log.info("BuildingEntityManager for unit tests");
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
        em = emFactory.createEntityManager();
    }

    protected abstract String getSchema();

    protected abstract String getPersistenceUnitName();
}
