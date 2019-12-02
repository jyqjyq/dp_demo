package com.dapeng.demo.service.impl;

import com.dapeng.demo.annotation.DataSource;
import com.dapeng.demo.enums.DataSourceEnum;
import com.dapeng.demo.mapper.AuditionCourseMapper;
import com.dapeng.demo.mapper.TaskEMapper;
import com.dapeng.demo.models.dto.*;
import com.dapeng.demo.models.dto.WorkSituationCourseDTO;
import com.dapeng.demo.models.resp.*;
import com.dapeng.demo.service.AuditionCourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuditionCourseServiceImpl  implements AuditionCourseService {

	@Autowired
	private AuditionCourseMapper auditionCourseMapper;

	@Autowired
	private TaskEMapper taskEDAO;

	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<String> getCourseList(Date date, String college) {
		return auditionCourseMapper.getCourseList(date, college);
	}

	@Override
	@DataSource(DataSourceEnum.DB1)
	public AuditionCourseSummaryDTO getSummary(Date date, String classCodes, String courseName) {
		List<String> classCodeList = null;
		if (StringUtils.isNotBlank(classCodes)) {
			classCodeList = Arrays.asList(classCodes.split(","));
		}
		return auditionCourseMapper.getSummary(date, classCodeList, courseName);
	}

	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<AuditionCourseDetailDTO> getAuditionCourseDetails(Date date, String classCodes, String courseName, String sortType) {
		List<String> classCodeList = null;
		if (StringUtils.isNotBlank(classCodes)) {
			classCodeList = Arrays.asList(classCodes.split(","));
		}
		return auditionCourseMapper.getAuditionCourseDetails(date, classCodeList, courseName, sortType);
	}

	@Override
	@DataSource(DataSourceEnum.DB1)
	public ListenSituationResultResp getListenCourse(String studentId, String stageId, Integer page, Integer size) {
		ListenSituationResultResp listenSituationResultResp = new ListenSituationResultResp();

		CourseAndIssueDTO courseAndIssue = auditionCourseMapper.getCourseAndIssue(stageId);
		if(null ==courseAndIssue){
			return listenSituationResultResp;
		}
		Page<Object> objects = PageHelper.startPage(page, size);
		List<ListenSituationDTO> list = auditionCourseMapper.getListenSituation(stageId, courseAndIssue.getCourseTitle(), courseAndIssue.getIssueTitle(), studentId);

		List<ListenCourseResp> listenCourseResps = new ArrayList<>();
		for (ListenSituationDTO listenSituationDTO : list) {
			ListenCourseResp resp = new ListenCourseResp();
			resp.setCourseDuration(listenSituationDTO.getCourseDuration());
			resp.setLiveDuration(listenSituationDTO.getLiveDuration());
			resp.setOpenChapters(listenSituationDTO.getOpenChapters());
			resp.setPlayBackDuration(listenSituationDTO.getPlayBackDuration());
			resp.setTeachingTime(listenSituationDTO.getTeachingTime());
			listenCourseResps.add(resp);
		}

		listenSituationResultResp.setTotalPageNum(objects.getPages()+"");
		listenSituationResultResp.setTotal(objects.getTotal()+"");
		listenSituationResultResp.setResult(listenCourseResps);
		return listenSituationResultResp;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public WorkSituationResultResp getWorkSituation(String studentId, String stageId, Integer page, Integer size) {
		WorkSituationResultResp listenSituationResultResp = new WorkSituationResultResp();

		Page<Object> objects = new Page<>();
		if(null != page) {
			objects = PageHelper.startPage(page, size);
		}
		List<WorkSituationDTO> workSituation = taskEDAO.getWorkSituation(stageId, studentId);

		List<WorkSituationResp> listenCourseResps = new ArrayList<>();
		for (WorkSituationDTO listenSituationDTO : workSituation) {
			WorkSituationResp resp = new WorkSituationResp();
			resp.setIsHandIn(listenSituationDTO.getIsHandIn());
			resp.setOpenChapters(listenSituationDTO.getOpenChapters());
			resp.setScore(listenSituationDTO.getScore());
			resp.setSubmitHomeworkTime(listenSituationDTO.getSubmitHomeworkTime());
			resp.setWorkTitle(listenSituationDTO.getWorkTitle());
			resp.setIsMustHandIn(listenSituationDTO.getIsMustHandIn());
			listenCourseResps.add(resp);
		}

		listenSituationResultResp.setTotalPageNum(objects.getPages()+"");
		listenSituationResultResp.setTotal(objects.getTotal()+"");
		listenSituationResultResp.setResult(listenCourseResps);
		return listenSituationResultResp;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<WorkSituationBatchDTO> getWorkSituationBatch(String stageId, String studentId) {
		List<String> list = new ArrayList<>();
		if(studentId.contains(",")){
			String[] split = studentId.split(",");
			list.addAll(Arrays.asList(split));
		}else{
			list.add(studentId);
		}
		List<WorkSituationBatchDTO> workSituationBatchDTOS = new ArrayList<>();

		List<WorkSituationDTO> workSituationBash = taskEDAO.getWorkSituationBash(stageId, list);

		Map<String, List<WorkSituationDTO>> map = new HashMap<>();
		for (WorkSituationDTO situationBash : workSituationBash) {
			if(map.containsKey(situationBash.getUserId())){
				List<WorkSituationDTO> workSituationDTOS = map.get(situationBash.getUserId());
				workSituationDTOS.add(situationBash);
			}else{
				List<WorkSituationDTO> workSituationDTOS = new ArrayList<>();
				workSituationDTOS.add(situationBash);
				map.put(situationBash.getUserId(), workSituationDTOS);
			}
		}

		for (String s : map.keySet()) {
			List<WorkSituationDTO> workSituationDTOS = map.get(s);

			WorkSituationBatchDTO workSituationBatchDTO = new WorkSituationBatchDTO();
			workSituationBatchDTO.setStudentId(s);
			workSituationBatchDTO.setWorks(workSituationDTOS);
			workSituationBatchDTOS.add(workSituationBatchDTO);
		}

		return workSituationBatchDTOS;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<WorkSituationCourseDTO> getWorkSituationByCourse(String studentId, String courseId) {
		List<WorkSituationCourseDTO> workSituationByCourse = taskEDAO.getWorkSituationByCourse(studentId, courseId);
		return workSituationByCourse;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<SubmitCountDTO> getSubmitCount(String studentId, String stageId) {
		List<String> list = new ArrayList<>();
		if(studentId.contains(",")){
			String[] split = studentId.split(",");
			list.addAll(Arrays.asList(split));
		}else{
			list.add(studentId);
		}
		List<SubmitCountDTO> submitCount = taskEDAO.getSubmitCount(stageId, list);
		return submitCount;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<SubmitCountBatchDTO> getSubmitCountBatch(String stageId) {
		List<String> list = new ArrayList<>();
		if(stageId.contains(",")){
			String[] split = stageId.split(",");
			list.addAll(Arrays.asList(split));
		}else{
			list.add(stageId);
		}
		List<SubmitCountBatchDTO> submitCount = taskEDAO.getSubmitCountBatch(list);
		return submitCount;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public IssueAuditionCountPageDTO getIssueAuditionCount(String studentId, String stageId, Integer pageNumber, Integer pageSize) {
		IssueAuditionCountPageDTO pageDTO = new IssueAuditionCountPageDTO();
		List<String> list = new ArrayList<>();
		if(studentId.contains(",")){
			String[] split = studentId.split(",");
			list.addAll(Arrays.asList(split));
		}else{
			list.add(studentId);
		}
		Page<Object> objects = new Page<>();
		if(null != pageNumber) {
			objects = PageHelper.startPage(pageNumber, pageSize);
		}
		List<IssueAuditionCountDTO> issueAuditionCount = auditionCourseMapper.getIssueAuditionCount(stageId, list);
		if(null == issueAuditionCount){
			issueAuditionCount = new ArrayList<>();
		}
		pageDTO.setTotal(String.valueOf(objects.getTotal()));
		pageDTO.setTotalPageNum(String.valueOf(objects.getPages()));
		pageDTO.setResult(issueAuditionCount);
		return pageDTO;
	}

	@Override
	@DataSource(DataSourceEnum.DB1)
	public List<IssueAuditionCountBatchDTO> getIssueAuditionCountBatch(String stageId) {
		List<String> list = new ArrayList<>();
		if(stageId.contains(",")){
			String[] split = stageId.split(",");
			list.addAll(Arrays.asList(split));
		}else{
			list.add(stageId);
		}
		List<IssueAuditionCountBatchDTO> issueAuditionCount = auditionCourseMapper.getIssueAuditionCountBatch(list);
		if(null == issueAuditionCount){
			issueAuditionCount = new ArrayList<>();
		}
		return issueAuditionCount;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public IssueAuditionTimeResp getIssueAuditionTime(String studentId, String stageId) {
		CourseAndIssueDTO courseAndIssue = auditionCourseMapper.getCourseAndIssue(stageId);
		if(null == courseAndIssue){
			IssueAuditionTimeResp issueAuditionTimeResp = new IssueAuditionTimeResp();
			issueAuditionTimeResp.setLiveDurationTotal(0);
			issueAuditionTimeResp.setPlayBackDurationTotal(0);
			return issueAuditionTimeResp;
		}
		IssueAuditionTimeDTO issueAuditionTime = auditionCourseMapper.getIssueAuditionTime(stageId, courseAndIssue.getCourseTitle(), courseAndIssue.getIssueTitle(), studentId);

		IssueAuditionTimeResp issueAuditionTimeResp = new IssueAuditionTimeResp();
		issueAuditionTimeResp.setLiveDurationTotal(issueAuditionTime.getLiveDurationTotal());
		issueAuditionTimeResp.setPlayBackDurationTotal(issueAuditionTime.getPlayBackDurationTotal());
		return issueAuditionTimeResp;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public ListenSituationTotalDTO getListenSituationTotal(String studentId, String stageId) {
		ListenSituationTotalDTO listenSituationTotalDTO = new ListenSituationTotalDTO();
		CourseAndIssueDTO courseAndIssue = auditionCourseMapper.getCourseAndIssue(stageId);
		if(null ==courseAndIssue){
			return listenSituationTotalDTO;
		}

		ListenSituationTotalDTO listenSituationTotal = auditionCourseMapper.getListenSituationTotal(stageId);
		ListenSituationTotalDTO userListenSituationTotal = auditionCourseMapper.getUserListenSituationTotal(stageId, courseAndIssue.getCourseTitle(), courseAndIssue.getIssueTitle(), studentId);
		listenSituationTotalDTO.setChapterListenedCount(userListenSituationTotal.getChapterListenedCount());
		listenSituationTotalDTO.setChapterTotal(listenSituationTotal.getChapterTotal());
		listenSituationTotalDTO.setLiveDurationListened(userListenSituationTotal.getLiveDurationListened());
		listenSituationTotalDTO.setLiveDurationTotal(listenSituationTotal.getLiveDurationTotal());
		return listenSituationTotalDTO;
	}


	@Override
	@DataSource(DataSourceEnum.DB1)
	public TaskTotalDTO getTaskTotal(String studentId, String stageId) {
		TaskTotalDTO taskTotal = taskEDAO.getTaskTotal(stageId, studentId);
		if(taskTotal== null){
			taskTotal = new TaskTotalDTO();
		}
		TaskTotalDTO userTaskTotal = taskEDAO.getUserTaskTotal(stageId, studentId);
		if(userTaskTotal== null){
			userTaskTotal = new TaskTotalDTO();
		}
		taskTotal.setCompletedWorkCount(userTaskTotal.getCompletedWorkCount());
		taskTotal.setMustHandInCompletedWorkCount(userTaskTotal.getMustHandInCompletedWorkCount());
		return taskTotal;
	}


}
