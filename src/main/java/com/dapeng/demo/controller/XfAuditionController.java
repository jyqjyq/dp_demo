package com.dapeng.demo.controller;

import com.dapeng.demo.models.dto.in.*;
import com.dapeng.demo.models.dto.out.*;
import com.dapeng.demo.service.XfAuditionService;
import com.dapeng.demo.util.response.ApiResult;
import com.dapeng.demo.util.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "AuditionController", description = "试听 API")
@RestController
@RequestMapping("/api/audition")
public class XfAuditionController {

    private final static Logger log = LoggerFactory.getLogger(XfAuditionController.class);

    @Autowired
    private XfAuditionService auditionFeignCallService;

    @ApiOperation(value = "听课情况")
    @RequestMapping(value = "/getListenCourse", method = RequestMethod.POST)
    public List<ListenCourseDataOutDTO> getListenCourse(ListenCourseInDTO listenCourseInDTO, HttpServletResponse response){
        log.info("getListenCourse in");
        ApiResult<ListenCourseOutDTO> result = auditionFeignCallService.getListenCourse(listenCourseInDTO);
        ListenCourseOutDTO listenCourse = result.getData();
        response.setHeader("X-Pagination-Count", listenCourse.getTotal());
        response.setHeader("X-Pagination-Number", listenCourseInDTO.getPage()+"");
        response.setHeader("X-Pagination-Pages", listenCourse.getTotalPageNum());
        response.setHeader("X-Pagination-Size", listenCourseInDTO.getSize()+"");
        log.info("getListenCourse result:{}", JsonUtils.toJSON(listenCourse));
        return listenCourse.getResult();
    }

    @ApiOperation(value = "作业情况")
    @RequestMapping(value = "/getWorkSituation", method = RequestMethod.POST)
    public List<WorkSituationDataOutDTO> getWorkSituation(ListenCourseInDTO listenCourseInDTO, HttpServletResponse response){
        log.info("getWorkSituation in");
        ApiResult<WorkSituationOutDTO> workSituation = auditionFeignCallService.getWorkSituation(listenCourseInDTO);
        WorkSituationOutDTO listenCourse = workSituation.getData();
        response.setHeader("X-Pagination-Count", listenCourse.getTotal());
        response.setHeader("X-Pagination-Number", listenCourseInDTO.getPage()+"");
        response.setHeader("X-Pagination-Pages", listenCourse.getTotalPageNum());
        response.setHeader("X-Pagination-Size", listenCourseInDTO.getSize()+"");
        log.info("getWorkSituation result:{}", JsonUtils.toJSON(listenCourse));
        return listenCourse.getResult();
    }

    @ApiOperation(value = "作业情况批量")
    @RequestMapping(value = "/getWorkSituationBatch", method = RequestMethod.POST)
    public List<WorkSituationBatchOutDTO> getWorkSituationBatch(ListenCourseInDTO listenCourseInDTO){
        log.info("getWorkSituationBatch in");
        ApiResult<List<WorkSituationBatchOutDTO>> workSituationBatch = auditionFeignCallService.getWorkSituationBatch(listenCourseInDTO);
        List<WorkSituationBatchOutDTO> data = workSituationBatch.getData();
        log.info("getWorkSituationBatch result:{}", JsonUtils.toJSON(data));
        return data;
    }

    @ApiOperation(value = "作业情况通过课程ID")
    @RequestMapping(value = "/getWorkSituationByCourse", method = RequestMethod.POST)
    public List<WorkSituationByCourseOutDTO> getWorkSituationByCourse(WorkSituationByCourseInDTO listenCourseInDTO){
        log.info("getWorkSituationByCourse in");
        ApiResult<List<WorkSituationByCourseOutDTO>> workSituationByCourse = auditionFeignCallService.getWorkSituationByCourse(listenCourseInDTO);
        log.info("getWorkSituationByCourse result:{}", JsonUtils.toJSON(workSituationByCourse.getData()));
        return workSituationByCourse.getData();
    }

    @ApiOperation(value = "作业情况总数接口")
    @PostMapping(value = "/getSubmitCount")
    public List<SubmitCountOutDTO> getSubmitCount(AuditionCountInDTO listenCourseInDTO){
        log.info("getSubmitCount in");
        ApiResult<List<SubmitCountOutDTO>> submitCount = auditionFeignCallService.getSubmitCount(listenCourseInDTO);
        log.info("getSubmitCount result:{}", JsonUtils.toJSON(submitCount));
        return submitCount.getData();
    }

