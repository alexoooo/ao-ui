package ao.ui.wicket.common;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * User: alex
 * Date: 1-Jun-2010
 * Time: 8:56:17 PM
 */
public class JettyRunner
{
    //-------------------------------------------------------------------------
    private JettyRunner() {}

    
    //-------------------------------------------------------------------------
    public static void run(
            int port, String urlPath)
    {
        run(port, urlPath, null);
    }

    public static void run(
            int    port,
            String urlPath,
            String modulePath)
    {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });

		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath( urlPath );

        String pathPrefix =
                modulePath == null ||
                System.getProperty("user.dir")
                        .endsWith( modulePath )
                ? "" : modulePath + "/";

        bb.setWar(pathPrefix + "src/main/webapp");


		// START JMX SERVER
		// MBeanServer mBeanServer =
        //      ManagementFactory.getPlatformMBeanServer();
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.getContainer().addEventListener(mBeanContainer);
		// mBeanContainer.start();

		server.addHandler(bb);

		try {
			System.out.println(
                    ">>> STARTING EMBEDDED JETTY SERVER, " +
                        "PRESS ANY KEY TO STOP");

			server.start();
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}

			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
