package io.datadynamics.utility.hadoop;


import org.apache.hadoop.fs.FileStatus;

/**
 * 날짜를 기반으로 정렬 기능을 제공하는 Hadoop {@link org.apache.hadoop.fs.FileStatus}.
 */
public class SortableFileStatus implements Comparable<SortableFileStatus> {

    /**
     * 파일 메타데이터.
     */
    FileStatus fileStatus;

    /**
     * 기본 생성자.
     *
     * @param fileStatus 파일 메타데이터.
     */
    public SortableFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }

    @Override
    public int compareTo(SortableFileStatus other) {
        FileStatus otherFileStatus = other.getFileStatus();
        if (fileStatus.getModificationTime() > otherFileStatus.getModificationTime()) {
            return 1;
        } else if (fileStatus.getModificationTime() < otherFileStatus.getModificationTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 파일 메타데이터를 반환한다.
     *
     * @return 파일 메타데이터.
     */
    public FileStatus getFileStatus() {
        return fileStatus;
    }
}