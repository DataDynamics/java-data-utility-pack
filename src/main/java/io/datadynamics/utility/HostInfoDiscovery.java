package io.datadynamics.utility;


/**
 * Interface used to discover a {@link HostInfo} from a system.
 *
 * @author Janne Valkealahti
 */
public interface HostInfoDiscovery {

    /**
     * Gets the host info.
     *
     * @return the host info
     */
    HostInfo getHostInfo();

    /**
     * Class wrapping host information.
     */
    public static final class HostInfo {
        private final String address;
        private final String hostname;

        /**
         * Instantiates a new host info.
         *
         * @param address  the address
         * @param hostname the hostname
         */
        public HostInfo(String address, String hostname) {
            super();
            this.address = address;
            this.hostname = hostname;
        }

        /**
         * Gets the ip address as represented in string.
         *
         * @return the ip address
         */
        public String getAddress() {
            return address;
        }

        /**
         * Gets the hostname.
         *
         * @return the hostname
         */
        public String getHostname() {
            return hostname;
        }
    }

}