    @ApiOperation(value = "作业情况总数接口批量")
    @PostMapping(value = "/getSubmitCountBatch")
    public List<SubmitCountBatchOutDTO> getSubmitCountBatch(AuditionCountBatchInDTO listenCourseInDTO){
        log.info("getSubmitCount in");
        ApiResult<List<SubmitCountBatchOutDTO>> submitCount = auditionFeignCallService.getSubmitCountBatch(listenCourseInDTO);
        log.info("getSubmitCount result:{}", JsonUtils.toJSON(submitCount));
        return submitCount.getData();
    }

    @ApiOperation(value = "听课章节数接口")
    @PostMapping(value = "/getIssueAuditionCount")
    public List<IssueAuditionCountOutDTO> getIssueAuditionCount(IssueAuditionCountInDTO listenCourseInDTO, HttpServletResponse response){
        log.info("getIssueAuditionCount in");
        ApiResult<IssueAuditionCountOutPageDTO> issueAuditionCount = auditionFeignCallService.getIssueAuditionCount(listenCourseInDTO);
        IssueAuditionCountOutPageDTO data = issueAuditionCount.getData();
        response.setHeader("X-Pagination-Count", data.getTotal());
        response.setHeader("X-Pagination-Number", String.valueOf(listenCourseInDTO.getPage()));
        response.setHeader("X-Pagination-Pages", data.getTotalPageNum());
        response.setHeader("X-Pagination-Size", String.valueOf(listenCourseInDTO.getSize()));
        log.info("getIssueAuditionCount result:{}", JsonUtils.toJSON(issueAuditionCount));
        return data.getResult();
    }

    @ApiOperation(value = "听课章节数接口批量")
    @PostMapping(value = "/getIssueAuditionCountBatch")
    public List<IssueAuditionCountBatchOutDTO> getIssueAuditionCountBatch(IssueAuditionCountBatchInDTO listenCourseInDTO, HttpServletResponse response){
        log.info("getIssueAuditionCount in");
        ApiResult<List<IssueAuditionCountBatchOutDTO>> issueAuditionCountBatch = auditionFeignCallService.getIssueAuditionCountBatch(listenCourseInDTO);
        List<IssueAuditionCountBatchOutDTO> data = issueAuditionCountBatch.getData();
        log.info("getIssueAuditionCount result:{}", JsonUtils.toJSON(issueAuditionCountBatch));
        return data;
    }

    @ApiOperation(value = "听课总时长接口")
    @PostMapping(value = "/getIssueAuditionTime")
    public IssueAuditionTimeOutDTO getIssueAuditionTime(AuditionCountInDTO listenCourseInDTO){
        log.info("getIssueAuditionTime in");
        ApiResult<IssueAuditionTimeOutDTO> issueAuditionTime = auditionFeignCallService.getIssueAuditionTime(listenCourseInDTO);
        log.info("getIssueAuditionTime result:{}", JsonUtils.toJSON(issueAuditionTime));
        return issueAuditionTime.getData();
    }

    @ApiOperation(value = "听课情况统计")
    @PostMapping(value = "/getListenSituationTotal")
    public ListenSituationOutDTO getListenSituationTotal(ListenSituationInDTO listenCourseInDTO){
        log.info("getListenSituationTotal in");
        ApiResult<ListenSituationOutDTO> issueAuditionTime = auditionFeignCallService.getListenSituationTotal(listenCourseInDTO);
        log.info("getListenSituationTotal result:{}", JsonUtils.toJSON(issueAuditionTime));
        return issueAuditionTime.getData();
    }

    @ApiOperation(value = "作业情况统计")
    @PostMapping(value = "/getTaskTotal")
    public TaskTotalOutDTO getTaskTotal(TaskTotalInDTO listenCourseInDTO){
        log.info("getTaskTotal in");
        ApiResult<TaskTotalOutDTO> issueAuditionTime = auditionFeignCallService.getTaskTotal(listenCourseInDTO);
        log.info("getTaskTotal result:{}", JsonUtils.toJSON(issueAuditionTime));
        return issueAuditionTime.getData();
    }
}
