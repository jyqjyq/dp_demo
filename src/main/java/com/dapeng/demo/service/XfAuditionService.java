package com.dapeng.demo.service;

import com.dapeng.demo.models.dto.in.*;
import com.dapeng.demo.models.dto.out.*;
import com.dapeng.demo.util.response.ApiResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface XfAuditionService {


    ApiResult<ListenCourseOutDTO> getListenCourse(@RequestBody ListenCourseInDTO dto);

    ApiResult<WorkSituationOutDTO> getWorkSituation(@RequestBody ListenCourseInDTO dto);

    ApiResult<List<WorkSituationBatchOutDTO>> getWorkSituationBatch(@RequestBody ListenCourseInDTO dto);

    ApiResult<List<WorkSituationByCourseOutDTO>> getWorkSituationByCourse(@RequestBody WorkSituationByCourseInDTO dto);

    ApiResult<List<SubmitCountOutDTO>> getSubmitCount(@RequestBody AuditionCountInDTO dto);

    ApiResult<List<SubmitCountBatchOutDTO>> getSubmitCountBatch(@RequestBody AuditionCountBatchInDTO dto);

    ApiResult<IssueAuditionCountOutPageDTO> getIssueAuditionCount(@RequestBody IssueAuditionCountInDTO dto);

    ApiResult<List<IssueAuditionCountBatchOutDTO>> getIssueAuditionCountBatch(@RequestBody IssueAuditionCountBatchInDTO dto);

    ApiResult<IssueAuditionTimeOutDTO> getIssueAuditionTime(@RequestBody AuditionCountInDTO dto);

    ApiResult<TaskTotalOutDTO> getTaskTotal(@RequestBody TaskTotalInDTO dto);

    ApiResult<ListenSituationOutDTO> getListenSituationTotal(@RequestBody ListenSituationInDTO dto);

}
