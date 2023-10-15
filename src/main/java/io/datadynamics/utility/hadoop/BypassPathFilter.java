package io.datadynamics.utility.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * 지정한 경로의 모든 파일을 그대로 처리하는 파일 필터.
 */
public class BypassPathFilter implements PathFilter {

    @Override
    public boolean accept(Path path) {
        return true;
    }

}