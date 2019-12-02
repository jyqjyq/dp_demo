package com.dapeng.demo.mapper;

import com.dapeng.demo.models.dto.SubmitCountBatchDTO;
import com.dapeng.demo.models.dto.SubmitCountDTO;
import com.dapeng.demo.models.dto.TaskTotalDTO;
import com.dapeng.demo.models.dto.WorkSituationDTO;
import com.dapeng.demo.models.dto.WorkSituationCourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskEMapper {

    List<WorkSituationDTO> getWorkSituation(@Param("issueId") String issueId, @Param("userId") String userId);

    List<WorkSituationDTO> getWorkSituationBash(@Param("issueId") String issueId, @Param("list") List<String> list);

    List<WorkSituationCourseDTO> getWorkSituationByCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    List<SubmitCountDTO> getSubmitCount(@Param("issueId") String issueId, @Param("list") List list);

    List<SubmitCountBatchDTO> getSubmitCountBatch(@Param("list") List list);

    TaskTotalDTO getTaskTotal(@Param("issueId") String issueId, @Param("studentId") String studentId);

    TaskTotalDTO getUserTaskTotal(@Param("issueId") String issueId, @Param("studentId") String studentId);

}
