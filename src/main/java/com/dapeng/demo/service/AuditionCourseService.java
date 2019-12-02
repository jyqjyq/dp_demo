package com.dapeng.demo.service;

import com.dapeng.demo.models.dto.*;
import com.dapeng.demo.models.dto.WorkSituationCourseDTO;
import com.dapeng.demo.models.resp.IssueAuditionTimeResp;
import com.dapeng.demo.models.resp.ListenSituationResultResp;
import com.dapeng.demo.models.resp.WorkSituationResultResp;

import java.util.Date;
import java.util.List;

public interface AuditionCourseService {

	List<String> getCourseList(Date date, String college);

	AuditionCourseSummaryDTO getSummary(Date date, String classCodes, String courseName);

	List<AuditionCourseDetailDTO> getAuditionCourseDetails(Date date, String classCodes, String courseName, String sortType);

	ListenSituationResultResp getListenCourse(String studentId, String stageId, Integer page, Integer size);



	WorkSituationResultResp getWorkSituation(String studentId, String stageId, Integer page, Integer size);

	List<WorkSituationBatchDTO> getWorkSituationBatch(String stageId, String studentId);

	List<WorkSituationCourseDTO> getWorkSituationByCourse(String studentId, String courseId);

	List<SubmitCountDTO> getSubmitCount(String studentId, String stageId);

	List<SubmitCountBatchDTO> getSubmitCountBatch(String stageId);

	IssueAuditionCountPageDTO getIssueAuditionCount(String studentId, String stageId, Integer pageNumber, Integer pageSize);

	List<IssueAuditionCountBatchDTO> getIssueAuditionCountBatch(String stageId);

	IssueAuditionTimeResp getIssueAuditionTime(String studentId, String stageId);

	ListenSituationTotalDTO getListenSituationTotal(String studentId, String stageId);

	TaskTotalDTO getTaskTotal(String studentId, String stageId);




}
