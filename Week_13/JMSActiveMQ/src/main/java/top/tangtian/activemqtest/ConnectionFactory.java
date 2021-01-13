package top.tangtian.activemqtest;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangtian
 * @version 1.0
 * @className ConnectionFactory
 * @description
 * @date 2021/1/11 7:49 AM
 **/
public class ConnectionFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);
    // Specify the connection parameters.
    private final static String WIRE_LEVEL_ENDPOINT
            = "tcp://120.79.218.62:61616";
    //    private final static String ACTIVE_MQ_USERNAME = "admin";
    //    private final static String ACTIVE_MQ_PASSWORD = "admin";

    public static PooledConnectionFactory createPooledConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
        // Create a pooled connection factory.
        final PooledConnectionFactory pooledConnectionFactory =
                new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(connectionFactory);
        pooledConnectionFactory.setMaxConnections(10);
        return pooledConnectionFactory;
    }

    public static ActiveMQConnectionFactory createActiveMQConnectionFactory() {
        // Create a connection factory.
        final ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(WIRE_LEVEL_ENDPOINT);

        // Pass the username and password.
        // connectionFactory.setUserName(ACTIVE_MQ_USERNAME);
        // connectionFactory.setPassword(ACTIVE_MQ_PASSWORD);
        return connectionFactory;
    }
}
