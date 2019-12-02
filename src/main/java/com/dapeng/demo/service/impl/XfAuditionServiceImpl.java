package com.dapeng.demo.service.impl;

import com.dapeng.demo.annotation.DataSource;
import com.dapeng.demo.enums.DataSourceEnum;
import com.dapeng.demo.models.dto.*;
import com.dapeng.demo.models.dto.in.*;
import com.dapeng.demo.models.dto.out.*;
import com.dapeng.demo.models.resp.*;
import com.dapeng.demo.service.AuditionCourseService;
import com.dapeng.demo.service.XfAuditionService;
import com.dapeng.demo.util.response.ApiResult;
import com.dapeng.demo.util.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XfAuditionServiceImpl implements XfAuditionService {
    private static Logger log = LoggerFactory.getLogger(XfAuditionServiceImpl.class);

    @Autowired
    private AuditionCourseService auditionCourseService;

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<ListenCourseOutDTO> getListenCourse(@RequestBody ListenCourseInDTO dto) {
        log.info("getListenCourse param :{}", JsonUtils.toJSON(dto));
        ListenCourseOutDTO outDTO = new ListenCourseOutDTO();

        ListenSituationResultResp result = auditionCourseService.getListenCourse(dto.getStudentId(), dto.getStageId(), dto.getPage(), dto.getSize());

        List<ListenCourseDataOutDTO> listenCourseResps = new ArrayList<>();
        if(null == result.getResult()){
            outDTO.setTotalPageNum("0");
            outDTO.setTotal("0");
            outDTO.setResult(listenCourseResps);
            return ApiResult.ok(outDTO);
        }
        for (ListenCourseResp listenSituationDTO : result.getResult()) {
            ListenCourseDataOutDTO resp = new ListenCourseDataOutDTO();
            resp.setCourseDuration(listenSituationDTO.getCourseDuration());
            resp.setLiveDuration(listenSituationDTO.getLiveDuration());
            resp.setOpenChapters(listenSituationDTO.getOpenChapters());
            resp.setPlayBackDuration(listenSituationDTO.getPlayBackDuration());
            resp.setTeachingTime(listenSituationDTO.getTeachingTime());
            listenCourseResps.add(resp);
        }

        outDTO.setTotalPageNum(result.getTotalPageNum());
        outDTO.setTotal(result.getTotal());
        outDTO.setResult(listenCourseResps);
        return ApiResult.ok(outDTO);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<WorkSituationOutDTO> getWorkSituation(@RequestBody ListenCourseInDTO dto) {
        log.info("getWorkSituation param :{}", JsonUtils.toJSON(dto));
        WorkSituationOutDTO outDTO = new WorkSituationOutDTO();

        WorkSituationResultResp result = auditionCourseService.getWorkSituation(dto.getStudentId(), dto.getStageId(), dto.getPage(), dto.getSize());

        List<WorkSituationDataOutDTO> listenCourseResps = new ArrayList<>();
        for (WorkSituationResp listenSituationDTO : result.getResult()) {
            WorkSituationDataOutDTO resp = new WorkSituationDataOutDTO();
            resp.setIsHandIn(listenSituationDTO.getIsHandIn());
            resp.setOpenChapters(listenSituationDTO.getOpenChapters());
            resp.setScore(listenSituationDTO.getScore());
            resp.setSubmitHomeworkTime(listenSituationDTO.getSubmitHomeworkTime());
            resp.setWorkTitle(listenSituationDTO.getWorkTitle());
            resp.setIsMustHandIn(listenSituationDTO.getIsMustHandIn());
            listenCourseResps.add(resp);
        }

        outDTO.setTotalPageNum(result.getTotalPageNum());
        outDTO.setTotal(result.getTotal());
        outDTO.setResult(listenCourseResps);
        return ApiResult.ok(outDTO);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<List<WorkSituationBatchOutDTO>> getWorkSituationBatch(@RequestBody ListenCourseInDTO dto) {
        List<WorkSituationBatchOutDTO> list = new ArrayList<>();
        List<WorkSituationBatchDTO> workSituationBatch = auditionCourseService.getWorkSituationBatch(dto.getStageId(), dto.getStudentId());
        for (WorkSituationBatchDTO situationBatch : workSituationBatch) {
            WorkSituationBatchOutDTO workSituationBatchOutDTO = new WorkSituationBatchOutDTO();

            List<WorkSituationDataOutDTO> workSituationDataOutDTOS = new ArrayList<>();
            for (WorkSituationDTO workSituationDTO : situationBatch.getWorks()) {
                WorkSituationDataOutDTO outDTO = new WorkSituationDataOutDTO();
                BeanUtils.copyProperties(workSituationDTO, outDTO);
                workSituationDataOutDTOS.add(outDTO);
            }
            workSituationBatchOutDTO.setStudentId(situationBatch.getStudentId());
            workSituationBatchOutDTO.setWorks(workSituationDataOutDTOS);
            list.add(workSituationBatchOutDTO);
        }
        return ApiResult.ok(list);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<List<WorkSituationByCourseOutDTO>> getWorkSituationByCourse(@RequestBody WorkSituationByCourseInDTO dto) {
        List<WorkSituationByCourseOutDTO> list = new ArrayList<>();
        List<WorkSituationCourseDTO> workSituationByCourse = auditionCourseService.getWorkSituationByCourse(dto.getStudentId(), dto.getCourseId());

        Map<String, List<WorkSituationByCourseInfoOutDTO>> map = new HashMap<>();
        for (WorkSituationCourseDTO workSituationCourseDTO : workSituationByCourse) {
            WorkSituationByCourseInfoOutDTO infoOutDTO = new WorkSituationByCourseInfoOutDTO();
            BeanUtils.copyProperties(workSituationCourseDTO, infoOutDTO);
            if(map.containsKey(workSituationCourseDTO.getCourseChannelId())){
                List<WorkSituationByCourseInfoOutDTO> workSituationByCourseInfoOutDTOS = map.get(workSituationCourseDTO.getCourseChannelId());
                workSituationByCourseInfoOutDTOS.add(infoOutDTO);
            }else{
                List<WorkSituationByCourseInfoOutDTO> workSituationByCourseInfoOutDTOS = new ArrayList<>();
                workSituationByCourseInfoOutDTOS.add(infoOutDTO);
                map.put(workSituationCourseDTO.getCourseChannelId(), workSituationByCourseInfoOutDTOS);
            }
        }

        for (String s : map.keySet()) {
            WorkSituationByCourseOutDTO workSituationByCourseOutDTO = new WorkSituationByCourseOutDTO();
            workSituationByCourseOutDTO.setStageId(s);
            workSituationByCourseOutDTO.setWorks(map.get(s));
            list.add(workSituationByCourseOutDTO);
        }
        return ApiResult.ok(list);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<IssueAuditionTimeOutDTO> getIssueAuditionTime(@RequestBody AuditionCountInDTO dto) {
        IssueAuditionTimeResp issueAuditionTime = auditionCourseService.getIssueAuditionTime(dto.getStudentId(), dto.getStageId());

        IssueAuditionTimeOutDTO issueAuditionTimeOutDTO = new IssueAuditionTimeOutDTO();
        issueAuditionTimeOutDTO.setLiveDurationTotal(issueAuditionTime.getLiveDurationTotal());
        issueAuditionTimeOutDTO.setPlayBackDurationTotal(issueAuditionTime.getPlayBackDurationTotal());
        return ApiResult.ok(issueAuditionTimeOutDTO);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<TaskTotalOutDTO> getTaskTotal(@RequestBody TaskTotalInDTO dto) {
        TaskTotalDTO taskTotal = auditionCourseService.getTaskTotal(dto.getStudentId(), dto.getStageId());
        TaskTotalOutDTO taskTotalOutDTO= new TaskTotalOutDTO();
        BeanUtils.copyProperties(taskTotal, taskTotalOutDTO);
        return ApiResult.ok(taskTotalOutDTO);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<ListenSituationOutDTO> getListenSituationTotal(@RequestBody ListenSituationInDTO dto) {
        ListenSituationTotalDTO listenSituationTotal = auditionCourseService.getListenSituationTotal(dto.getStudentId(), dto.getStageId());
        ListenSituationOutDTO listenSituationOutDTO = new ListenSituationOutDTO();
        listenSituationOutDTO.setChapterListenedCount(listenSituationTotal.getChapterListenedCount());
        listenSituationOutDTO.setChapterTotal(listenSituationTotal.getChapterTotal());
        listenSituationOutDTO.setLiveDurationListened(listenSituationTotal.getLiveDurationListened());
        listenSituationOutDTO.setLiveDurationTotal(listenSituationTotal.getLiveDurationTotal());
        return ApiResult.ok(listenSituationOutDTO);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<IssueAuditionCountOutPageDTO> getIssueAuditionCount(@RequestBody IssueAuditionCountInDTO dto) {
        IssueAuditionCountOutPageDTO pageDTO = new IssueAuditionCountOutPageDTO();
        List<IssueAuditionCountOutDTO> list = new ArrayList<>();
        IssueAuditionCountPageDTO issueAuditionCountPage = auditionCourseService.getIssueAuditionCount(dto.getStudentId(), dto.getStageId(), dto.getPage(), dto.getSize());
        if(null == issueAuditionCountPage || issueAuditionCountPage.getResult().size()<1){
            pageDTO.setTotal(String.valueOf(0));
            pageDTO.setTotalPageNum(String.valueOf(0));
            pageDTO.setResult(list);
            return ApiResult.ok(pageDTO);
        }
        for (IssueAuditionCountDTO issueAuditionCountDTO : issueAuditionCountPage.getResult()) {
            IssueAuditionCountOutDTO issueAuditionCountOutDTO =  new IssueAuditionCountOutDTO();
            issueAuditionCountOutDTO.setIssueAuditionCount(issueAuditionCountDTO.getIssueAuditionCount());
            issueAuditionCountOutDTO.setStudentId(issueAuditionCountDTO.getStudentId());
            list.add(issueAuditionCountOutDTO);
        }
        pageDTO.setTotal(issueAuditionCountPage.getTotal());
        pageDTO.setTotalPageNum(issueAuditionCountPage.getTotalPageNum());
        pageDTO.setResult(list);
        return ApiResult.ok(pageDTO);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<List<IssueAuditionCountBatchOutDTO>> getIssueAuditionCountBatch(@RequestBody IssueAuditionCountBatchInDTO dto) {
        List<IssueAuditionCountBatchOutDTO> list = new ArrayList<>();
        List<IssueAuditionCountBatchDTO> issueAuditionCountBatch = auditionCourseService.getIssueAuditionCountBatch(dto.getStageIds());
        for (IssueAuditionCountBatchDTO issueAuditionCountDTO : issueAuditionCountBatch) {
            IssueAuditionCountBatchOutDTO issueAuditionCountOutDTO =  new IssueAuditionCountBatchOutDTO();
            issueAuditionCountOutDTO.setChapterNumListened(issueAuditionCountDTO.getChapterNumListened());
            issueAuditionCountOutDTO.setUserId(issueAuditionCountDTO.getUserId());
            issueAuditionCountOutDTO.setStageId(issueAuditionCountDTO.getStageId());
            list.add(issueAuditionCountOutDTO);
        }
        return ApiResult.ok(list);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<List<SubmitCountOutDTO>> getSubmitCount(@RequestBody AuditionCountInDTO dto) {
        List<SubmitCountDTO> submitCount = auditionCourseService.getSubmitCount(dto.getStudentId(), dto.getStageId());
        List<SubmitCountOutDTO> list = new ArrayList<>();
        for (SubmitCountDTO submitCountDTO : submitCount) {
            SubmitCountOutDTO submitCountOutDTO = new SubmitCountOutDTO();
            submitCountOutDTO.setUserId(submitCountDTO.getUserId());
            submitCountOutDTO.setCompleteWorkCount(submitCountDTO.getCompleteWorkCount());
            list.add(submitCountOutDTO);
        }
        return ApiResult.ok(list);
    }

    @Override
    @DataSource(DataSourceEnum.DB1)
    public ApiResult<List<SubmitCountBatchOutDTO>> getSubmitCountBatch(@RequestBody AuditionCountBatchInDTO dto) {
        List<SubmitCountBatchDTO> submitCount = auditionCourseService.getSubmitCountBatch(dto.getStageIds());
        List<SubmitCountBatchOutDTO> list = new ArrayList<>();
        for (SubmitCountBatchDTO submitCountDTO : submitCount) {
            SubmitCountBatchOutDTO submitCountOutDTO = new SubmitCountBatchOutDTO();
            submitCountOutDTO.setUserId(submitCountDTO.getUserId());
            submitCountOutDTO.setWorkNumCompleted(submitCountDTO.getWorkNumCompleted());
            submitCountOutDTO.setStageId(submitCountDTO.getStageId());
            list.add(submitCountOutDTO);
        }
        return ApiResult.ok(list);
    }

}
