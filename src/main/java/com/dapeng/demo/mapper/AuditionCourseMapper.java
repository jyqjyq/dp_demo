package com.dapeng.demo.mapper;

import com.dapeng.demo.models.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@Mapper
public interface AuditionCourseMapper  {


    List<String> getCourseList (
            @Param("date") Date date,
            @Param("college") String college);

    AuditionCourseSummaryDTO getSummary(@Param("date") Date date,
                                        @Param("classCodes") List<String> classCodes,
                                        @Param("courseName") String courseName);

    List<AuditionCourseDetailDTO> getAuditionCourseDetails(@Param("date") Date date,
                                                           @Param("classCodes") List<String> classCodes,
                                                           @Param("courseName") String courseName,
                                                           @Param("sortType") String sortType);

    CourseAndIssueDTO getCourseAndIssue(@Param("issueId") String issueId);

    List<ListenSituationDTO> getListenSituation(@Param("issueId") String issueId, @Param("courseName") String courseName, @Param("issueName") String issueName, @Param("userId") String userId);

    IssueAuditionTimeDTO getIssueAuditionTime(@Param("issueId") String issueId, @Param("courseName") String courseName, @Param("issueName") String issueName, @Param("userId") String userId);

    List<IssueAuditionCountDTO> getIssueAuditionCount(@Param("issueId") String issueId, @Param("list") List list);

    List<IssueAuditionCountBatchDTO> getIssueAuditionCountBatch(@Param("list") List list);

    ListenSituationTotalDTO getListenSituationTotal(@Param("issueId") String issueId);

    ListenSituationTotalDTO getUserListenSituationTotal(@Param("issueId") String issueId, @Param("courseName") String courseName, @Param("issueName") String issueName, @Param("userId") String userId);

}
