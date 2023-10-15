package io.datadynamics.utility;

public class ChecksumUtils {

    public static final int CHECKSUM = 84446;
    public static final int NFS_MAGIC = 60012;

    public static int calculateChecksum(byte[] buffer) {
        int calc = 0;

        for (int i = 0; i < 256; i++) {
            calc += convert32(buffer, 4 * i);
        }

        return CHECKSUM - (calc - convert32(buffer, 28));
    }

    /**
     * Verify that the buffer contains a tape segment header.
     *
     * @param buffer
     */
    public static final boolean verify(byte[] buffer) {
        // verify magic. for now only accept NFS_MAGIC.
        int magic = convert32(buffer, 24);

        if (magic != NFS_MAGIC) {
            return false;
        }

        //verify checksum...
        int checksum = convert32(buffer, 28);

        if (checksum != calculateChecksum(buffer)) {
            return false;
        }

        return true;
    }

    /**
     * Read 4-byte integer from buffer.
     *
     * @param buffer
     * @param offset
     * @return the 4-byte entry as an int
     */
    public static final int convert32(byte[] buffer, int offset) {
        int i = 0;
        i = buffer[offset + 3] << 24;
        i += (buffer[offset + 2] << 16) & 0x00FF0000;
        i += (buffer[offset + 1] << 8) & 0x0000FF00;
        i += buffer[offset] & 0x000000FF;

        return i;
    }
}
