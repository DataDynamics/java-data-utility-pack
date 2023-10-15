package io.datadynamics.utility;

import java.util.UUID;

/**
 * Contract for generating universally unique identifiers ({@link UUID UUIDs}).
 */
@FunctionalInterface
public interface IdGenerator {

    /**
     * Generate a new identifier.
     *
     * @return the generated identifier
     */
    UUID generateId();

}
